/**
 * FileName: WechatQRCodeServiceImpl
 * Author:   Phil
 * Date:     11/21/2018 12:17 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.qrcode.service.impl;

import com.google.gson.JsonObject;
import com.phil.modules.util.EncodeUtils;
import com.phil.modules.util.HttpUtil;
import com.phil.modules.util.JsonUtil;
import com.phil.wechat.qrcode.config.WechatQRCodeConfig;
import com.phil.wechat.qrcode.constant.QRCodeConstant;
import com.phil.wechat.qrcode.model.WechatQRCode;
import com.phil.wechat.qrcode.model.WechatQRCodeShortUrl;
import com.phil.wechat.qrcode.service.WechatQRCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/21/2018 12:17 PM
 * @since 1.0
 */
@Service
@Slf4j
public class WechatQRCodeServiceImpl implements WechatQRCodeService {

    private final WechatQRCodeConfig WechatQRCodeConfig;

    @Autowired
    public WechatQRCodeServiceImpl(WechatQRCodeConfig WechatQRCodeConfig) {
        this.WechatQRCodeConfig = WechatQRCodeConfig;
    }

    /**
     * 创建临时带参数二维码
     *
     * @param accessToken
     * @param sceneId     场景Id
     * @return
     * @expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     */
    @Override
    public String createTempTicket(String accessToken, int sceneId, int expireSeconds) {
        TreeMap<String, String> params = new TreeMap<>();
        params.put("access_token", accessToken);
        // output data
        JsonObject data = new JsonObject();
        data.addProperty("action_name", QRCodeConstant.QR_SCENE);
        data.addProperty("expire_seconds", expireSeconds);
        JsonObject scene = new JsonObject();
        scene.addProperty("scene_id", sceneId);
        JsonObject actionInfo = new JsonObject();
        actionInfo.add("scene", scene);
        data.add("action_info", actionInfo);
        String result = HttpUtil.doPost(WechatQRCodeConfig.getCreateTicketUrl(), params, data.toString());
        WechatQRCode wechatQRCode = JsonUtil.fromJson(result, WechatQRCode.class);
        return wechatQRCode == null ? null : wechatQRCode.getTicket();
    }

    /**
     * 创建临时带参数二维码(字符串)
     *
     * @param accessToken
     * @param sceneStr    场景Id
     * @return
     * @expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     */
    @Override
    public String createTempTicket(String accessToken, String sceneStr, int expireSeconds) {
        TreeMap<String, String> params = new TreeMap<>();
        params.put("access_token", accessToken);
        // output data
        JsonObject data = new JsonObject();
        data.addProperty("action_name", QRCodeConstant.QR_STR_SCENE);
        data.addProperty("expire_seconds", expireSeconds);
        JsonObject scene = new JsonObject();
        scene.addProperty("scene_str", sceneStr);
        JsonObject actionInfo = new JsonObject();
        actionInfo.add("scene", scene);
        data.add("action_info", actionInfo);
        String result = HttpUtil.doPost(WechatQRCodeConfig.getCreateTicketUrl(), params, data.toString());
        WechatQRCode wechatQRCode = JsonUtil.fromJson(result, WechatQRCode.class);
        return wechatQRCode == null ? null : wechatQRCode.getTicket();
    }

    /**
     * 创建永久二维码(数字)
     *
     * @param accessToken
     * @param sceneId     场景Id
     * @return
     */
    @Override
    public String createForeverTicket(String accessToken, int sceneId) {
        TreeMap<String, String> params = new TreeMap<>();
        params.put("access_token", accessToken);
        // output data
        JsonObject data = new JsonObject();
        data.addProperty("action_name", QRCodeConstant.QR_LIMIT_SCENE);
        JsonObject scene = new JsonObject();
        scene.addProperty("scene_id", sceneId);
        JsonObject actionInfo = new JsonObject();
        actionInfo.add("scene", scene);
        data.add("action_info", actionInfo);
        String result = HttpUtil.doPost(WechatQRCodeConfig.getCreateTicketUrl(), params, data.toString());
        WechatQRCode wechatQRCode = JsonUtil.fromJson(result, WechatQRCode.class);
        return wechatQRCode == null ? null : wechatQRCode.getTicket();
    }

    /**
     * 创建永久二维码(字符串)
     *
     * @param accessToken
     * @param sceneStr    场景str
     * @return
     */
    @Override
    public String createForeverTicket(String accessToken, String sceneStr) {
        TreeMap<String, String> params = new TreeMap<>();
        params.put("access_token", accessToken);
        // output data
        JsonObject data = new JsonObject();
        data.addProperty("action_name", "QR_LIMIT_STR_SCENE");
        JsonObject actionInfo = new JsonObject();
        JsonObject scene = new JsonObject();
        scene.addProperty("scene_str", sceneStr);
        actionInfo.add("scene", scene);
        data.add("action_info", actionInfo);
        String result = HttpUtil.doPost(WechatQRCodeConfig.getCreateTicketUrl(), params, data.toString());
        WechatQRCode wechatQRCode = JsonUtil.fromJson(result, WechatQRCode.class);
        return wechatQRCode == null ? null : wechatQRCode.getTicket();
    }

    /**
     * 获取二维码ticket后，通过ticket换取二维码图片展示
     *
     * @param accessToken
     * @param ticket
     * @param isShortUrl  是否需要展示
     * @return
     */
    @Override
    public String showQrCode(String accessToken, String ticket, boolean isShortUrl) {
        String url = String.format(WechatQRCodeConfig.getShowQrcodeUrl(), EncodeUtils.urlEncode(ticket));
        if (isShortUrl) {
            return toShortQRCodeurl(accessToken, url);
        }
        return url;
    }

    /**
     * 长链接转短链接
     *
     * @param accessToken
     * @param longUrl     长链接
     * @return
     */
    private String toShortQRCodeurl(String accessToken, String longUrl) {
        TreeMap<String, String> params = new TreeMap<>();
        params.put("access_token", accessToken);
        JsonObject data = new JsonObject();
        data.addProperty("action", QRCodeConstant.LONG_TO_SHORT);
        data.addProperty("long_url", longUrl);
        String result = HttpUtil.doPost(WechatQRCodeConfig.getShortQrcodeUrl(),
                params, data.toString());
        WechatQRCodeShortUrl wechatQRCodeShortUrl = JsonUtil.fromJson(result, WechatQRCodeShortUrl.class);
        return wechatQRCodeShortUrl == null ? null : wechatQRCodeShortUrl.getShortUrl();
    }

}
