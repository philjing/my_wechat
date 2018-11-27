/**
 * FileName: ImageMsg
 * Author:   Phil
 * Date:     8/1/2018 16:50
 * Description: Image messages
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
 * 〈Image messages〉
 *
 * @author Phil
 * @create 8/1/2018 16:50
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
@XStreamAlias("xml")
public class ImageMessage extends AbstractMessage {

    private static final long serialVersionUID = 7956698377730960776L;

    @XStreamAlias("MsgId")
    private long msgId;

    @XStreamAlias("PicUrl")
    @XStreamConverter(value = CDATAConvert.class)
    private String picUrl;

    @XStreamAlias("MediaId")
    @XStreamConverter(value = CDATAConvert.class)
    private String mediaId;

    @Override
    public String setMsgType() {
        return MsgType.IMAGE.getType();
    }
}
