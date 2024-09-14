//package cn.cai.workflow.config;
//
//import cn.cai.workflow.filter.rest.StatelessUserAuthenticationFilter;
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
//@Configuration
////@EnableWebSecurity
//@Order(SecurityProperties.BASIC_AUTH_ORDER - 20)
//public class RestSecurityConfig {
//
////    @Bean
////    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
////        http
////                .authorizeHttpRequests(authorizeRequests ->
////                        authorizeRequests
////                                .requestMatchers("/engine-rest/**").authenticated() // 匹配 "/engine-rest/**" 路径
////                                .anyRequest().permitAll() // 其他请求不需要认证
////                )
////                .csrf(csrf -> csrf.disable()) // 禁用 CSRF
////                .sessionManagement(sessionManagement ->
////                        sessionManagement
////                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 无状态会话管理
////                )
////                .httpBasic(Customizer.withDefaults()); // 启用 HTTP Basic 认证
////        http
////                .authorizeHttpRequests(authorizeRequests ->
////                        authorizeRequests
////                                .requestMatchers("/camunda/app/**")
////                                .authenticated() // 匹配 "/engine-rest/**" 路径
////                                .anyRequest().permitAll() // 其他请求不需要认证
////                )
////                .httpBasic(Customizer.withDefaults());
////
////        return http.build();
////
////    }
//
//    @Bean
//    public FilterRegistrationBean statelessUserAuthenticationFilter() {
//        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
//        filterRegistration.setFilter(new StatelessUserAuthenticationFilter());
//        filterRegistration.setOrder(102); // make sure the filter is registered after the Spring Security Filter Chain
//        filterRegistration.addUrlPatterns("/rest/*");
//        return filterRegistration;
//    }
//
//}