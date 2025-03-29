package cn.cai.auth.saToken.controller;

import cn.cai.auth.api.req.LoginRequest;
import cn.cai.auth.api.resq.LoginResp;
import cn.cai.auth.saToken.service.impl.AuthServiceImpl;
import cn.dev33.satoken.sso.processor.SaSsoServerProcessor;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.dtflys.forest.annotation.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * SSO-Server端：统一认证中心
 * @author 蔡
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "认证相关接口")
@RequestMapping("/auth")
public class AuthController {


    private final AuthServiceImpl authService;

    @GetMapping
    @Operation(summary = "统一认证地址")
    public Object ssoAuth() {
    // 调用SaSsoServerProcessor类的单例实例的ssoAuth方法，并返回其结果
        return SaSsoServerProcessor.instance.ssoAuth();
    }

    @PostMapping("/loginByPassword")
    @Operation(summary = "登录接口")
    public Object ssoDoLogin(@RequestBody LoginRequest loginRequest) {
        return SaResult.data(authService.loginByUsername(loginRequest.getUsername(), loginRequest.getPassword()));
    }

    @GetMapping("/isLogin")
    @Operation(summary = "是否登录")
    public SaResult isLogin() {
        return SaResult.ok("当前是否登录:" + StpUtil.isLogin());
    }

    // SSO-Server：校验ticket 获取账号id
    @RequestMapping("/checkTicket")
    public Object ssoCheckTicket() {
        return SaSsoServerProcessor.instance.ssoCheckTicket();
    }

    @RequestMapping("/logout")
    @Operation(summary = "登出")
    public Object ssoLogout() {
        return SaSsoServerProcessor.instance.ssoSignout();
    }
}
