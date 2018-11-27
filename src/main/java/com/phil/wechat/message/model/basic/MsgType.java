/**
 * FileName: MsgType
 * Author:   Phil
 * Date:     8/1/2018 17:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.message.model.basic;

/**
 * 〈Receiving standard messages Types〉
 *
 * @author Phil
 * @create 8/1/2018 16:33
 * @since 1.0.0
 */
public enum MsgType {

    TEXT("text"), IMAGE("image"), VOICE("voice"), VIDEO("video"), SHORTVIDEO("shortvideo"),
    LOCATION("location"), LINK("link"), MUSIC("music"), NEWS("news");

    private String type;

    MsgType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}