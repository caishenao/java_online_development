package cn.cai.auth.casdoor.controller;

import cn.cai.auth.api.resq.LoginResp;
import cn.cai.auth.api.resq.UserInfoResp;
import cn.cai.auth.casdoor.service.impl.UserServiceImpl;
import cn.cai.web.comment.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.casbin.casdoor.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户接口
 * @author 蔡
 */
@RestController
@RequestMapping("/user")
@Schema(description = "用户接口")
@RequiredArgsConstructor
public class UserController {


    private final UserServiceImpl userService;


    @GetMapping("/userInfo")
    @Operation(summary = "获取用户信息")
    public ResponseData<UserInfoResp> userInfo() {
        UserInfoResp userInfo = userService.getUserInfo();
        return ResponseData.success(userInfo);
    }

}
