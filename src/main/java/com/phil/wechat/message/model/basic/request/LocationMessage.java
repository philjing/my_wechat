/**
 * FileName: Location
 * Author:   Phil
 * Date:     8/1/2018 17:03
 * Description: Location message
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
 * 〈Location message〉
 *
 * @author Phil
 * @create 8/1/2018 17:03
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
@XStreamAlias("xml")
public class LocationMessage extends AbstractMessage {

    private static final long serialVersionUID = 6454465001982821055L;

    @XStreamAlias("MsgId")
    private long msgId;

    @XStreamAlias("Location_X")
    private double locationX;

    @XStreamAlias("Location_Y")
    private double locationY;

    @XStreamAlias("Scale")
    private int scale;

    @XStreamAlias("Label")
    @XStreamConverter(CDATAConvert.class)
    private String label;

    @Override
    public String setMsgType() {
        return MsgType.LOCATION.getType();
    }
}
