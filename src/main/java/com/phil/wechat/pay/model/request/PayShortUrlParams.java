/**
 * 
 */
package com.phil.wechat.pay.model.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 二维码链接转成短链接请求参数
 * @author phil
 * @date  2017年8月1日
 *
 */
@Getter
@Setter
public class PayShortUrlParams extends AbstractPayParams {
	
	private static final long serialVersionUID = 8485062553285184505L;

	private String long_url;
}
