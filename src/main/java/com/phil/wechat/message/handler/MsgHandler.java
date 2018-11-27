///**
// * FileName: MsgHandler
// * Author:   Phil
// * Date:     8/1/2018 21:46
// * Description:
// * History:
// * <author>          <time>          <version>          <desc>
// * 作者姓名           修改时间           版本号              描述
// */
//package com.phil.wechat.message.handler;
//
//import com.phil.modules.util.XmlUtil.XStreamFactroy;
//import com.phil.wechat.message.model.basic.AbstractMsg;
//import com.phil.wechat.message.model.basic.response.*;
//import com.thoughtworks.xstream.XStream;
//import org.apache.commons.lang3.StringUtils;
//
///**
// * 〈一句话功能简述〉
// * 〈〉
// *
// * @author Phil
// * @create 8/1/2018 21:46
// * @since 1.0.0
// */
//public class MsgHandler {
//
//    private static final String XML = "xml";
//
//    /**
//     * @param msg
//     * @return
//     */
//    public static String msgToXml(AbstractMsg msg) {
//        if (msg == null) {
//            return StringUtils.EMPTY;
//        }
//        XStream xs = XStreamFactroy.init(Boolean.TRUE);
//        xs.alias(XML, msg.getClass());
//        return xs.toXML(msg);
//    }
//
//    /**
//     * 文本消息
//     *
//     * @param text
//     * @return
//     */
//    public static String textMsgToXml(TextMsg text) {
//        if (text == null) {
//            return StringUtils.EMPTY;
//        }
//        XStream xs = XStreamFactroy.init(Boolean.TRUE);
//        xs.alias(XML, TextMsg.class);
//        return xs.toXML(text);
//    }
//
//    /**
//     * 图片消息
//     *
//     * @param image
//     * @return
//     */
//    public static String imageMsgToXml(ImageMsg image) {
//        if (image == null) {
//            return StringUtils.EMPTY;
//        }
//        XStream xs = XStreamFactroy.init(Boolean.TRUE);
//        xs.alias(XML, ImageMsg.class);
//        xs.aliasField("Image", Image.class, "image");
//        return xs.toXML(image);
//    }
//
//    /**
//     * 音乐消息
//     *
//     * @param music
//     * @return
//     */
//    public static String musicMsgToXml(MusicMsg music) {
//        if (music == null) {
//            return StringUtils.EMPTY;
//        }
//        XStream xs = XStreamFactroy.init(Boolean.TRUE);
//        xs.alias(XML, MusicMsg.class);
//        xs.aliasField("Music", Music.class, "music");
//        return xs.toXML(music);
//    }
//
//    /**
//     * 图文消息
//     *
//     * @param news
//     * @return
//     */
//    public static String newsMsgToXml(NewsMsg news) {
//        if (news == null) {
//            return StringUtils.EMPTY;
//        }
//        XStream xs = XStreamFactroy.init(true);
//        xs.alias(XML, NewsMsg.class);
//        xs.addImplicitCollection(Articles.class, "list", "item", Item.class);
//        xs.aliasField("Articles", NewsMsg.class, "articles");
//        return xs.toXML(news);
//    }
//
//    /**
//     * (短)视频消息
//     *
//     * @param video
//     * @return
//     */
//    public static String videoMsgToXml(VideoMsg video) {
//        if (video == null) {
//            return StringUtils.EMPTY;
//        }
//        XStream xs = XStreamFactroy.init(Boolean.TRUE);
//        xs.alias("xml", VideoMsg.class);
//        xs.aliasField("Video", Video.class, "video");
//        return xs.toXML(video);
//    }
//
//    /**
//     * 语音消息
//     *
//     * @param voice
//     * @return
//     */
//    public static String voiceMsgToXml(VoiceMsg voice) {
//        if (voice == null) {
//            return StringUtils.EMPTY;
//        }
//
//        XStream xs = XStreamFactroy.init(Boolean.TRUE);
//        xs.alias("xml", VoiceMsg.class);
//        xs.aliasField("Voice", Voice.class, "voice");
//        return xs.toXML(voice);
//    }
//
//    public static void main(String[] args) {
//
//        System.out.print(newsMsgToXml(new NewsMsg()));
//
//    }
//
//}
