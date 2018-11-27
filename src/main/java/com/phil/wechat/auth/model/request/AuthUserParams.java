package com.phil.wechat.auth.model.request;

import com.phil.wechat.auth.model.AbstractParams;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.TreeMap;

/**
 * 获取用户信息请求
 * @author phil
 * @date  2017年7月2日
 *
 */
@Setter
@Getter
public class AuthUserParams extends AbstractParams {

	private static final long serialVersionUID = 66535717787322321L;
	
	private String accessToken;
	private String openid;
	private String lang;

	/**
	 * 参数组装
	 * @return
	 */
	public Map<String, String> getParams() {
		Map<String, String> params = new TreeMap<>();
		params.put("access_token", this.accessToken);
		params.put("openid", this.openid);
		params.put("lang", this.lang);
		return params;
	}
}
