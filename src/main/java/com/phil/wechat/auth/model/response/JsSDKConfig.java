package com.phil.wechat.auth.model.response;

import lombok.Getter;
import lombok.Setter;

/**
 * JS-SDK的页面配置信息
 *
 * @author phil
 * @date 2017年8月22日
 */
@Getter
@Setter
public class JsSDKConfig {

    private String appId;

    private long timestamp;

    private String noncestr;

    private String signature;
}