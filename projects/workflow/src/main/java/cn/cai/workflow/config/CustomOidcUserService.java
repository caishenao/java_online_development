//package cn.cai.workflow.config;
//
//import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
//import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.oidc.user.OidcUser;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomOidcUserService extends OidcUserService {
//
//    @Override
//    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
//        OidcUser oidcUser = super.loadUser(userRequest);
//
//        // 获取用户的OIDC信息，并在Camunda中创建/匹配用户
//        String username = oidcUser.getName();  // OIDC返回的用户名
//        String email = oidcUser.getEmail();    // 获取用户的邮箱等其他信息
//
//        // 根据用户名或邮箱在Camunda用户系统中进行匹配
//        // 若匹配成功，返回相应的Camunda用户
//        // 例如：UserDetails userDetails = camundaUserService.loadUserByUsername(username);
//
//        return oidcUser; // 你可以根据需要扩展该方法
//    }
//}