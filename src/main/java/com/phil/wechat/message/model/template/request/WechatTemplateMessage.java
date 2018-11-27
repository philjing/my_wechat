package com.phil.wechat.message.model.template.request;

import com.google.gson.annotations.SerializedName;
import com.phil.modules.annotation.NotRequire;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.TreeMap;

/**
 * 模板消息
 * @author phil
 * @date 2017年7月2日
 *
 */
@Getter
@Setter
public class WechatTemplateMessage implements Serializable {

    private static final long serialVersionUID = -1035536196053732735L;

    @SerializedName("touser")
    private String touser; //接收者openid

    @SerializedName("template_id")
	private String templateId; //模板ID

	@NotRequire//只是个标识
	private String url; //模板跳转链接

	// "miniprogram":{ 未加入
	// "appid":"xiaochengxuappid12345",
	// "pagepath":"index?foo=bar"
	// },

	private TreeMap<String, TreeMap<String, String>> data; //data数据

	/**
	 * 参数
	 * @param value
	 * @param color 可不填
	 * @return
	 */
	public static TreeMap<String, String> item(String value, String color) {
		TreeMap<String, String> params = new TreeMap<>();
		params.put("value", value);
		params.put("color", color);
		return params;
	}
}
