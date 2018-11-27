package com.phil.wechat.message.model.template.request;

import java.util.TreeMap;

/**
 * 模板基础选项
 *
 * @author phil
 * @date 2017年8月1日
 */
public class WechatTemplateData {

    /**
     * 获取参数
     *
     * @param color 文字的颜色(可不填)
     * @param value 文字的值
     * @return
     */
    public static TreeMap<String, String> thempleItem(String value, String color) {
        TreeMap<String, String> params = new TreeMap<>();
        params.put("value", value);
        params.put("color", color);
        return params;
    }
}
