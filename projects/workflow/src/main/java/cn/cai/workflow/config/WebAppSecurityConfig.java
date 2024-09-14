//package cn.cai.workflow.config;
//
//import org.camunda.bpm.webapp.impl.security.auth.ContainerBasedAuthenticationFilter;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
////import org.springframework.security.config.Customizer;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.http.SessionCreationPolicy;
////import org.springframework.security.web.SecurityFilterChain;
//
//import java.util.Collections;
//
//@Configuration
//@Order(SecurityProperties.BASIC_AUTH_ORDER - 15)
////@EnableWebSecurity
//public class WebAppSecurityConfig{
//
////    @Bean
////    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
////
////        http
////                .authorizeHttpRequests(authorizeRequests ->
////                        authorizeRequests
////                                .requestMatchers("/camunda/app/**")
////                                .authenticated() // 匹配 "/engine-rest/**" 路径
////                                .anyRequest().permitAll() // 其他请求不需要认证
////                )
////                .httpBasic(Customizer.withDefaults());
////        return http.build();
////    }
//
////    @Bean
////    public FilterRegistrationBean containerBasedAuthenticationFilter(){
////
////        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
////        filterRegistration.setFilter(new ContainerBasedAuthenticationFilter());
////        filterRegistration.setInitParameters(Collections.singletonMap("authentication-provider", "com.camunda.demo.filter.webapp.SpringSecurityAuthenticationProvider"));
////        filterRegistration.setOrder(101); // make sure the filter is registered after the Spring Security Filter Chain
////        filterRegistration.addUrlPatterns("/camunda/app/*");
////        return filterRegistration;
////    }
//}
