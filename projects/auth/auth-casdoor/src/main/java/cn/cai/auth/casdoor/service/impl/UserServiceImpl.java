package cn.cai.auth.casdoor.service.impl;

import cn.cai.auth.api.pojo.BasicRole;
import cn.cai.auth.api.resq.LoginResp;
import cn.cai.auth.api.resq.UserInfoResp;
import cn.cai.auth.api.service.BasicUserService;
import cn.cai.web.comment.utils.ServletUtils;
import lombok.RequiredArgsConstructor;
import org.casbin.casdoor.entity.Role;
import org.casbin.casdoor.entity.User;
import org.casbin.casdoor.service.AuthService;
import org.casbin.casdoor.service.TokenService;
import org.casbin.casdoor.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 用户服务业务层实现
 * @author 蔡
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements BasicUserService {

    private final AuthService authService;

    /**
     * 获取用户信息
     * @return 用户信息
     */
    @Override
    public UserInfoResp getUserInfo() {
        // 1. 获取token
        String token = ServletUtils.getToken("Authorization", "Bearer ");
        User user = authService.parseJwtToken(token);

        // 2. 获取用户信息
        if (CollectionUtils.isEmpty(user.roles)) {
            user.roles = Collections.emptyList();
        }
        List<BasicRole> roleList = new ArrayList<>(user.roles.size());
        for (Role role : user.roles) {
            BasicRole basicRole = new BasicRole();
            basicRole.setCode(role.name);
            basicRole.setName(role.displayName);
            roleList.add(basicRole);
        }
        return UserInfoResp.builder()
                .userId(user.id)
                .username(user.name)
                .phone(user.phone)
                .avatar(user.avatar)
                .roles(roleList)
                .build();
    }
}
