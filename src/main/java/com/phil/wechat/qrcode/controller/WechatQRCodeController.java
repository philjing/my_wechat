/**
 * FileName: WechatQRCodeController
 * Author:   Phil
 * Date:     11/21/2018 5:11 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.qrcode.controller;

import com.phil.modules.config.WechatAccountConfig;
import com.phil.wechat.auth.service.WechatAuthService;
import com.phil.wechat.qrcode.service.WechatQRCodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/21/2018 5:11 PM
 * @since 1.0
 */
@RestController
@RequestMapping("api/qrcode")
public class WechatQRCodeController {

    private final WechatAccountConfig wechatAccountConfig;

    private final WechatQRCodeService wechatQRCodeService;

    private final WechatAuthService wechatAuthService;

    @Autowired
    public WechatQRCodeController(WechatAccountConfig wechatAccountConfig, WechatQRCodeService wechatQRCodeService, WechatAuthService wechatAuthService) {
        this.wechatAccountConfig = wechatAccountConfig;
        this.wechatQRCodeService = wechatQRCodeService;
        this.wechatAuthService = wechatAuthService;
    }

    /** 参数传入对象**/
    @PostMapping("create/temp/V1")
    public Map<String, Object> createTempTicket(@RequestBody(required = false) String sceneStr, @RequestBody(required = false) Integer sceneId) {
        String result = wechatAuthService.getAccessToken(wechatAccountConfig.getAppid(), wechatAccountConfig.getAppsecret());
        Map<String, Object> data = new HashMap<>();
        if (StringUtils.isBlank(sceneStr) && sceneId == null) {
            data.put("code", -1);
            data.put("msg", "参数为空");
            return data;
        }
        String url;
        String ticket;
        if (StringUtils.isNotBlank(sceneStr)) {
            ticket = wechatQRCodeService.createTempTicket(result, sceneStr, 0);
            url = wechatQRCodeService.showQrCode(result, ticket, true);
            data.put("msg", "生成临时字符串二维码成功");
        } else {
            ticket = wechatQRCodeService.createTempTicket(result, sceneId, 0);
            url = wechatQRCodeService.showQrCode(result, ticket, true);
            data.put("msg", "生成临时整型二维码成功");
        }
        data.put("code", 0);
        data.put("data", url);
        return data;
    }
}
