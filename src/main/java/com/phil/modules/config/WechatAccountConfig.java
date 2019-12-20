/**
 * FileName: WechatAccountConfig
 * Author:   Phil
 * Date:     11/20/2018 3:10 PM
 * Description: 配置文件
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.modules.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈接入配置信息〉
 *
 * @author Phil
 * @create 11/20/2018 3:10 PM
 * @since 1.0
 */
@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "wechat.account")
public class WechatAccountConfig {

    /***
     * 微信的appid
     */
    private String appid;
    /***
     * 微信的secret
     */
    private String appsecret;
    /***
     * 微信授权url
     */
    private String returnUrl;
    /***
     * 微信的验证token
     */
    private String token;
}
