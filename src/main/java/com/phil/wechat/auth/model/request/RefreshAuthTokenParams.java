package com.phil.wechat.auth.model.request;

import com.phil.wechat.auth.model.AbstractParams;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.TreeMap;

/**
 * 刷新token请求
 * 
 * @author phil
 * @date 2017年7月2日
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class RefreshAuthTokenParams extends AbstractParams {

	private static final long serialVersionUID = -1748872326563685295L;
	private String appid;
	private String grant_type = "refresh_token";
	private String refresh_token;

	/**
	 * 参数组装
	 * 
	 * @return
	 */
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new TreeMap<>();
		params.put("appid", this.appid);
		params.put("grant_type", this.grant_type);
		params.put("refresh_token", this.refresh_token);
		return params;
	}
}
