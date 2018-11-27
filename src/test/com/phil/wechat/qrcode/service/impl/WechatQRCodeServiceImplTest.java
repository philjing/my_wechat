package com.phil.wechat.qrcode.service.impl;

import com.phil.WechatMainApplication;
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

    private String accessToken = "15_dCgtxrHuvYOfVZlEAj9IX5lQbuOy6g0UaAfMCnlBbGOAbxaZzjfz95jYZdzSVJTtK30jRdU7IkRo5MrdOOgM9ms3ZMdb3eiJIwglVcc789w54dL9EXNNjVpz9oyKsajMcORO6ORAVscnWUGeOOCdAAASSG";

    @Test
    public void createTempIntTicket() {
        int sceneId = 1001;
        String ticket = wechatQRCodeService.createTempTicket(accessToken, sceneId, 0);
        System.out.println("临时整型二维码" + wechatQRCodeService.showQrCode(accessToken, ticket, true));
    }

    @Test
    public void createTempStrTicket() {
        String sceneStr = "phil";
        String ticket = wechatQRCodeService.createTempTicket(accessToken, sceneStr, 0);
        System.out.println("临时字符串二维码" + wechatQRCodeService.showQrCode(accessToken, ticket, true));
    }

    @Test
    public void createForeverIntTicket() {
        int sceneId = 1001;
        String ticket = wechatQRCodeService.createForeverTicket(accessToken, sceneId);
        System.out.println("永久整型二维码" + wechatQRCodeService.showQrCode(accessToken, ticket, true));
    }

    @Test
    public void createForeverStrTicket() {
        String sceneStr = "phil";
        String ticket = wechatQRCodeService.createForeverTicket(accessToken, sceneStr);
        System.out.println("永久字符串二维码" + wechatQRCodeService.showQrCode(accessToken, ticket, true));
    }

    @Test
    public void showQrCode() {

    }
}