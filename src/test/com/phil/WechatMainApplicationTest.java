package com.phil;

import com.phil.wechat.auth.config.WechatAuthConfig;
import com.phil.wechat.mass.config.WechatMassConfig;
import com.phil.wechat.material.config.WechatMaterialConfig;
import com.phil.wechat.menu.config.WechatMenuConfig;
import com.phil.wechat.message.config.WechatMessageConfig;
import com.phil.wechat.pay.config.WechatPayConfig;
import com.phil.wechat.qrcode.config.WechatQRCodeConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("prod")
public class WechatMainApplicationTest {

//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    WechatMessageConfig wechatMessageConfig;

    @Autowired
    WechatQRCodeConfig wechatQRCodeConfig;

    @Autowired
    WechatAuthConfig wechatAuthConfig;

    @Autowired
    WechatMenuConfig wechatMenuConfig;

    @Autowired
    WechatMassConfig wechatMassConfig;

    @Autowired
    WechatPayConfig wechatPayConfig;

    @Autowired
    private WechatMaterialConfig wechatMaterialConfig;

    @Test
    public void contextLoad() {
//        stringRedisTemplate.opsForValue().set("phil", "phil");
//        System.out.println(stringRedisTemplate.opsForValue().get("phil"));
        System.out.println(wechatMessageConfig);
        System.out.println(wechatQRCodeConfig);
        System.out.println(wechatAuthConfig);
        System.out.println(wechatMenuConfig);
        System.out.println(wechatMassConfig);
        System.out.println(wechatPayConfig);
        System.out.println(wechatMaterialConfig);
    }

}