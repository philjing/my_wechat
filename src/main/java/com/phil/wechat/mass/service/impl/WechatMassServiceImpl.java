/**
 * FileName: WechatMassServiceImpl
 * Author:   Phil
 * Date:     11/21/2018 11:08 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.mass.service.impl;

import com.google.gson.JsonObject;
import com.phil.modules.result.ResultState;
import com.phil.modules.util.HttpUtil;
import com.phil.modules.util.JsonUtil;
import com.phil.wechat.mass.config.WechatMassConfig;
import com.phil.wechat.mass.model.request.MassNews;
import com.phil.wechat.mass.model.request.send.MassTagData;
import com.phil.wechat.mass.model.request.send.MassUserData;
import com.phil.wechat.mass.model.response.MassMsgResult;
import com.phil.wechat.mass.model.response.MassMsgStatusResult;
import com.phil.wechat.mass.model.response.MassNewsResult;
import com.phil.wechat.mass.service.WechatMassService;
import com.phil.wechat.material.util.MaterialUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/21/2018 11:08 PM
 * @since 1.0
 */
@Service
@Slf4j
public class WechatMassServiceImpl implements WechatMassService {

    @Resource
    private WechatMassConfig wechatMassConfig;

    /**
     * 获取到图文消息素材里的thumb_media_id
     *
     * @param accessToken
     * @param type        类型
     * @param filePath    图片的本地路径
     * @return
     */
    @Override
    public MassNewsResult uploadForMassNewsFile(String accessToken, String type, String filePath) {
        Map<String, String> params = new TreeMap<>();
        params.put("access_token", accessToken);
        params.put("type", type);
        try {
            String result = MaterialUtil.uploadMediaFile(wechatMassConfig.getUploadMediaUrl(), params, filePath);
            return JsonUtil.fromJson(result, MassNewsResult.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public MassNewsResult uploadMassNews(String accessToken, MassNews entity) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        String data = JsonUtil.toJson(entity);
        String result = HttpUtil.doPost(wechatMassConfig.getUploadNewsUrl(), params, data);
        return JsonUtil.fromJson(result, MassNewsResult.class);
    }

    @Override
    public MassMsgResult sendToOpenid(String accessToken, MassUserData massData) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        // post 提交的参数
//        String data = JsonUtil.toJson(massData);
        String result = HttpUtil.doPost(wechatMassConfig.getSendMassMessageUrl(), params, massData.toJson());
        return JsonUtil.fromJson(result, MassMsgResult.class);
    }

    @Override
    public MassMsgResult sendToTag(String accessToken, MassTagData massData) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        String result = HttpUtil.doPost(wechatMassConfig.getSendallMassMessageUrl(), params, massData.toJson());
        return JsonUtil.fromJson(result, MassMsgResult.class);
    }

    @Override
    public MassMsgResult previewToOpenid(String accessToken, Map<String, String> massData) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
//        if ("text".equals(messagetype)) {
//            map.put("content", mediaId);
//        } else if ("wxcard".equals(messagetype)) {
//            map.put("wxcard", req);
//        } else {
//            map.put("media_id", mediaId);
//        }
//        map.put("msgtype", types);
        String data = JsonUtil.toJson(massData);
        String result = HttpUtil.doPost(wechatMassConfig.getPreviewMassMessageUrl(), params, data);
        return JsonUtil.fromJson(result, MassMsgResult.class);
    }

    @Override
    public ResultState deleteMassMessage(String accessToken, String msgId, int articleIdx) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        JsonObject json = new JsonObject();
        json.addProperty("msg_id", msgId);
        json.addProperty("article_idx", articleIdx);
        String result = HttpUtil.doPost(wechatMassConfig.getDeleteMassMessageUrl(), params, json.toString());
        return JsonUtil.fromJson(result, ResultState.class);
    }

    @Override
    public MassMsgStatusResult getMassMessageStatus(String accessToken, String msgId) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        JsonObject json = new JsonObject();
        json.addProperty("msg_id",msgId);
        String result = HttpUtil.doPost(wechatMassConfig.getGetMassMessageStatusUrl(), params, json.toString());
        return JsonUtil.fromJson(result, MassMsgStatusResult.class);
    }
}
