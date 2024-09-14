package cn.cai.workflow.config;

import cn.cai.workflow.filter.rest.StatelessUserAuthenticationFilter;
import cn.cai.workflow.listener.JacksonDateFormatListener;
import org.camunda.bpm.engine.rest.CustomJacksonDateFormatListener;
import org.camunda.bpm.engine.rest.security.auth.ProcessEngineAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebConfig {


    /**
     * 配置rest-api开启验证
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean<ProcessEngineAuthenticationFilter> processEngineAuthenticationFilterFilterRegistrationBean() {
        FilterRegistrationBean<ProcessEngineAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ProcessEngineAuthenticationFilter());
        registrationBean.setAsyncSupported(true);
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("authentication-provider", "org.camunda.bpm.engine.rest.security.auth.impl.HttpBasicAuthenticationProvider");
        registrationBean.setInitParameters(initParameters);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean<JacksonDateFormatListener> customJacksonDateFormatListenerServletListenerRegistrationBean() {
        ServletListenerRegistrationBean<JacksonDateFormatListener> registrationBean = new ServletListenerRegistrationBean<>();
        registrationBean.setListener(new JacksonDateFormatListener());
        return registrationBean;
    }

}
