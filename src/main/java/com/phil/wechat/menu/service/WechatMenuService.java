/**
 * FileName: WechatMenuService
 * Author:   Phil
 * Date:     11/24/2018 1:14 AM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.menu.service;

import com.phil.modules.result.ResultState;
import com.phil.wechat.menu.model.response.CondResult;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/24/2018 1:14 AM
 * @since 1.0
 */
public interface WechatMenuService {

    ResultState createMenu(String accessToken);

    String getMenu(String accessToken);

    ResultState deleteMenu(String accessToken);

    CondResult addConditional(String accessToken);

    ResultState deleteConditional(String accessToken);

    String testConditional(String accessToken, String userId);

    String getConditionalConfig(String accessToken);

}
