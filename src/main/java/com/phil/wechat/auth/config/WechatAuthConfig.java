/**
 * FileName: WechatAuthConfig
 * Author:   Phil
 * Date:     11/21/2018 7:10 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.auth.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/21/2018 7:10 PM
 * @since 1.0
 */
@Component
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "wechat.auth")
public class WechatAuthConfig {

    //获取凭证
    private String getAccessTokenUrl;
    //授权链接
    private String authorizeOauthUrl;
    //获取Oauth token
    private String getOauthTokenUrl;
    //刷新token
    private String refreshOauthTokenUrl;
    //获取授权用户信息
    private String snsUserinfoUrl;
    //判断用户accesstoken是否有效
    private String checkSnsAuthStatusUrl;
    //授权登陆链接
    private String qrConnectUrl;
    //获取jsapi-ticket
    private String getTicketUrl;

}
