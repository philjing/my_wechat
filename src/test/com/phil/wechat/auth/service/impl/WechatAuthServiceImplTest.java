package com.phil.wechat.auth.service.impl;

import com.phil.WechatMainApplication;
import com.phil.modules.config.WechatAccountConfig;
import com.phil.wechat.auth.config.WechatAuthConfig;
import com.phil.wechat.auth.model.request.AuthCodeParam;
import com.phil.wechat.auth.service.WechatAuthService;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WechatMainApplication.class)
@ActiveProfiles("dev")
public class WechatAuthServiceImplTest {

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Autowired
    private WechatAuthConfig wechatAuthConfig;

    @Autowired
    private WechatAuthService wechatAuthService;

    @Test
    public void getAuthPath() {
        AuthCodeParam param = new AuthCodeParam();
        param.setAppid(wechatAccountConfig.getAppid());
        param.setScope(AuthCodeParam.SCOPE_SNSPAIUSERINFO);
        param.setState(DigestUtils.md5Hex(wechatAccountConfig.getAppid()));
        param.setRedirect_uri("www.4399.com/www");
        System.out.println(wechatAuthService.getAuthUrl(param, wechatAuthConfig.getAuthorizeOauthUrl()));
    }

    @Test
    public void getAuthAccessToken() {
    }

    @Test
    public void refreshAuthAccessToken() {
    }

    @Test
    public void getAuthUserInfo() {
    }

    @Test
    public void authToken() {
    }
}