/**
 * FileName: WechatConfig
 * Author:   Phil
 * Date:     8/7/2018 14:02
 * Description: 微信固定配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.modules.config;

/**
 * 〈一句话功能简述〉
 * 〈微信固定配置〉
 *
 * @author Phil
 * @create 8/7/2018 14:02
 * @since 1.0.0
 */
public class WechatConfig {

    // 多媒体上传
    public static final String UPLOAD_MEDIA_TYPE_URL = "https://api.weixin.qq.com/cgi-bin/media/upload"; // ?access_token=ACCESS_TOKEN&type=TYPE
    public static final String UPLOAD_FOREVER_NEWS_MEDIA_URL = "https://api.weixin.qq.com/cgi-bin/material/add_news";
    public static final String UPLOAD_TEMP_MEDIA_TYPE_URL = "https://api.weixin.qq.com/cgi-bin/media/upload";
    public static final String UPLOAD_FOREVER_MEDIA_TYPE_URL = "https://api.weixin.qq.com/cgi-bin/material/add_material";

    //获取群发视频post中的media_id
    public static final String UPLOAD_VIDEO_MEDIA_URL = " https://api.weixin.qq.com/cgi-bin/media/uploadvideo";
    // 上传图文消息内的图片获取URL
    public static final String UPLOAD_IMG_MEDIA_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadimg";
    // 上传图文消息素材的path
    public static final String UPLOAD_NEWS_MEDIA_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadnews";

}
