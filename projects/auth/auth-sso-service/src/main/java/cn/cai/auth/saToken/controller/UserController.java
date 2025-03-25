package cn.cai.auth.saToken.controller;

import cn.cai.auth.api.resq.UserInfoResp;
import cn.cai.auth.saToken.service.impl.UserServiceImpl;
import cn.dev33.satoken.util.SaResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 * @author 蔡
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(description = "用户管理", name = "用户管理")
public class UserController {

    private final UserServiceImpl userService;


    @GetMapping("/info")
    @Operation(summary = "获取用户信息")
    public SaResult info() {
        UserInfoResp userInfo = userService.getUserInfo();
        return SaResult.data(userInfo);
    }


}
