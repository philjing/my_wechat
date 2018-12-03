/**
 * FileName: WechatMaterialConfig
 * Author:   Phil
 * Date:     11/30/2018 4:44 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.material.config;

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
 * @create 11/30/2018 4:44 PM
 * @since 1.0
 */
@Component
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "wechat.material")
public class WechatMaterialConfig {

    //新增临时素材
    private String uploadTempMediaUrl;

    //获取临时素材
    private String getTempMediaUrl;

    //新增永久图文素材
    private String addNewsUrl;

    //上传图文消息内的图片获取URL
    private String uploadimgMediaUrl;

    //新增其他类型永久素材
    private String uploadForeverMediaUrl;

    //获取永久素材
    private String getMaterialUrl;

    //删除永久素材
    private String deleteMaterialUrl;

    //获取素材总数
    private String getMaterialcountUrl;

}
