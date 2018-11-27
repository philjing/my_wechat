/**
 * FileName: VideoMsg
 * Author:   Phil
 * Date:     8/1/2018 17:11
 * Description: Video message
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
 * 〈Video message〉
 *
 * @author Phil
 * @create 8/1/2018 17:11
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
@XStreamAlias("xml")
public class VideoMessage extends AbstractMessage {

    private static final long serialVersionUID = -6331358888009287237L;

    @XStreamAlias("MsgId")
    private long msgId;

    @XStreamAlias("MediaId")
    @XStreamConverter(CDATAConvert.class)
    private String mediaId;

    @XStreamAlias("ThumbMediaId")
    @XStreamConverter(CDATAConvert.class)
    private String thumbMediaId;

    @Override
    public String setMsgType() {
        return MsgType.VIDEO.getType();
    }
}
