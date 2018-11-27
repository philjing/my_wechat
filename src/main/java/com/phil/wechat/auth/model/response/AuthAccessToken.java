package com.phil.wechat.auth.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * 网页授权access_token
 *
 * @author phil
 * @date 2017年7月2日
 */
@Getter
@Setter
public class AuthAccessToken extends AccessToken {

    private static final long serialVersionUID = -7322304990185215263L;

    @SerializedName("refresh_token")
    private String refreshToken; // 用户刷新access_token

    private String openid; // 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID

    private String scope; // 用户授权的作用域，使用逗号（,）分隔
}
