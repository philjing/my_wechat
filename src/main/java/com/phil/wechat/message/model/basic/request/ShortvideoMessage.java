/**
 * FileName: ShortVideoMsg
 * Author:   Phil
 * Date:     8/1/2018 17:07
 * Description: Sight message
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.message.model.basic.request;

import com.phil.wechat.message.model.basic.MsgType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈一句话功能简述〉
 *
 * @author Phil
 * @create 8/1/2018 17:07
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
@XStreamAlias("xml")
public class ShortvideoMessage extends VideoMessage {

    private static final long serialVersionUID = -5422432061507461801L;

    @Override
    public String setMsgType() {
        return MsgType.SHORTVIDEO.getType ();
    }
}
