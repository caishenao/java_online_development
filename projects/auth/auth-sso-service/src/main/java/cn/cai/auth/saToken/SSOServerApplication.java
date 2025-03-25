package cn.cai.auth.saToken;

import cn.dev33.satoken.sso.SaSsoManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * SSO服务端启动类
 * @author 蔡
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SSOServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSOServerApplication.class, args);
        System.out.println();
        System.out.println("---------------------- Sa-Token SSO 统一认证中心启动成功 ----------------------");
        System.out.println("配置信息：" + SaSsoManager.getServerConfig());
        System.out.println();
    }
}
