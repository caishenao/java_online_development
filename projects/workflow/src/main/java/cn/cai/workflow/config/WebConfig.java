package cn.cai.workflow.config;

import cn.cai.web.comment.interceptor.RequestContextInterceptor;
import cn.cai.workflow.listener.JacksonDateFormatListener;
import org.camunda.bpm.engine.rest.security.auth.ProcessEngineAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestContextInterceptor())
                .addPathPatterns("/engine-rest/*","/customize/*")
                .excludePathPatterns(
                        "/",
                        "/error",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/v2/api-docs",
                        "/doc.html",
                        "/actuator/**",
                        "/openapi***",
                        "/api-docs/**",
                        "/camunda/**"
                );
    }

    /**
     * 配置rest-api开启验证
     *
     * @return
     */
//    @Bean
//    public FilterRegistrationBean<ProcessEngineAuthenticationFilter> processEngineAuthenticationFilterFilterRegistrationBean() {
//        FilterRegistrationBean<ProcessEngineAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new ProcessEngineAuthenticationFilter());
//        registrationBean.setAsyncSupported(true);
//        Map<String, String> initParameters = new HashMap<>();
////        initParameters.put("authentication-provider", "org.camunda.bpm.engine.rest.security.auth.impl.HttpBasicAuthenticationProvider");
//        initParameters.put("authentication-provider", "cn.cai.workflow.provider.AuthenticationProvider");
//        registrationBean.setInitParameters(initParameters);
//        registrationBean.addUrlPatterns("/engine-rest/*", "/customize/*");
//        return registrationBean;
//    }

    @Bean
    public ServletListenerRegistrationBean<JacksonDateFormatListener> customJacksonDateFormatListenerServletListenerRegistrationBean() {
        ServletListenerRegistrationBean<JacksonDateFormatListener> registrationBean = new ServletListenerRegistrationBean<>();
        registrationBean.setListener(new JacksonDateFormatListener());
        return registrationBean;
    }

}
