/**
 * FileName: TextMessage
 * Author:   Phil
 * Date:     8/1/2018 16:30
 * Description: Text messages
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.message.model.basic.request;

import com.phil.modules.converter.CDATAConvert;
import com.phil.wechat.message.model.basic.AbstractMessage;
import com.phil.wechat.message.model.basic.MsgType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈一句话功能简述〉
 * 〈文本消息〉
 *
 * @author Phil
 * @create 8/1/2018 16:30
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
@XStreamAlias("xml")
public class TextMessage extends AbstractMessage {

    private static final long serialVersionUID = -3843872584044244318L;

    @XStreamAlias("MsgId")
    private long msgId;

    @XStreamAlias("Content")
    @XStreamConverter(CDATAConvert.class)
    private String content;

    @Override
    public String setMsgType() {
        return MsgType.TEXT.getType();
    }
}
