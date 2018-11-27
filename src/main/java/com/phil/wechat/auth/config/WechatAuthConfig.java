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

    private String getAccessTokenUrl;

    private String authorizeOauthUrl;

    private String getOauthTokenUrl;

    private String refreshOauthTokenUrl;

    private String snsUserinfoUrl;

    private String checkSnsAuthStatusUrl;

    private String qrConnectUrl;

    private String getTicketUrl;

}
