package com.phil.wechat.message.service.impl;

import com.phil.WechatMainApplication;
import com.phil.modules.config.WechatAccountConfig;
import com.phil.modules.util.JsonUtil;
import com.phil.wechat.auth.service.WechatAuthService;
import com.phil.wechat.message.model.template.request.WechatTemplateMessage;
import com.phil.wechat.message.service.WechatTemplateMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.TreeMap;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WechatMainApplication.class)
@ActiveProfiles("dev")
public class WechatTemplateMessageServiceImplTest {

    @Autowired
    private WechatAccountConfig wechatAccountConfig;


    @Autowired
    private WechatAuthService wechatAuthService;

    @Autowired
    private WechatTemplateMessageService wechatTemplateMessageService;

    @Test
    public void testSendTemplate() {
        String result = wechatAuthService.getAccessToken(wechatAccountConfig.getAppid(), wechatAccountConfig.getAppsecret());
        TreeMap<String, TreeMap<String, String>> params = new TreeMap<>();
        // 根据具体模板参数组装
        params.put("first", WechatTemplateMessage.item("您的户外旅行活动订单已经支付完成，可在我的个人中心中查看", "#000000"));
        params.put("keyword1", WechatTemplateMessage.item("8.1发现尼泊尔—人文与自然的旅行圣地", "#000000"));
        params.put("keyword2", WechatTemplateMessage.item("5000元", "#000000"));
        params.put("keyword3", WechatTemplateMessage.item("2017.1.2", "#000000"));
        params.put("keyword4", WechatTemplateMessage.item("5", "#000000"));
        params.put("remark", WechatTemplateMessage.item("请届时携带好身份证件准时到达集合地点，若临时退改将产生相应损失，敬请谅解,谢谢！", "#000000"));
        WechatTemplateMessage wechatTemplateMsg = new WechatTemplateMessage();
        wechatTemplateMsg.setTemplateId("Ub2oYYFPf8ofmA17H31Zqu9Z_HLycZ7MC-Dx_Se1Nkw");
        wechatTemplateMsg.setTouser("ovHQ5v6CW3INkWUsCl3olODif0cc");
        wechatTemplateMsg.setUrl("http://music.163.com/#/song?id=27867140");
        wechatTemplateMsg.setData(params);
        String data = JsonUtil.toJson(wechatTemplateMsg);
        System.out.println(wechatTemplateMessageService.sendTemplate(result,data));
    }


}