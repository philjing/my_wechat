/**
 * FileName: WechatMessageConfig
 * Author:   Phil
 * Date:     11/21/2018 6:43 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.qrcode.config;

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
 * @create 11/21/2018 6:43 PM
 * @since 1.0
 */
@Component
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "wechat.qrcode")
public class WechatQRCodeConfig {

    private String createTicketUrl;

    private String showQrcodeUrl;

    private String shortQrcodeUrl;
}
