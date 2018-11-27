/**
 * FileName: WechatTemplateService
 * Author:   Phil
 * Date:     11/21/2018 1:56 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.message.service;

import com.phil.wechat.message.model.template.response.TemplateMessageResult;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Phil
 * @create 11/21/2018 1:56 PM
 * @since 1.0
 */
public interface WechatTemplateMessageService {

    /**
     * 发送模板消息
     *
     * @param accessToken
     * @param data
     * @return
     */
    TemplateMessageResult sendTemplate(String accessToken, String data);

}
