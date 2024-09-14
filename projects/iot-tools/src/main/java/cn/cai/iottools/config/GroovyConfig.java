package cn.cai.iottools.config;

import groovy.lang.GroovyClassLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * groovy相关配置
 */

@Configuration
public class GroovyConfig {


    /**
     * 类执行器
     * @return
     */
    @Bean
    public GroovyClassLoader groovyClassLoader() {
        return new GroovyClassLoader();
    }
}
