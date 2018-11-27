/**
 * FileName: WechatPayConfig
 * Author:   Phil
 * Date:     11/21/2018 7:21 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.pay.config;

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
 * @create 11/21/2018 7:21 PM
 * @since 1.0
 */
@Component
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "wechat.pay")
public class WechatPayConfig {

    private String mchid;

    private String apikey;

    private String notifyUrl;

    private String unifiedOrderUrl;

    private String refundUrl;

    private String checkOrderUrl;

    private String closeOrderUrl;

    private String queryRefundUrl;

    private String downloadBillUrl;

    private String payShortUrl;

    private String reportUrl;

}
