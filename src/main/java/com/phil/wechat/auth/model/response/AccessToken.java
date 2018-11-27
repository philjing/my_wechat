package com.phil.wechat.auth.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 微信通用接口凭证
 * 
 * @author phil
 * @date 2017年7月2日
 * 
 */
@Getter
@Setter
public class AccessToken implements Serializable {

    private static final long serialVersionUID = 5806078615354556552L;

    // 获取到的凭证
    @SerializedName("access_token")
	private String accessToken;
	// 凭证有效时间，单位：秒
	private int expires_in;

}
