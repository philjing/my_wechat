/**
 * FileName: WechatMainApplication
 * Author:   Phil
 * Date:     11/20/2018 9:50 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 〈一句话功能简述〉<br>
 * 〈主启动程序〉
 *
 * @author Phil
 * @create 11/20/2018 9:50 PM
 * @since 1.0
 */
@SpringBootApplication
@EnableScheduling
public class WechatMainApplication {

    public static void main(String[] args) {
        // Spring应用启动起来
        SpringApplication.run(WechatMainApplication.class, args);
    }

}
