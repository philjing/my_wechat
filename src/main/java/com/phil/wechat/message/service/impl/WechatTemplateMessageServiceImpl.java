/**
 * FileName: WechatTemplateMessageService
 * Author:   Phil
 * Date:     11/21/2018 1:57 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.message.service.impl;

import com.phil.wechat.message.config.WechatMessageConfig;
import com.phil.modules.util.HttpUtil;
import com.phil.modules.util.JsonUtil;
import com.phil.wechat.message.model.template.response.TemplateMessageResult;
import com.phil.wechat.message.service.WechatTemplateMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/21/2018 1:57 PM
 * @since 1.0
 */
@Service
@Slf4j
public class WechatTemplateMessageServiceImpl implements WechatTemplateMessageService {

    private final WechatMessageConfig wechatMessageConfig;

    @Autowired
    public WechatTemplateMessageServiceImpl(WechatMessageConfig wechatMessageConfig) {
        this.wechatMessageConfig = wechatMessageConfig;
    }

    @Override
    public TemplateMessageResult sendTemplate(String accessToken, String data) {
        TreeMap<String, String> params = new TreeMap<>();
        params.put("access_token", accessToken);
        String result = HttpUtil.doPost(wechatMessageConfig.getSendTemplateMessageUrl(),
                params, data);
        log.info(result);
        return JsonUtil.fromJson(result, TemplateMessageResult.class);
    }
}
