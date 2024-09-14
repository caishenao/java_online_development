package cn.cai.iottools;

import com.gitee.sunchenbin.mybatis.actable.manager.handler.StartUpHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan({"com.gitee.sunchenbin.mybatis.actable.dao.*", "cn.cai.iottools.mapper"})
@ComponentScan({"com.gitee.sunchenbin.mybatis.actable.manager.*", "cn.cai.*"})
public class IotToolsApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(IotToolsApplication.class, args);
        // 容器中获取actable的核心处理类
        StartUpHandler bean =run.getBean(StartUpHandler.class, args);
        // 手动执行actable的建表方法
        bean.startHandler();
    }

}
