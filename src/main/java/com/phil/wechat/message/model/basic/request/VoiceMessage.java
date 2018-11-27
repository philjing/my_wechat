/**
 * FileName: VoiceMsg
 * Author:   Phil
 * Date:     8/1/2018 17:00
 * Description: Voice message
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
 * 〈Voice message〉
 *
 * @author Phil
 * @create 8/1/2018 17:00
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
@XStreamAlias("xml")
public class VoiceMessage extends AbstractMessage {

    private static final long serialVersionUID = 8431121537060876665L;

    @XStreamAlias("MsgId")
    private long msgId;

    @XStreamAlias("MediaId")
    @XStreamConverter(CDATAConvert.class)
    private String mediaId;

    @XStreamAlias("Format")
    @XStreamConverter(CDATAConvert.class)
    private String format;

    @XStreamAlias("Recognition")
    @XStreamConverter(CDATAConvert.class)
    private String recognition;

    @Override
    public String setMsgType() {
        return MsgType.VOICE.getType ();
    }
}
