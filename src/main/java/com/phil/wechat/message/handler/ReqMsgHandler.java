///**
// * FileName: ReqMsgHandler
// * Author:   Phil
// * Date:     8/2/2018 12:26
// * Description: 回复消息处理
// * History:
// * <author>          <time>          <version>          <desc>
// * 作者姓名           修改时间           版本号              描述
// */
//package com.phil.wechat.message.handler;
//
//import com.phil.wechat.message.model.basic.BasicMsg;
//import com.phil.wechat.message.model.basic.response.ImageMessage;
//import com.phil.wechat.message.model.basic.response.NewsMessage;
//import com.phil.wechat.message.model.basic.response.TextMessage;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//
//import java.util.LinkedList;
//
///**
// * 〈一句话功能简述〉
// * 〈回复消息处理〉
// *
// * @author Phil
// * @create 8/2/2018 12:26
// * @since 1.0.0
// */
//@Slf4j
//public class ReqMsgHandler {
//
//    /**
//     * @param basic
//     * @param msg
//     * @return
//     */
//    public static String sendTextMsg(BasicMsg basic, TextMessage msg) {
//        msg.setFromUserName(basic.getToUserName());
//        msg.setToUserName(basic.getFromUserName());
//        msg.setCreateTime(System.currentTimeMillis());
//        return MsgHandler.textMsgToXml(msg);
//    }
//
//    public static String sendImageMsg(BasicMsg basic, ImageMessage msg) {
//        msg.setFromUserName(basic.getToUserName());
//        msg.setToUserName(basic.getFromUserName());
//        msg.setCreateTime(System.currentTimeMillis());
//        return MsgHandler.imageMsgToXml(msg);
//    }
//
//    public static String sendNewsMsg(BasicMsg basic, NewsMessage newsMessage) {
//        if (newsMessage == null) {
//            return StringUtils.EMPTY;
//        }
//        articles.setList(items);
//        msg.setFromUserName(basic.getToUserName());
//        msg.setToUserName(basic.getFromUserName());
//        msg.setCreateTime(System.currentTimeMillis());
//        msg.setArticles(articles);
//        msg.setArticleCount(items.size());
//        return MsgHandler.newsMsgToXml(msg);
//    }
//
//    public static void main(String[] args) {
//
//        BasicMsg basicMsg = new BasicMsg();
//        basicMsg.setFromUserName("from");
//        basicMsg.setToUserName("to");
//
//        Articles articles = new Articles();
//
//        Item item1 = new Item();
//        item1.setDescription("d1");
//        item1.setTitle("t1");
//
//        Item item2 = new Item();
//        item2.setDescription("d2");
//        item2.setTitle("t2");
//
//        List<Item> list = new LinkedList<>();
//        list.add(item1);
//        list.add(item2);
//        articles.setList(list);
//
//        NewsMsg msg = new NewsMsg();
//
//        System.out.println(sendNewsMsg(basicMsg, msg, articles, list));
//    }
//}
