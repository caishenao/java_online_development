package cn.cai.auth.saToken.service.impl;

import cn.cai.auth.api.resq.LoginResp;
import cn.cai.auth.api.service.BasicAuthService;
import cn.cai.auth.saToken.enums.CertificationTypeEnum;
import cn.cai.auth.saToken.pojo.entity.UserCertificationEntity;
import cn.cai.auth.saToken.pojo.entity.UserEntity;
import cn.cai.auth.saToken.repository.UserCertificationRepository;
import cn.cai.auth.saToken.repository.UserRepository;
import cn.dev33.satoken.stp.StpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 权限服务实现
 * @author 蔡
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements BasicAuthService {

    private final UserRepository userRepository;

    private final UserCertificationRepository userCertificationRepository;

    /**
     * 根据用户名、密码登录
     * @param username 用户名
     * @param password 密码
     * @return 登录响应
     */
    @Override
    public LoginResp loginByUsername(String username, String password) {
        // 1. 根据用户名查询用户信息
        UserEntity userEntity = userRepository.getByPhone(username);
        if (userEntity == null) {
            throw new RuntimeException("用户名错误");
        }
        // 2. 查询用户的认证信息
        UserCertificationEntity certification = userCertificationRepository.getByType(userEntity.getId(), CertificationTypeEnum.USERNAME);
        if (certification == null) {
            throw new RuntimeException("没有该认证方式!");
        }
        String value = certification.getValue();
        if (!value.equals(password)) {
            throw new RuntimeException("密码错误!");
        }
        // 3. 登录成功，返回token
        StpUtil.login(userEntity.getId());
        return LoginResp.builder().accessToken(StpUtil.getTokenValue()).build();
    }

    @Override
    public void logout() {

    }
}
