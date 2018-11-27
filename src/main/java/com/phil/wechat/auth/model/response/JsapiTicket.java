package com.phil.wechat.auth.model.response;

import com.google.gson.annotations.SerializedName;
import com.phil.modules.result.ResultState;
import lombok.Getter;
import lombok.Setter;

/**
 * jsapi_ticket是公众号用于调用微信JS接口的临时票据
 *
 * @author phil
 * @date 2017年8月21日
 */
@Getter
@Setter
public class JsapiTicket extends ResultState {

    private static final long serialVersionUID = -7890303071477417151L;

    private String ticket; //jsapi_ticket

    @SerializedName("expires_in")
    private String expiresIn;
}