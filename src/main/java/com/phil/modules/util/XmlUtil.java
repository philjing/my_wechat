/**
 * FileName: XmlUtil
 * Author:   Phil
 * Date:     8/1/2018 11:01
 * Description: XML Parse Util
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.modules.util;

import com.phil.wechat.message.model.basic.response.NewsMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈XML Parse Util〉
 *
 * @author Phil
 * @create 8/1/2018
 * @since 1.0.0
 */
public class XmlUtil {

    private XmlUtil() {
    }

    /**
     * @param request
     * @return
     * @throws IOException
     */
    public static Map<String, Object> toMap(HttpServletRequest request) throws IOException {
        if (request != null && request.getInputStream() != null) {
            return toMap(request.getInputStream());
        }
        return new HashMap<>();
    }

    /**
     * @param inputStream
     * @return
     */
    public static Map<String, Object> toMap(InputStream inputStream) {
        Map<String, Object> map = new HashMap<>();
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();
            for (Element e : elementList) {
                map.put(e.getName(), e.getText());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 使用dom4将xml文件中的数据转换成Map<Object,Object>
     *
     * @param xml xml格式的字符串
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static Map<String, Object> toMap(String xml, String encoding)
            throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        if (StringUtils.isEmpty(encoding)) {
            encoding = StandardCharsets.UTF_8.name();
        }
        InputStream is = IOUtils.toInputStream(xml, encoding);
        org.w3c.dom.Document document = builder.parse(is);
        // 获取到document里面的全部结点
        org.w3c.dom.NodeList allNodes = document.getFirstChild().getChildNodes();
        org.w3c.dom.Node node;
        Map<String, Object> map = new LinkedHashMap<>();
        int i = 0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if (node instanceof org.w3c.dom.Element) {
                map.put(node.getNodeName(), node.getTextContent());
            }
            i++;
        }
        return map;
    }


    /**
     * 示例 <xml> <return_code><![CDATA[SUCCESS]]></return_code>
     * <return_msg><![CDATA[OK]]></return_msg>
     * <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
     * <mch_id><![CDATA[10000100]]></mch_id>
     * <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>
     * <openid><![CDATA[oUpF8uMuAJO_M2pxb1Q9zNjWeS6o]]></openid>
     * <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>
     * <result_code><![CDATA[SUCCESS]]></result_code>
     * <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>
     * <trade_type><![CDATA[JSAPI]]></trade_type> </xml>
     * 将xml数据(<![CDATA[SUCCESS]]>格式)映射到java对象中
     *
     * @param xml 待转换的xml格式的数据
     * @param cls 待转换为的java对象
     * @return
     */
    public static <T> T fromXml(String xml, Class<T> cls) {
        // 将从API返回的XML数据映射到Java对象
        XStream xstream = new XStream(new DomDriver());
//        xstream.alias(SystemConstant.XML, cls);
        xstream.processAnnotations(cls);
        xstream.ignoreUnknownElements();// 暂时忽略掉一些新增的字段
        return cls.cast(xstream.fromXML(xml));
    }

    /**
     * 将java对象可转换为xml(<![CDATA[ ]]>格式)
     *
     * @param obj
     * @return
     */
    public static String toXml(Object obj) {
        XStream xStream = new XStream(new DomDriver(StandardCharsets.UTF_8.name(), new XmlFriendlyNameCoder("-_", "_")));//解决下划线问题
        //xstream使用注解转换
        xStream.processAnnotations(obj.getClass());
        return StringEscapeUtils.unescapeXml(xStream.toXML(obj));
    }

    public static void main(String[] args) {
//        TextMessage message = new TextMessage();
//        message.setContent("11");
//        message.setCreateTime(System.currentTimeMillis() / 1000L);
//        message.setToUserName("To");
//        message.setFromUserName("from");
//
//        System.out.println(XmlUtil.toXml(message));
//        System.out.println(StringEscapeUtils.unescapeXml(XmlUtil.toXml(message)));
//
//        MusicMessage musicMessage = new MusicMessage();
//        MusicMessage.Music music = new MusicMessage.Music();
//        music.setDescription("de");
//        music.setHqMusicUrl("hq");
//        musicMessage.setMusic(music);
//        musicMessage.setFromUserName("from");
//        musicMessage.setToUserName("to");
//        System.out.println(XmlUtil.toXml(musicMessage));

        NewsMessage newsMessage = new NewsMessage();
        NewsMessage.Item item1 = new NewsMessage.Item();
        item1.setPicurl("pic");
        NewsMessage.Item item2 = new NewsMessage.Item();
        item2.setPicurl("pic");
        newsMessage.addArticle(item1);
        newsMessage.addArticle(item2);

        newsMessage.setFromUserName("from");
        newsMessage.setToUserName("to");
        System.out.println(XmlUtil.toXml(newsMessage));
    }
}
