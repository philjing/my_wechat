///**
// * FileName: WechatMessageService
// * Author:   Phil
// * Date:     11/21/2018 1:33 PM
// * Description:
// * History:
// * <author>          <time>          <version>          <desc>
// * 作者姓名           修改时间           版本号              描述
// */
//package com.phil.wechat.message.service;
//
//import com.phil.wechat.message.model.basic.BasicMsg;
//import com.phil.wechat.message.model.mass.result.MassMsgResult;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 〈一句话功能简述〉<br>
// * 〈〉
// *
// * @author Phil
// * @create 11/21/2018 1:33 PM
// * @since 1.0
// */
// interface WechatMessageService {
//
//    /**
//     * 用户发送的为文本消息
//     *
//     * @param msg    基础消息
//     * @param params 请求参数
//     * @return 返回需要该消息回复的xml格式类型的字符串
//     */
//     WechatResult textMsg(BasicMsg msg, Map<String, String> params);
//
//
//    /**
//     * 链接消息
//     *
//     * @param msg
//     * @param params
//     * @return 返回需要该消息回复的xml格式类型的字符串
//     */
//     WechatResult linkMsg(BasicMsg msg, Map<String, String> params);
//
//    /**
//     * 默认执行的消息
//     *
//     * @param msg
//     * @param params
//     * @return 返回需要该消息回复的xml格式类型的字符串
//     */
//     WechatResult defaultMsg(BasicMsg msg, Map<String, String> params);
//
//    /**
//     * 音乐执行的消息
//     *
//     * @param msg    基础参数
//     * @param params 请求参数
//     * @return 返回需要该消息回复的xml格式类型的字符串
//     */
//     WechatResult musicMsg(BasicMsg msg, Map<String, String> params);
//
//    /**
//     * 图片消息
//     *
//     * @param msg
//     * @param params
//     * @return 返回需要该消息回复的xml格式类型的字符串
//     */
//     WechatResult imageMsg(BasicMsg msg, Map<String, String> params);
//
//    /**
//     * 地理位置消息
//     *
//     * @param msg
//     * @param params
//     * @return 返回需要该消息回复的xml格式类型的字符串
//     */
//     WechatResult locationMsg(BasicMsg msg, Map<String, String> params);
//
//    /**
//     * 语音消息
//     *
//     * @param msg
//     * @param params
//     * @return 返回需要该消息回复的xml格式类型的字符串
//     */
//     WechatResult voiceMsg(BasicMsg msg, Map<String, String> params);
//
//    /**
//     * 视频消息
//     *
//     * @param msg    消息基类
//     * @param params
//     * @return 返回需要该消息回复的xml格式类型的字符串
//     */
//     WechatResult videoMsg(BasicMsg msg, Map<String, String> params);
//
//    /**
//     * 小视频消息
//     *
//     * @param msg
//     * @param params
//     * @return 返回需要该消息回复的xml格式类型的字符串
//     */
//     WechatResult shortvideo(BasicMsg msg, Map<String, String> params);
//
//    /**
//     * 用户关注时调用的方法
//     *
//     * @param msg
//     * @param params
//     * @return
//     */
//     WechatResult subscribe(BasicMsg msg, Map<String, String> params);
//
//    /**
//     * 取消关注时调用的方法
//     *
//     * @param msg
//     * @param params
//     * @return
//     */
//     WechatResult unsubscribe(BasicMsg msg, Map<String, String> params);
//
//    /**
//     * 用户已关注时的事件推送
//     *
//     * @param msg
//     * @param params
//     * @return
//     */
//     WechatResult scan(BasicMsg msg, Map<String, String> params);
//
//    /**
//     * 上报地理位置事件
//     *
//     * @param msg
//     * @param params
//     * @return
//     */
//     WechatResult eventLocation(BasicMsg msg, Map<String, String> params);
//
//    /**
//     * 点击菜单拉取消息时的事件推送 (自定义菜单的click在这里做响应)
//     *
//     * @param msg
//     * @param params
//     * @return
//     */
//     WechatResult eventClick(BasicMsg msg, Map<String, String> params);
//
//    /**
//     * 点击菜单跳转链接时的事件推送 (自定义菜单的view在这里做响应)
//     *
//     * @param msg
//     * @param params
//     * @return
//     */
//     WechatResult eventView(BasicMsg msg, Map<String, String> params);
//
//    /**
//     * 事件类型默认返回
//     *
//     * @param msg
//     * @param params
//     * @return
//     */
//     WechatResult eventDefaultReply(BasicMsg msg, Map<String, String> params);
//
//    /**
//     * 客服创建回话 事件
//     *
//     * @param msg    消息基类
//     * @param params 参数内容
//     * @return
//     */
//     WechatResult kfCreateSession(BasicMsg msg, Map<String, String> params);
//
//    /**
//     * 客服关闭回话事件
//     *
//     * @param msg    消息基类
//     * @param params xml内容
//     * @return
//     */
//     WechatResult kfCloseSession(BasicMsg msg, Map<String, String> params);
//
//    /**
//     * 客服转接回话事件
//     *
//     * @param msg    消息基类
//     * @param params xml内容
//     * @return
//     */
//     WechatResult kfSwitchSession(BasicMsg msg, Map<String, String> params);
//
//    /**
//     * 发送模板消息
//     *
//     * @param accessToken
//     * @param data
//     * @return
//     */
//     TemplateMsgResult sendTemplate(String accessToken, String data);
//
//    /**
//     * 根据标签进行群发文本消息
//     *
//     * @param accessToken 授权token
//     * @param entity      图文消息对象
//     * @return
//     */
//     MassMsgResult sendTextToTag(String accessToken, int tagId, String content);
//
//    /**
//     * 根据标签进行群发图文消息
//     *
//     * @param accessToken 授权token
//     * @param tagId       标签
//     * @param mediaId     uploadMedia方法获得
//     * @return
//     */
//     MassMsgResult sendMpnewsToTag(String accessToken, int tagId, String mediaId);
//
//    /**
//     * 根据标签进行群发图片
//     *
//     * @param accessToken 授权token
//     * @param tagId       标签
//     * @param mediaId     uploadMedia方法获得
//     * @return
//     */
//     MassMsgResult sendImageToTag(String accessToken, int tagId, String mediaId);
//
//    /**
//     * 根据标签进行群发语音/音频
//     *
//     * @param accessToken 授权token
//     * @param tagId       标签
//     * @param mediaId     uploadMedia方法获得
//     * @return
//     */
//     MassMsgResult sendVoiceToTag(String accessToken, int tagId, String mediaId);
//
//    /**
//     * 根据标签进行群发视频
//     *
//     * @param accessToken 授权token
//     * @param tagId       标签
//     * @param mediaId     uploadMedia方法获得
//     * @return
//     */
//     MassMsgResult sendVideoToTag(String accessToken, int tagId, String mediaId);
//
//    /**
//     * 根据标签进行群发卡券
//     *
//     * @param accessToken 授权token
//     * @param tagId       标签
//     * @param cardId  卡券Id
//     * @return
//     */
//     MassMsgResult sendWxCardToTag(String accessToken, int tagId, String cardId);
//
//    /**
//     * 根据OpenId进行群发图文消息
//     *
//     * @param accessToken 授权token
//     * @param tagId       标签
//     * @param mediaId     uploadMedia方法获得
//     * @return
//     */
//     MassMsgResult sendMpnewsToOpenid(String accessToken, List<String> openids, String mediaId);
//
//    /**
//     * 根据OpenId进行群发文本消息
//     *
//     * @param accessToken 授权token
//     * @param openids     openid
//     * @param content
//     * @return
//     */
//     MassMsgResult sendTextToOpenid(String accessToken, List<String> openids, String content);
//
//    /**
//     * 根据OpenId进行群发语音消息
//     *
//     * @param accessToken 授权token
//     * @param openids     openid
//     * @param mediaId
//     * @return
//     */
//     MassMsgResult sendVocieToOpenid(String accessToken, List<String> openids, String mediaId);
//
//    /**
//     * 根据OpenId进行群发图片消息
//     *
//     * @param accessToken 授权token
//     * @param openids     openid
//     * @param mediaId
//     * @return
//     */
//     MassMsgResult sendImageToOpenid(String accessToken, List<String> openids, String mediaId);
//
//    /**
//     * 根据OpenId进行群发视频消息
//     *
//     * @param accessToken  授权token
//     * @param openids      openid
//     * @param mpVideoMedia uploadMediaVideo方法获得media
//     * @return
//     */
//    MassMsgResult sendVideoToOpenid(String accessToken, List<String> openids, MpVideoMedia mpVideoMedia);
//
//    /**
//     * 根据OpenId进行群发卡券消息
//     *
//     * @param accessToken 授权token
//     * @param openids     openid
//     * @param mediaId
//     * @return
//     */
//     MassMsgResult sendWxcardToOpenid(String accessToken, List<String> openids, String cardId);
//
//
//}
