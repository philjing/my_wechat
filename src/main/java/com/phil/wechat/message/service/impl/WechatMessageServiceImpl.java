/**
 * FileName: WechatMessageServiceImpl
 * Author:   Phil
 * Date:     11/21/2018 1:36 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.message.service.impl;


import com.phil.modules.result.WechatResult;
import com.phil.modules.util.DateUtils;
import com.phil.wechat.message.model.basic.request.RequestMessage;
import com.phil.wechat.message.model.basic.response.TextMessage;
import com.phil.wechat.message.service.WechatMessageService;
import com.phil.wechat.message.service.WechatTemplateMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/21/2018 1:36 PM
 * @since 1.0
 */
@Service
@Slf4j
public class WechatMessageServiceImpl implements WechatMessageService {

    @Resource
    WechatTemplateMessageService wechatTemplateMessageService;

    @Override
    public String sendTextMsg(RequestMessage message) {
        TextMessage text = new TextMessage();
        text.setContent("测试自动回复" + message.getContent().trim());// 自动回复
        text.setCreateTime(DateUtils.getCurrentTimeMillis());
        text.setToUserName(message.getFromUserName());
        text.setFromUserName(message.getToUserName());
        return text.toXml();
    }

    @Override
    public WechatResult sendLinkMsg(RequestMessage message) {
        return null;
    }

    @Override
    public WechatResult defaultMsg(RequestMessage message) {
        return null;
    }

    @Override
    public WechatResult sendMusicMsg(RequestMessage message) {
        return null;
    }

    @Override
    public WechatResult senImageMsg(RequestMessage message) {
        return null;
    }

    @Override
    public WechatResult sendLocationMsg(RequestMessage message) {
        return null;
    }

    @Override
    public WechatResult sendVoiceMsg(RequestMessage message) {
        return null;
    }

    @Override
    public WechatResult sendVideoMsg(RequestMessage message) {
        return null;
    }

    @Override
    public WechatResult sendShortvideo(RequestMessage message) {
        return null;
    }

    @Override
    public String doSubscribe(RequestMessage message) {
        TextMessage text = new TextMessage();
        text.setContent("未关注时进行关注再事件二维码参数" + message.getEventKey());// 自动回复
        text.setCreateTime(DateUtils.getCurrentTimeMillis());
        text.setToUserName(message.getFromUserName());
        text.setFromUserName(message.getToUserName());
        return text.toXml();
    }

    @Override
    public WechatResult doUnsubscribe(RequestMessage message) {
        return null;
    }

    @Override
    public String doScan(RequestMessage message) {
        TextMessage text = new TextMessage();
        text.setContent("已关注事件二维码参数" + message.getEventKey());// 自动回复
        text.setCreateTime(DateUtils.getCurrentTimeMillis());
        text.setToUserName(message.getFromUserName());
        text.setFromUserName(message.getToUserName());
        return text.toXml();
    }
}
