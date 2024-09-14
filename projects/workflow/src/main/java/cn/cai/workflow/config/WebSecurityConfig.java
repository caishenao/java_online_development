//package cn.cai.workflow.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class WebSecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/camunda/**").authenticated()  // 对Camunda相关请求进行认证
//                                .anyRequest().permitAll()                    // 允许其他请求
//                )
//                .oauth2Login(oauth2Login ->
//                        oauth2Login
//                                .loginPage("/oauth2/authorization/oidc")   // OIDC 登录页面
//                                .defaultSuccessUrl("/camunda", true)        // 登录成功后重定向到Camunda
//                );
//        return http.build();
//    }
//}