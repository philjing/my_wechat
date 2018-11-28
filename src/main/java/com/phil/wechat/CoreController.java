/**
 * FileName: CoreController
 * Author:   Phil
 * Date:     8/1/2018 15:52
 * Description: 接入微信并处理消息事件
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat;

import com.phil.modules.config.WechatAccountConfig;
import com.phil.modules.util.DateUtils;
import com.phil.modules.util.SignatureUtil;
import com.phil.modules.util.XmlUtil;
import com.phil.wechat.auth.service.WechatAuthService;
import com.phil.wechat.message.constant.MsgTypeConstant;
import com.phil.wechat.message.model.basic.request.RequestMessage;
import com.phil.wechat.message.model.template.request.WechatTemplateMessage;
import com.phil.wechat.message.service.WechatMessageService;
import com.phil.wechat.message.service.WechatTemplateMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 〈一句话功能简述〉
 * 〈接入微信并处理消息事件〉
 *
 * @author Phil
 * @create 8/1/2018 15:52
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/core/weixin/V1")
@Slf4j
public class CoreController {

    @Resource
    private WechatAccountConfig wechatAccountConfig;

    @Resource
    private WechatMessageService wechatMessageService;

    @Resource
    private WechatTemplateMessageService wechatTemplateMessageService;

    @Resource
    private WechatAuthService wechatAuthService;

    /**
     * 处理微信服务器发来的get请求，进行签名的验证
     * <p>
     * signature 微信端发来的签名
     * timestamp 微信端发来的时间戳
     * nonce     微信端发来的随机字符串
     * echostr   微信端发来的验证字符串
     */
    @GetMapping(value = "wechat")
    public String validate(@RequestParam(value = "signature") String signature,
                           @RequestParam(value = "timestamp") String timestamp,
                           @RequestParam(value = "nonce") String nonce,
                           @RequestParam(value = "echostr") String echostr) {
        return SignatureUtil.checkSignature(signature, wechatAccountConfig.getToken(), timestamp, nonce) ? echostr : null;
    }

    /**
     * 此处是处理微信服务器的消息转发的
     */
    @PostMapping(value = "wechat")
    public void processMsg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //调用核心服务类接收处理请求
        String respXml = defaultMsgDispose(request.getInputStream());
//        log.info("回复的xml" + respXml);
        IOUtils.write(respXml.getBytes(), response.getOutputStream());
//        response.getWriter().write(respXml);
    }


    private String defaultMsgDispose(InputStream input) throws Exception {
        String wxXml = IOUtils.toString(input);
        System.out.println(wxXml);
        RequestMessage message = XmlUtil.fromXml(wxXml, RequestMessage.class);
        System.out.println(message.toString());
        String msgType = message.getMsgType();
        String respXml = "系统故障";
        switch (msgType) {
            case MsgTypeConstant.REQ_MESSAGE_TYPE_TEXT: // 文本消息
                respXml = wechatMessageService.sendTextMsg(message);
                break;
            case MsgTypeConstant.REQ_MESSAGE_TYPE_EVENT:
                switch (message.getEvent()) {
                    //用户已关注时的事件推送
                    case MsgTypeConstant.EVENT_TYPE_SCAN:
                        respXml = wechatMessageService.doScan(message);
                        wechatTemplateMessageService.sendTemplate(
                                wechatAuthService.getAccessToken(wechatAccountConfig.getAppid(), wechatAccountConfig.getAppsecret()),
                                toTemplate(message));
                        break;
                    //用户未关注时，进行关注后的事件推送
                    case MsgTypeConstant.EVENT_TYPE_SUBSCRIBE:
                        respXml = wechatMessageService.doSubscribe(message);
                        wechatTemplateMessageService.sendTemplate(
                                wechatAuthService.getAccessToken(wechatAccountConfig.getAppid(), wechatAccountConfig.getAppsecret()),
                                toTemplate(message));
                }
            default:
        }
        return respXml;
    }

    private String toTemplate(RequestMessage message) throws Exception {
        Map<String, Map<String, String>> data = new HashMap<>();
        WechatTemplateMessage template = new WechatTemplateMessage();
        data.put("keyword1", template.item(message.getEventKey(), "#000000"));
        data.put("keyword2", template.item(DateUtils.getDateString(DateUtils.DATE_FORMAT), "#000000"));
        data.put("keyword3", template.item(new Random().nextInt() + "", "#000000"));
        template.setTouser(message.getFromUserName());
        template.setTemplateId("bFpV7w3DhSUhlZtu2FZAlijoQs97Z6DA_PpCqOqmPfQ");
        template.setUrl("");
        template.setData(data);
        return template.toJson();
    }
}
