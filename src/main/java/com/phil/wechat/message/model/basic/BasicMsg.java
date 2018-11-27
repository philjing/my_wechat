/**
 * FileName: BasicMsg
 * Author:   Phil
 * Date:     8/2/2018 13:16
 * Description: 消息基本信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.message.model.basic;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 〈一句话功能简述〉
 * 〈消息基本信息〉
 *
 * @author Phil
 * @create 8/2/2018 13:16
 * @since 1.0.0
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class BasicMsg {

    private String toUserName;

    private String fromUserName;

    private int CreateTime;

    private long MsgId;

    @XmlElement(name = "ToUserName")
    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    @XmlElement(name = "FromUserName")
    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public int getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(int createTime) {
        CreateTime = createTime;
    }

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }
}
