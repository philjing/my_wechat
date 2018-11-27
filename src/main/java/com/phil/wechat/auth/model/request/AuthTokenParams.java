package com.phil.wechat.auth.model.request;

import com.phil.wechat.auth.model.AbstractParams;
import lombok.*;

import java.util.Map;
import java.util.TreeMap;

/**
 * 获取授权请求token的请求参数
 *
 * @author phil
 * @date 2017年7月2日
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = false)
public class AuthTokenParams extends AbstractParams {

    private static final long serialVersionUID = 4652953400751046159L;
    private String appid; //公众号的唯一标识
    private String secret; //公众号的appsecret
    private String code; //填写第一步获取的code参数
    private String grant_type = "authorization_code";

    /**
     * 参数组装
     *
     * @return
     */
    public Map<String, String> getParams() {
        Map<String, String> params = new TreeMap<>();
        params.put("appid", this.appid);
        params.put("secret", this.secret);
        params.put("code", this.code);
        params.put("grant_type", this.grant_type);
        return params;
    }
}
