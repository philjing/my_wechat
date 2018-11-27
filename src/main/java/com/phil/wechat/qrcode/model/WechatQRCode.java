package com.phil.wechat.qrcode.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * 微信带参二维码
 * @author phil
 * @date 2017年6月7日
 *
 */
@Getter
@Setter
public class WechatQRCode {
	// 获取的二维码
	private String ticket;
	// 二维码的有效时间,单位为秒,最大不超过2592000（即30天）

    @SerializedName("expire_seconds")
	private int expireSeconds;
	// 二维码图片解析后的地址
	private String url;
}