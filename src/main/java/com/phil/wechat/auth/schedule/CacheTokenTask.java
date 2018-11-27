/**
 * FileName: CacheTokenTask
 * Author:   Phil
 * Date:     11/22/2018 5:24 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.auth.schedule;

import com.phil.modules.cache.RedisUtils;
import com.phil.modules.config.WechatAccountConfig;
import com.phil.wechat.auth.service.WechatAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/22/2018 5:24 PM
 * @since 1.0
 */
@Component
@Slf4j
public class CacheTokenTask {

    @Value("${wechat.token}")
    private String token;

    @Resource
    WechatAccountConfig wechatAccountConfig;

    @Resource
    WechatAuthService wechatAuthService;

    @Resource
    RedisUtils redisUtils;

    @Scheduled(fixedRate = 1000 * 60 * 90, initialDelay = 2000)//项目启动2秒中之后执行一次，然后每90min执行一次，单位都为ms
    public void getToken() {
        String access_token = wechatAuthService.getAccessToken(wechatAccountConfig.getAppid(), wechatAccountConfig.getAppsecret());
        log.info("从微信服务器获取的token======" + access_token);
        redisUtils.set(token, access_token, 60 * 120);
        log.info("从redis中获取的token === " + redisUtils.get(token).toString());
    }

}
