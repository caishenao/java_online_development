package cn.cai.auth.saToken.service.impl;

import cn.cai.auth.api.resq.UserInfoResp;
import cn.cai.auth.api.service.BasicUserService;
import cn.cai.auth.saToken.pojo.entity.UserEntity;
import cn.cai.auth.saToken.repository.UserRepository;
import cn.dev33.satoken.stp.StpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements BasicUserService {


    private final UserRepository userRepository;

    /**
     * 获取用户信息
     * @return 用户信息
     */
    @Override
    public UserInfoResp getUserInfo() {
        // 获取redis中的信息
        String loginDevice = StpUtil.getLoginDevice();
        log.info("登录设备:{}", loginDevice);
        String loginId = (String)StpUtil.getLoginId();
        log.info("登录id:{}", loginId);
        UserEntity loginUser = userRepository.getById(loginId);
        UserInfoResp build = UserInfoResp.builder()
                .userId(loginUser.getId())
                .username(loginUser.getName())
                .avatar(loginUser.getAvatar())
                .phone(loginUser.getPhone())
                .build();
        log.info("用户信息:{}", loginUser);
        return build;
    }
}
