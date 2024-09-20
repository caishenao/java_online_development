package cn.cai.workflow;

import com.alibaba.fastjson2.JSONObject;
import org.assertj.core.util.Lists;
import org.casbin.casdoor.config.CasdoorAutoConfigure;
import org.casbin.casdoor.config.CasdoorConfiguration;
import org.casbin.casdoor.entity.Enforcer;
import org.casbin.casdoor.entity.Permission;
import org.casbin.casdoor.entity.Role;
import org.casbin.casdoor.entity.User;
import org.casbin.casdoor.service.AuthService;
import org.casbin.casdoor.service.EnforcerService;
import org.casbin.casdoor.service.PermissionService;
import org.casbin.casdoor.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 权限相关测试
 */
@SpringBootTest
public class AutherServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CasdoorAutoConfigure casdoorAutoConfigure;

    @Autowired
    private CasdoorConfiguration casdoorConfiguration;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private EnforcerService enforcerService;

    @Test
    public void getPermissions() throws IOException {
        // 判断是否有权限
        String[] strings  = new String[]{"{r}","dev", "/api/111", "GET"};
        boolean enforce = enforcerService.enforce("admin", "api-model","后端api表", strings);
        System.out.println(enforce);
    }


    @Test
    void testUser() {
        List<User> users = null;
        try {
            users = userService.getUsers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (User user : users) {
            List<Permission> permissions = user.permissions;
            List<Role> roles = user.roles;
            for (Role role : roles) {

            }
            for (Permission permission : permissions) {
                System.out.println(permission);
            }
        }
    }

    @Autowired
    private AuthService authService;
    @Test
    void testAuher() {
        User user = authService.parseJwtToken("");


    }
}
