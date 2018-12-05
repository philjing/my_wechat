/**
 * FileName: WechatImportConfig
 * Author:   Phil
 * Date:     12/4/2018 4:52 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.modules.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈配置表/文件相关〉
 *
 * @author Phil
 * @create 12/4/2018 4:52 PM
 * @since 1.0
 */
@Component
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "wechat.import")
public class WechatImportConfig {

    //配置表字段  关键字、触发类型(精准、包含、正则)、回复类型(图文、文字、图片、音乐、语音、视频)、是否开启

    //关键字
    private String key; //MediaId or content

    //匹配模式
    private Integer match;

    //回复类型
    private String respType;

    //回复的内容包括mediaId、文字
    private String response;

    //回复的mediaId
//    private String mediaId;

    //是否启用
    private Integer enable;

}
