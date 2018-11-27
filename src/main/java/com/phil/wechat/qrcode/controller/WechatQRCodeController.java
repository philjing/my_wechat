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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    @Resource
    private WechatAccountConfig wechatAccountConfig;

    @Resource
    private WechatQRCodeService wechatQRCodeService;

    @Resource
    private WechatAuthService wechatAuthService;

    /**
     * 参数形式
     *
         {
         "sceneStr": null,
         "sceneId": "2"
         }
         {
         "sceneStr": str
         }
     **/
    @PostMapping("create/temp/V1")
    public Map<String, Object> createTempTicket(@RequestBody Map<String, String> params) {
        String accessToken = wechatAuthService.getAccessToken(wechatAccountConfig.getAppid(), wechatAccountConfig.getAppsecret());
        Map<String, Object> data = new HashMap<>();
        if (Objects.isNull(params.get("sceneStr")) && Objects.isNull(params.get("sceneId"))) {
            data.put("code", -1);
            data.put("msg", "参数为空");
            return data;
        }
        String url;
        String ticket;
        if (!Objects.isNull(params.get("sceneStr"))) {
            ticket = wechatQRCodeService.createTempTicket(accessToken, params.get("sceneStr"), 0);
            url = wechatQRCodeService.showQrCode(accessToken, ticket, true);
            data.put("msg", "生成临时字符串二维码成功");
        } else {
            ticket = wechatQRCodeService.createTempTicket(accessToken, params.get("sceneId"), 0);
            url = wechatQRCodeService.showQrCode(accessToken, ticket, true);
            data.put("msg", "生成临时整型二维码成功");
        }
        data.put("code", 0);
        data.put("data", url);
        return data;
    }

    /**
     * 参数形式
     *
     {
     "sceneStr": null,
     "sceneId": "2"
     }
     {
     "sceneStr": str
     }
     **/
    @PostMapping("create/forever/V1")
    public Map<String, Object> createForevericket(@RequestBody Map<String, String> params) {
        String accessToken = wechatAuthService.getAccessToken(wechatAccountConfig.getAppid(), wechatAccountConfig.getAppsecret());
        Map<String, Object> data = new HashMap<>();
        if (Objects.isNull(params.get("sceneStr")) && Objects.isNull(params.get("sceneId"))) {
            data.put("code", -1);
            data.put("msg", "参数为空");
            return data;
        }
        String url;
        String ticket;
        if (!Objects.isNull(params.get("sceneStr"))) {
            ticket = wechatQRCodeService.createForeverTicket(accessToken, params.get("sceneStr"));
            url = wechatQRCodeService.showQrCode(accessToken, ticket, true);
            data.put("msg", "生成永久字符串二维码成功");
        } else {
            ticket = wechatQRCodeService.createForeverTicket(accessToken, params.get("sceneId"));
            url = wechatQRCodeService.showQrCode(accessToken, ticket, true);
            data.put("msg", "生成永久整型二维码成功");
        }
        data.put("code", 0);
        data.put("data", url);
        return data;
    }
}
