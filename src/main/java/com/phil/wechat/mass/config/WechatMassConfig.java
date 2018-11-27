/**
 * FileName: WechatMassConfig
 * Author:   Phil
 * Date:     11/21/2018 7:57 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.mass.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈群发〉
 *
 * @author Phil
 * @create 11/21/2018 7:57 PM
 * @since 1.0
 */
@Component
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "wechat.mass")
public class WechatMassConfig {

    private String uploadNewsUrl;

    private String uploadMediaUrl;

    private String uploadVideoUrl;

    private String sendallMassMessageUrl;

    private String sendMassMessageUrl;

    private String deleteMassMessageUrl;

    private String previewMassMessageUrl;

    private String getMassMessageStatusUrl;

    private String setSpeedMassMessageUrl;

}
