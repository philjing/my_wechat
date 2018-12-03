package com.phil.wechat.qrcode.service.impl;

import com.phil.WechatMainApplication;
import com.phil.wechat.auth.service.WechatAuthService;
import com.phil.wechat.qrcode.service.WechatQRCodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WechatMainApplication.class)
@ActiveProfiles("dev")
public class WechatQRCodeServiceImplTest {

    @Autowired
    private WechatQRCodeService wechatQRCodeService;

    @Autowired
    private WechatAuthService wechatAuthService;

    @Test
    public void createTempIntTicket() {
        String accessToken = wechatAuthService.getAccessToken();
        int sceneId = 1001;
        String ticket = wechatQRCodeService.createTempTicket(accessToken, sceneId, 600);
        System.out.println("临时整型二维码" + wechatQRCodeService.showQrCode(accessToken, ticket, true));
        //临时整型二维码https://w.url.cn/s/AaUQBgt
    }

    @Test
    public void createTempStrTicket() {
        String accessToken = wechatAuthService.getAccessToken();
        String sceneStr = "phil";
        String ticket = wechatQRCodeService.createTempTicket(accessToken, sceneStr, 600);
        System.out.println("临时字符串二维码" + wechatQRCodeService.showQrCode(accessToken, ticket, true));
        //临时字符串二维码https://w.url.cn/s/AEyD82H
    }

    @Test
    public void createForeverIntTicket() {
        String accessToken = wechatAuthService.getAccessToken();
        int sceneId = 1001;
        String ticket = wechatQRCodeService.createForeverTicket(accessToken, sceneId);
        System.out.println("永久整型二维码" + wechatQRCodeService.showQrCode(accessToken, ticket, true));
        //永久整型二维码https://w.url.cn/s/A4nbwZe
    }

    @Test
    public void createForeverStrTicket() {
        String accessToken = wechatAuthService.getAccessToken();
        String sceneStr = "phil";
        String ticket = wechatQRCodeService.createForeverTicket(accessToken, sceneStr);
        System.out.println("永久字符串二维码" + wechatQRCodeService.showQrCode(accessToken, ticket, true));
        //永久字符串二维码https://w.url.cn/s/AMbBCNx
    }
}