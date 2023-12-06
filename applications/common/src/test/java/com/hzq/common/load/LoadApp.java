package com.hzq.common.load;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * es数据源自行管理，依赖applicationcontext自定义注册，会导致默认客户端实例先于被管理实例生成，导致心跳报错
 * exclude：移除es默认自动装配的客户端，因为默认客户端优先级高于applicationcontext注册的bean，所以无法自动避免默认客户端加载
 * excludeName：spring-boot-autoconfigure包的spring.factories名称
 * scanBasePackages：扫包严格按配置顺序来的，所以改不同包名，优先加载公共实例
 *
 * @author Huangzq
 * @date 2023/12/6 16:37
 */
@SpringBootApplication(scanBasePackages = {"com.hzq.common.load", "com.hzq.common.utils"})
public class LoadApp {
    public static void main(String[] args) {
        SpringApplication.run(LoadApp.class, args);
    }
}
