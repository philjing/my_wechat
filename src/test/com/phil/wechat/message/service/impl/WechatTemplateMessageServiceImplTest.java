package com.phil.wechat.message.service.impl;

import com.phil.WechatMainApplication;
import com.phil.wechat.auth.service.WechatAuthService;
import com.phil.wechat.message.model.template.request.WechatTemplateMessage;
import com.phil.wechat.message.service.WechatTemplateMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WechatMainApplication.class)
@ActiveProfiles("dev")
public class WechatTemplateMessageServiceImplTest {

    @Autowired
    private WechatAuthService wechatAuthService;

    @Autowired
    private WechatTemplateMessageService wechatTemplateMessageService;

    @Test
    public void testSendTemplate() {
        String accessToken = wechatAuthService.getAccessToken();
        Map<String, Map<String, String>> params = new HashMap<>();
        WechatTemplateMessage template = new WechatTemplateMessage();
        params.put("keyword1", template.item("8.1发现尼泊尔—人文与自然的旅行圣地", null));
        params.put("keyword2", template.item("5000元", null));
        params.put("keyword3", template.item("2017.1.2", null));
        params.put("keyword4", template.item("5", null));
        template.setTemplateId("Ub2oYYFPf8ofmA17H31Zqu9Z_HLycZ7MC-Dx_Se1Nkw");
        template.setTouser("ovHQ5v6CW3INkWUsCl3olODif0cc");
        template.setUrl("http://music.163.com/#/song?id=27867140");
        template.setData(params);
        System.out.println(wechatTemplateMessageService.sendTemplate(accessToken, template.toJson()));
        //ResultState(errcode=0, errmsg=ok)
    }
}