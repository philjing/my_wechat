/**
 * FileName: WechatMessageService
 * Author:   Phil
 * Date:     11/21/2018 1:33 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.message.service;

import com.phil.modules.result.WechatResult;
import com.phil.wechat.message.model.basic.request.RequestMessage;

/**
 * 〈微信消息处理，基本消息收发回复、模板消息〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/21/2018 1:33 PM
 * @since 1.0
 */
public interface WechatMessageService {

    /**
     * 用户发送的为文本消息
     *
     * @param message    基础消息
     * @return 返回需要该消息回复的xml格式类型的字符串
     */
    String sendTextMsg(RequestMessage message);


    /**
     * 链接消息
     *
     * @param message
     * @return 返回需要该消息回复的xml格式类型的字符串
     */
    WechatResult sendLinkMsg(RequestMessage message);

    /**
     * 默认执行的消息
     *
     * @param message
     * @return 返回需要该消息回复的xml格式类型的字符串
     */
    WechatResult defaultMsg(RequestMessage message);

    /**
     * 音乐执行的消息
     *
     * @param message    基础参数
     * @return 返回需要该消息回复的xml格式类型的字符串
     */
    WechatResult sendMusicMsg(RequestMessage message);

    /**
     * 图片消息
     *
     * @param message
     * @return 返回需要该消息回复的xml格式类型的字符串
     */
    WechatResult senImageMsg(RequestMessage message);

    /**
     * 地理位置消息
     *
     * @param message
     * @return 返回需要该消息回复的xml格式类型的字符串
     */
    WechatResult sendLocationMsg(RequestMessage message);

    /**
     * 语音消息
     *
     * @param message
     * @return 返回需要该消息回复的xml格式类型的字符串
     */
    WechatResult sendVoiceMsg(RequestMessage message);

    /**
     * 视频消息
     *
     * @param message    消息基类
     * @return 返回需要该消息回复的xml格式类型的字符串
     */
    WechatResult sendVideoMsg(RequestMessage message);

    /**
     * 小视频消息
     *
     * @param message
     * @return 返回需要该消息回复的xml格式类型的字符串
     */
    WechatResult sendShortvideo(RequestMessage message);

    /**
     * 用户关注时调用的方法
     *
     * @param message
     * @return
     */
    String doSubscribe(RequestMessage message);

    /**
     * 取消关注时调用的方法
     *
     * @param message
     * @return
     */
    WechatResult doUnsubscribe(RequestMessage message);

    /**
     * 用户已关注时的事件推送
     *
     * @param message
     * @return
     */
    String doScan(RequestMessage message);

}
