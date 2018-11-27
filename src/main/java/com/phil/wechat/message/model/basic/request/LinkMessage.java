/**
 * FileName: LinkMsg
 * Author:   Phil
 * Date:     8/1/2018 17:13
 * Description: Link message
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
 * 〈Link message〉
 *
 * @author Phil
 * @create 8/1/2018 17:13
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
@XStreamAlias("xml")
public class LinkMessage extends AbstractMessage {

    private static final long serialVersionUID = 438674546276087683L;

    @XStreamAlias("MsgId")
    private long msgId;

    @XStreamAlias("Title")
    @XStreamConverter(CDATAConvert.class)
    private String title;

    @XStreamAlias("Description")
    @XStreamConverter(CDATAConvert.class)
    private String description;

    @XStreamAlias("Url")
    @XStreamConverter(CDATAConvert.class)
    private String url;

    @Override
    public String setMsgType() {
        return MsgType.LINK.getType();
    }
}
