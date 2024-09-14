//package cn.cai.workflow.config;
//
//import jakarta.annotation.Resource;
//import org.casbin.casdoor.entity.CasdoorUser;
//import org.casbin.casdoor.service.CasdoorUserService;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//@Service
//public class CamundaUserDetailsService implements UserDetailsService {
//
//
//
////    @Resource
////    private CasdoorUserService casdoorUserService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // 根据 Casdoor 返回的用户名查询 Camunda 用户
////        CasdoorUser user = null;
////        try {
////            user = casdoorUserService.getUser(username);
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
////        if (user != null) {
////            return User.withUsername(user.getName())
////                .password(user.getPassword())
////                .roles("USER")
////                .build();
////        }
//        // 这里可以对接 Camunda 内部用户系统或 Casdoor 提供的用户信息
//        if ("admin".equals(username)) {
//            return User.withUsername("admin")
//                .password("{noop}password")
//                .roles("ADMIN")
//                .build();
//        }
//        throw new UsernameNotFoundException("User not found");
//    }
//}