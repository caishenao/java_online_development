package cn.cai.auth.casdoor.service.impl;

import cn.cai.auth.api.resq.LoginResp;
import cn.cai.auth.api.service.BasicAuthService;
import cn.cai.web.comment.utils.ServletUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.casbin.casdoor.config.CasdoorConfiguration;
import org.casbin.casdoor.entity.Token;
import org.casbin.casdoor.entity.User;
import org.casbin.casdoor.service.AuthService;
import org.casbin.casdoor.service.TokenService;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * 认证服务业务层实现
 */
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements BasicAuthService {


    private final TokenService tokenService;
    private final AuthService authService;
    private final CasdoorConfiguration casdoorConfiguration;

    /**
     * 根据code获取token
     * @param code code
     * @param state 随机字符串
     * @return token
     */
    @Override
    public LoginResp getToken(String code, String state) {
        String authToken = authService.getOAuthToken(code, state);
        return LoginResp.builder().accessToken(authToken).build();
    }

    /**
     * 登出
     */
    @Override
    public void logout() {
        String token = ServletUtils.getToken("Authorization", "Bearer ");
        if (StringUtils.isBlank(token)) {
            return;
        }
        User user = authService.parseJwtToken(token);
        Token userToken = new Token();
        userToken.owner = user.owner;
        userToken.name = user.name;
        userToken.application = casdoorConfiguration.getApplicationName();
        userToken.accessToken = token;
        userToken.user = user.name;
        try {
            tokenService.deleteToken(userToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
