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
import com.phil.modules.config.WechatImportConfig;
import com.phil.modules.result.WechatResult;
import com.phil.modules.util.DateUtils;
import com.phil.modules.util.SignatureUtil;
import com.phil.modules.util.XmlUtil;
import com.phil.wechat.auth.service.WechatAuthService;
import com.phil.wechat.material.service.WechatMaterialService;
import com.phil.wechat.message.constant.WechatMessageConstant;
import com.phil.wechat.message.model.basic.request.RequestMessage;
import com.phil.wechat.message.model.basic.response.*;
import com.phil.wechat.message.model.template.request.WechatTemplateMessage;
import com.phil.wechat.message.service.WechatMessageService;
import com.phil.wechat.message.service.WechatTemplateMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * 〈接入微信并处理消息事件〉
 *
 * @Author: Mr.Jing
 * @Date: 2019/12/20
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

    @Resource
    private WechatMaterialService wechatMaterialService;

    @Resource
    private WechatImportConfig wechatImportConfig;

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
        defaultMsgDispose(request.getInputStream(), response.getOutputStream());
    }

    private void defaultMsgDispose(InputStream input, OutputStream output) throws Exception {
        String wxXml = IOUtils.toString(input);
        RequestMessage message = XmlUtil.fromXml(wxXml, RequestMessage.class);
        String msgType = message.getMsgType();
        if (Objects.equals(msgType, WechatMessageConstant.REQ_MESSAGE_TYPE_EVENT)) {
            log.info("微信推送的事件消息" + message);
        } else {
            log.info("用户发送的消息" + message);
        }
        //配置表字段  关键字、触发类型(精准、包含、正则)、回复类型(图文、文字、图片、音乐、语音、视频)、是否开启
        //根据消息类型MsgType.如果是事件根据事件类型查询配置否则根据消息类型
        //作为示例，结果采用 WechatImportConfig配置表示
        if (Objects.equals(msgType, WechatMessageConstant.REQ_MESSAGE_TYPE_EVENT)) {
//            wechatImportConfig.setKey(message.getEvent());  //模仿查询结果
        } else if (Objects.equals(msgType, WechatMessageConstant.REQ_MESSAGE_TYPE_TEXT)) {  //关键词匹配
//            wechatImportConfig.setMatch(1); //完全匹配
//            wechatImportConfig.setKey(message.getContent().trim());
        } else {
//            wechatImportConfig.setKey(message.getMsgType()); //模仿查询结果
        }
        System.out.println(wechatImportConfig);
        //处理匹配后的结果
        if (wechatImportConfig.getEnable() == 0) {
            WechatResult keyResult = parseKeyword(message, wechatImportConfig);
            if (keyResult.isXml()) {
                IOUtils.write(keyResult.getRespXml(), output);
            }

            if (keyResult.isNews()) {
                IOUtils.write(keyResult.getRespXml(), output);
            }
            //发模板消息
//        if (keyResult.isTemplate()) {
//            wechatTemplateMessageService.sendTemplate(
//                    wechatAuthService.getAccessToken(),
//                    toTemplate(message, new WechatTemplateMessage()));
//        }
        } else {
            String respXml = "";
            //没有匹配上的
            WechatResult result = new WechatResult();
            switch (msgType) {
                case WechatMessageConstant.REQ_MESSAGE_TYPE_TEXT: // 文本消息
                    respXml = wechatMessageService.sendTextMsg(message);
                    result.setXml(true);
                    result.setRespXml(respXml);
                    break;
                case WechatMessageConstant.REQ_MESSAGE_TYPE_IMAGE: // 图片消息
                    respXml = wechatMessageService.sendTextMsg(message);
                    result.setXml(true);
                    result.setRespXml(respXml);
                    break;
                case WechatMessageConstant.REQ_MESSAGE_TYPE_VIDEO: // 语音消息
                    respXml = wechatMessageService.sendTextMsg(message);
                    result.setXml(true);
                    result.setRespXml(respXml);
                    break;
                case WechatMessageConstant.REQ_MESSAGE_TYPE_SHORTVIDEO: //小视频消息
                    respXml = wechatMessageService.sendTextMsg(message);
                    result.setXml(true);
                    result.setRespXml(respXml);
                    break;
                case WechatMessageConstant.REQ_MESSAGE_TYPE_LOCATION: // 地理消息
                    respXml = wechatMessageService.sendTextMsg(message);
                    result.setXml(true);
                    result.setRespXml(respXml);
                    break;
                case WechatMessageConstant.REQ_MESSAGE_TYPE_LINK: // 链接消息消息
                    respXml = wechatMessageService.sendTextMsg(message);
                    result.setXml(true);
                    result.setRespXml(respXml);
                    break;
                case WechatMessageConstant.REQ_MESSAGE_TYPE_EVENT:
                    switch (message.getEvent()) {
                        //用户已关注时的事件推送
                        case WechatMessageConstant.EVENT_TYPE_SCAN:
                            result = wechatMessageService.doScan(message);
                            if (result.isTemplate()) {
                                String json = this.toTemplate(message, new WechatTemplateMessage("bFpV7w3DhSUhlZtu2FZAlijoQs97Z6DA_PpCqOqmPfQ", ""));
                                result.setRespJson(json);
                            }
                            break;
                        //用户未关注时，进行关注后的事件推送
                        case WechatMessageConstant.EVENT_TYPE_SUBSCRIBE:
                            result = wechatMessageService.doSubscribe(message);
                            if (result.isTemplate()) {
                                String json = this.toTemplate(message, new WechatTemplateMessage("bFpV7w3DhSUhlZtu2FZAlijoQs97Z6DA_PpCqOqmPfQ", ""));
                                result.setRespJson(json);
                            }
                            break;
                        //用户取消注时
                        case WechatMessageConstant.EVENT_TYPE_UNSUBSCRIBE:
//                            respXml = wechatMessageService.doUnsubscribe(message);
                            log.debug("用户取消关注了┭┮﹏┭┮");
                            break;
                        //上报地理位置事件
                        case WechatMessageConstant.EVENT_TYPE_LOCATION:
//                            respXml = wechatMessageService.doLocation(message);
                            log.debug("用户上报地理位置事件了");
                            break;
                        //自定义菜单事件 点击菜单拉取消息时的事件推送
                        case WechatMessageConstant.EVENT_TYPE_CLICK:
//                            respXml = wechatMessageService.doClick(message);
                            break;
                        //自定义菜单事件 点击菜单跳转链接时的事件推送
                        case WechatMessageConstant.EVENT_TYPE_VIEW:
//                            respXml = wechatMessageService.doView(message);
                            break;
                    }
                default:
            }

            System.out.println(result);
            //回复文本消息
            if (StringUtils.isNotBlank(respXml)) {
                IOUtils.write(respXml.trim(), output);
            }

            if (result.isXml()) {
                IOUtils.write(result.toXml(), output);
            }

            //推送模板消息
            if (result.isTemplate()) {
                wechatTemplateMessageService.sendTemplate(wechatAuthService.getAccessToken(), result.getRespJson());
            }
        }
    }

    //图文、文字、图片、音乐、语音、视频
    //关键字处理
    private WechatResult parseKeyword(RequestMessage message, WechatImportConfig config) {
        WechatResult result = new WechatResult();
        switch (config.getRespType()) {
            case WechatMessageConstant.RESP_MESSAGE_TYPE_TEXT:
                TextMessage text = new TextMessage();
                text.setContent(config.getResponse());
//                result = wechatMessageService.sendTextMsg(message, text);
                break;
            case WechatMessageConstant.RESP_MESSAGE_TYPE_NEWS:
                NewsMessage news = new NewsMessage();
                news.setCreateTime(DateUtils.getCurrentTimeMillis());
                news.setToUserName(message.getFromUserName());
                news.setFromUserName(message.getToUserName());
                news.addArticle(new NewsMessage.Item());
                news.addArticle(new NewsMessage.Item());
//                result = wechatMessageService.sendNewsMsg(news);
                break;
            case WechatMessageConstant.RESP_MESSAGE_TYPE_IMAGE:
                ImageMessage image = new ImageMessage();
                image.setCreateTime(DateUtils.getCurrentTimeMillis());
                image.setToUserName(message.getFromUserName());
                image.setFromUserName(message.getToUserName());
                image.setMediaId(config.getResponse());
//                result = wechatMessageService.sendImageMsg(image);
                break;
            case WechatMessageConstant.RESP_MESSAGE_TYPE_MUSIC:
                MusicMessage music = new MusicMessage();
                music.setCreateTime(DateUtils.getCurrentTimeMillis());
                music.setToUserName(message.getFromUserName());
                music.setFromUserName(message.getToUserName());
                music.setMusic(new MusicMessage.Music(message.getThumbMediaId()));
//                result = wechatMessageService.sendMusicMsg(music);
                break;
            case WechatMessageConstant.RESP_MESSAGE_TYPE_VOICE:
                VoiceMessage voice = new VoiceMessage();
                voice.setCreateTime(DateUtils.getCurrentTimeMillis());
                voice.setToUserName(message.getFromUserName());
                voice.setFromUserName(message.getToUserName());
                voice.setMediaId(config.getResponse());
//                result = wechatMessageService.sendVoiceMsg(voice);
                break;
            case WechatMessageConstant.RESP_MESSAGE_TYPE_VIDEO:
                VideoMessage video = new VideoMessage();
                video.setCreateTime(DateUtils.getCurrentTimeMillis());
                video.setToUserName(message.getFromUserName());
                video.setFromUserName(message.getToUserName());
                video.setVideo(new VideoMessage.Video(message.getMediaId()));
//                result = wechatMessageService.sendVideoMsg(video);
                break;
            default:
                result = new WechatResult();
        }
        return result;
    }


    private String toTemplate(RequestMessage message, WechatTemplateMessage template) {
        Map<String, Map<String, String>> data = new HashMap<>();
        data.put("keyword1", template.item(message.getEventKey(), null));
        data.put("keyword2", template.item(DateUtils.getDateString(DateUtils.DATE_FORMAT), null));
        data.put("keyword3", template.item(new Random().nextInt(10) + "", null));
        template.setTouser(message.getFromUserName());
//        template.setTemplateId("bFpV7w3DhSUhlZtu2FZAlijoQs97Z6DA_PpCqOqmPfQ");
//        template.setUrl("");
        template.setData(data);
        return template.toJson();
    }

}
