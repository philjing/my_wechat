/**
 * FileName: AuthServiceImpl
 * Author:   Phil
 * Date:     11/21/2018 12:13 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.auth.service.impl;

import com.google.gson.JsonSyntaxException;
import com.phil.modules.config.WechatAccountConfig;
import com.phil.modules.result.ResultState;
import com.phil.modules.util.HttpUtil;
import com.phil.modules.util.JsonUtil;
import com.phil.modules.util.RedisUtils;
import com.phil.wechat.auth.config.WechatAuthConfig;
import com.phil.wechat.auth.model.BasicAuthParam;
import com.phil.wechat.auth.model.response.AccessToken;
import com.phil.wechat.auth.model.response.AuthAccessToken;
import com.phil.wechat.auth.model.response.AuthUserInfo;
import com.phil.wechat.auth.service.WechatAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/21/2018 12:13 PM
 * @since 1.0
 */
@Service
@Slf4j
public class WechatAuthServiceImpl implements WechatAuthService {

    @Resource
    RedisUtils redisUtils;

    @Resource
    WechatAuthConfig wechatAuthConfig;

    @Resource
    private WechatAccountConfig wechatAccountConfig;

    /**
     * 获取授权凭证token
     *
     * @return 授权凭证token
     */
    @Override
    public String getAccessToken() {
        String accessToken = null;
        if (Objects.isNull(redisUtils.get(wechatAccountConfig.getToken()))) {
            Map<String, String> map = new TreeMap<>();
            map.put("appid", wechatAccountConfig.getAppid());
            map.put("secret", wechatAccountConfig.getAppsecret());
            map.put("grant_type", "client_credential");
            String json = HttpUtil.doGet(wechatAuthConfig.getGetAccessTokenUrl(), map);
            AccessToken bean = JsonUtil.fromJson(json, AccessToken.class);
            if (bean != null) {
                accessToken = bean.getAccessToken();
                log.info("从微信服务器获取的授权凭证{}", accessToken);
                redisUtils.set(wechatAccountConfig.getToken(), accessToken, 60 * 120);
                log.info("从微信服务器获取的token缓存到Redis");
            }
        } else {
            accessToken = redisUtils.get(wechatAccountConfig.getToken()).toString();
            log.info("从redis中获取的授权凭证{}", accessToken);
        }
        return accessToken;
    }

    /**
     * 获取授权请求url
     *
     * @param basic
     * @param url
     * @return
     * @throws Exception
     */
    @Override
    public String getAuthUrl(BasicAuthParam basic, String url) {
        Map<String, String> params = basic.getParams();
        try {
            return HttpUtil.setParmas(url, params, "") + "#wechat_redirect";
        } catch (Exception e) {
            log.debug("error" + e.getMessage());
        }
        return null;
    }

    /**
     * 获取网页授权凭证
     *
     * @param basic
     * @param url
     * @return
     */
    @Override
    public AuthAccessToken getAuthAccessToken(BasicAuthParam basic, String url) {
        try {
            String result = HttpUtil.doGet(wechatAuthConfig.getGetOauthTokenUrl(), basic.getParams());
            return JsonUtil.fromJson(result, AuthAccessToken.class);
        } catch (Exception e) {
            log.debug("error" + e.getMessage());
        }
        return null;
    }

    /**
     * 刷新网页授权验证
     *
     * @param basic 参数
     * @param url   请求路径
     * @return 新的网页授权验证
     */
    @Override
    public AuthAccessToken refreshAuthAccessToken(BasicAuthParam basic, String url) {
        try {
            String result = HttpUtil.doGet(wechatAuthConfig.getRefreshOauthTokenUrl(), basic.getParams());
            return JsonUtil.fromJson(result, AuthAccessToken.class);
        } catch (Exception e) {
            log.debug("error" + e.getMessage());
        }
        return null;
    }

    /**
     * 通过网页授权获取用户信息
     *
     * @param accessToken 网页授权接口调用凭证
     * @param openid      用户唯一标识
     * @return 用户信息
     */
    @Override
    public AuthUserInfo getAuthUserInfo(String accessToken, String openid) {
        // 通过网页授权获取用户信息
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        params.put("openid", openid);
        String result = HttpUtil.doGet(wechatAuthConfig.getSnsUserinfoUrl(), params);
        try {
            return JsonUtil.fromJson(result, AuthUserInfo.class);
        } catch (JsonSyntaxException e) {
            log.debug("transfer exception");
        }
        return null;
    }

    /**
     * 检验授权凭证（access_token）是否有效
     *
     * @param accessToken 网页授权接口调用凭证
     * @param openid      用户唯一标识
     * @return { "errcode":0,"errmsg":"ok"}表示成功 { "errcode":40003,"errmsg":"invalid
     * openid"}失败
     */
    @Override
    public ResultState authToken(String accessToken, String openid) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        params.put("openid", openid);
        String jsonResult = HttpUtil.doGet(
                wechatAuthConfig.getCheckSnsAuthStatusUrl(), params);
        return JsonUtil.fromJson(jsonResult, ResultState.class);
    }
}
