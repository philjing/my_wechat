/**
 * FileName: WechatMenuConfig
 * Author:   Phil
 * Date:     11/21/2018 7:43 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.menu.config;

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
 * @create 11/21/2018 7:43 PM
 * @since 1.0
 */
@Component
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "wechat.menu")
public class WechatMenuConfig {

    private String createUrl;

    private String getUrl;

    private String delteUrl;

    private String addconditionalUrl;

    private String delconditionalUrl;

    private String testconditionalUrl;

    private String getCurrentSelfmenuUrl;
}
