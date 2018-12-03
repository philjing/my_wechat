package com.phil.wechat.mass.service.impl;

import com.google.gson.JsonObject;
import com.phil.WechatMainApplication;
import com.phil.wechat.mass.model.request.MassNews;
import com.phil.wechat.mass.model.request.send.MassUserImageData;
import com.phil.wechat.mass.model.request.send.MassUserMpnewsData;
import com.phil.wechat.mass.model.request.send.MassUserTextData;
import com.phil.wechat.mass.model.request.send.MassUserVoiceData;
import com.phil.wechat.mass.model.request.type.MassImageType;
import com.phil.wechat.mass.model.request.type.MassMpnewsType;
import com.phil.wechat.mass.model.request.type.MassTextType;
import com.phil.wechat.mass.model.request.type.MassVoiceType;
import com.phil.wechat.mass.service.WechatMassService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WechatMainApplication.class)
@ActiveProfiles("dev")
public class WechatMassServiceImplTest {

    @Resource
    private WechatMassService wechatMassService;

    private String accessToken = "15_wR_uy3F8vWem6JfB5uG6wvJo2C2Ok8I9D-i39kkaTP78D98O3hl-nPIiFhu-ylU0VDXWHBQmY_3gz4K8-Em7Ub3c5KXZj_W9cbXZT0D-NzA3uaVrc61QeOgjfKFeEm62cMG0IcCtPhFSkrGTHCEbAHAAVX";

    @Test
    public void uploadForMassNewsFile() {
        String filePath = "C:/Users/Phil/Desktop/820084947_副本2.jpg";
        System.out.println(wechatMassService.uploadForMassNewsFile(accessToken, "image", filePath));
    }

    @Test
    public void uploadMassNews() {
        MassNews massNews = new MassNews();
        String filePath1 = "C:/Users/Phil/Desktop/1034851200_副本_副本.jpg";
        String mediaId1 = wechatMassService.uploadForMassNewsFile(accessToken, "image", filePath1);

        MassNews.MassNewsArticle article1 = new MassNews.MassNewsArticle();
        article1.setAuthor("phil");
        article1.setContent("Hello");
        article1.setContentSourceUrl("");
        article1.setThumbMediaId(mediaId1);
        massNews.addArticle(article1);

        String filePath2 = "C:/Users/Phil/Desktop/23213.jpg";
        String mediaId2 = wechatMassService.uploadForMassNewsFile(accessToken, "image", filePath2);

        MassNews.MassNewsArticle article2 = new MassNews.MassNewsArticle();
        article2.setAuthor("phil");
        article2.setContent("Hello");
        article2.setContentSourceUrl("");
        article2.setThumbMediaId(mediaId2);
        massNews.addArticle(article2);
        System.out.println(wechatMassService.uploadMassNews(accessToken, massNews));
    }

    @Test
    public void sendNewsToOpenid() {
        String mediaId = "sdPeuaes30dsGRr-BJ1Ipnw-ntOCXJfYkui1weY-GWp4q5Ejy_uyjrs3tQUYYR83";
        MassUserMpnewsData data = new MassUserMpnewsData();
        data.addOpenid("ovHQ5v6CW3INkWUsCl3olODif0cc");
        data.addOpenid("ovHQ5v_btX6f7dtbTitqCOyC4aBw");
        data.addType(new MassMpnewsType(mediaId));
        System.out.println(wechatMassService.sendToOpenid(accessToken, data));
    }

    @Test
    public void sendTextToOpenid() {
        String content = "测试根据Openid群发文字消息" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        MassUserTextData data = new MassUserTextData();
        data.addOpenid("ovHQ5v6CW3INkWUsCl3olODif0cc");
        data.addOpenid("ovHQ5v_btX6f7dtbTitqCOyC4aBw");
        data.addType(new MassTextType(content));
        System.out.println(wechatMassService.sendToOpenid(accessToken, data));
    }

    @Test
    public void sendVocieToOpenid() {
        String mediaId = "";
        MassUserVoiceData data = new MassUserVoiceData();
        data.addOpenid("ovHQ5v6CW3INkWUsCl3olODif0cc");
        data.addOpenid("ovHQ5v_btX6f7dtbTitqCOyC4aBw");
        data.addType(new MassVoiceType(mediaId));
        System.out.println(wechatMassService.sendToOpenid(accessToken, data));
    }

    @Test
    public void sendImageToOpenid() {
        String mediaId = "nLh_e0YCYBBHzPKWDm8St4vpuRRjMyVsM4QZsRmhlbnm6f4IKTigVydCOB07wO30";
        MassUserImageData data = new MassUserImageData();
        data.addOpenid("ovHQ5v6CW3INkWUsCl3olODif0cc");
        data.addOpenid("ovHQ5v_btX6f7dtbTitqCOyC4aBw");
        data.addType(new MassImageType(mediaId));
        System.out.println(wechatMassService.sendToOpenid(accessToken, data));
    }

    @Test
    public void sendVideoToOpenid() {
    }

    @Test
    public void sendWxcardToOpenid() {
    }

    @Test
    public void previewToOpenid() {
        String mediaId = "nLh_e0YCYBBHzPKWDm8St4vpuRRjMyVsM4QZsRmhlbnm6f4IKTigVydCOB07wO30";
        Map<String, String> map = new HashMap<>();
        map.put("touser", "ovHQ5v6CW3INkWUsCl3olODif0cc");
        map.put("media_id", mediaId);
        map.put("msgtype", "image");
        System.out.println(wechatMassService.previewToOpenid(accessToken, map));
    }

    @Test
    public void deleteMassMessage() {


    }

    @Test
    public void getMassMessageStatus() {
        JsonObject json = new JsonObject();
        json.addProperty("msg_id", "232132131");
        System.out.println(json.toString());
    }
}