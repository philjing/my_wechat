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
import com.phil.modules.util.SignatureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    public String processMsg(HttpServletRequest request) {
//        // 调用核心服务类接收处理请求
        return "";
    }
}
