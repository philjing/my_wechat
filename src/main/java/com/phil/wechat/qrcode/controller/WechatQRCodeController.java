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

import com.phil.modules.util.HttpUtil;
import com.phil.wechat.auth.service.WechatAuthService;
import com.phil.wechat.qrcode.service.WechatQRCodeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
@Slf4j
public class WechatQRCodeController {

    @Resource
    private WechatQRCodeService wechatQRCodeService;

    @Resource
    private WechatAuthService wechatAuthService;

    /**
     * 参数形式
     * <p>
     * {
     * "sceneStr": null,
     * "sceneId": "2"
     * }
     * <p>
     * OR
     * {
     * "sceneStr": str
     * }
     **/
    @PostMapping("create/temp/V1")
    public Map<String, Object> createTempTicket(@RequestBody Map<String, String> params) {
        String accessToken = wechatAuthService.getAccessToken();
        Map<String, Object> data = new HashMap<>();
        if (Objects.isNull(params.get("sceneStr")) && Objects.isNull(params.get("sceneId"))) {
            data.put("code", -1);
            data.put("msg", "参数为空");
            return data;
        }
        String ticket;
        if (!Objects.isNull(params.get("sceneStr"))) {
            ticket = wechatQRCodeService.createTempTicket(accessToken, params.get("sceneStr"), 0);
            data.put("msg", "生成临时字符串二维码成功");
        } else {
            ticket = wechatQRCodeService.createTempTicket(accessToken, Integer.valueOf(params.get("sceneId")), 0);
            data.put("msg", "生成临时整型二维码成功");
        }
        String url = wechatQRCodeService.showQrCode(accessToken, ticket, true);
        data.put("code", 0);
        data.put("data", url);
        return data;
    }

    /**
     * 参数形式
     * <p>
     * {
     * "sceneStr": null,
     * "sceneId": "2"
     * }
     * {
     * "sceneStr": str
     * }
     **/
    @PostMapping("create/forever/V1")
    public Map<String, Object> createForevericket(@RequestBody Map<String, String> params) {
        String accessToken = wechatAuthService.getAccessToken();
        Map<String, Object> data = new HashMap<>();
        if (Objects.isNull(params.get("sceneStr")) && Objects.isNull(params.get("sceneId"))) {
            data.put("code", -1);
            data.put("msg", "参数为空");
            return data;
        }
        String ticket;
        if (!Objects.isNull(params.get("sceneStr"))) {
            ticket = wechatQRCodeService.createTempTicket(accessToken, params.get("sceneStr"), 0);
            data.put("msg", "生成永久字符串二维码成功");
        } else {
            ticket = wechatQRCodeService.createTempTicket(accessToken, Integer.valueOf(params.get("sceneId")), 0);
            data.put("msg", "生成永久整型二维码成功");
        }
        String url = wechatQRCodeService.showQrCode(accessToken, ticket, true);
        data.put("code", 0);
        data.put("data", url);
        return data;
    }

    /**
     * 文件流形式(示例)
     * 描述:通过文印传来的令牌,项目设定,打印设备信息作为参数,在公众号生成带参二维码
     */
    @PostMapping(value = "createDevQRCode/{TokenId}/{ProjectId}/{DeviceId}")
    public void createDevQRCode(@PathVariable("TokenId") String tokenId,
                                @PathVariable("ProjectId") Integer projectId, @PathVariable("DeviceId") String deviceId, HttpServletResponse response) throws Exception {
        if (StringUtils.isBlank(tokenId) || projectId == null || StringUtils.isBlank(deviceId)) {
            throw new Exception("自定义报错");
        }
        String accessToken = wechatAuthService.getAccessToken();
        StringBuffer sceneStr = new StringBuffer();
        sceneStr.append(tokenId).append(DigestUtils.md5Hex(projectId.toString())).append(deviceId);
        String ticket = wechatQRCodeService.createForeverTicket(accessToken, sceneStr.toString());
        String url = wechatQRCodeService.showQrCode(accessToken, ticket, true);
        HttpUtil.toOutput(url, response.getOutputStream());
    }
}
