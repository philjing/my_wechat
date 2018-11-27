/**
 * FileName: WechatMenuServiceImpl
 * Author:   Phil
 * Date:     11/24/2018 1:14 AM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.menu.service.impl;

import com.google.gson.JsonObject;
import com.phil.modules.result.ResultState;
import com.phil.modules.util.HttpUtil;
import com.phil.modules.util.JsonUtil;
import com.phil.wechat.menu.config.WechatMenuConfig;
import com.phil.wechat.menu.model.response.CondResult;
import com.phil.wechat.menu.service.WechatMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/24/2018 1:14 AM
 * @since 1.0
 */
@Service
@Slf4j
public class WechatMenuServiceImpl implements WechatMenuService {

    @Resource
    WechatMenuConfig wechatMenuConfig;

    @Override
    public ResultState createMenu(String accessToken) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        return null;
    }

    @Override
    public String getMenu(String accessToken) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        return null;
    }

    @Override
    public ResultState deleteMenu(String accessToken) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        String result = HttpUtil.doGet(wechatMenuConfig.getDelteUrl(), params, null);
        return JsonUtil.fromJson(result, ResultState.class);
    }

    @Override
    public CondResult addConditional(String accessToken) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        String result = HttpUtil.doGet(wechatMenuConfig.getAddconditionalUrl(), params, null);
        return JsonUtil.fromJson(result, CondResult.class);
    }

    @Override
    public ResultState deleteConditional(String accessToken) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        String result = HttpUtil.doPost(wechatMenuConfig.getDelconditionalUrl(), params, null);
        return JsonUtil.fromJson(result, ResultState.class);
    }

    @Override
    public String testConditional(String accessToken, String userId) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        JsonObject json = new JsonObject();
        json.addProperty("user_id", userId);
        return HttpUtil.doPost(wechatMenuConfig.getTestconditionalUrl(), params, json.toString());

    }

    @Override
    public String getConditionalConfig(String accessToken) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        return HttpUtil.doGet(wechatMenuConfig.getGetCurrentSelfmenuUrl(), params, null);
    }
}
