package cn.cai.auth.casdoor.controller;

import cn.cai.auth.api.resq.LoginResp;
import cn.cai.auth.casdoor.service.impl.AuthServiceImpl;
import cn.cai.web.comment.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "认证相关接口")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceImpl authService;

    @GetMapping("/getToken/{code}/{state}")
    @ResponseBody
    public ResponseData<LoginResp> getToken(@PathVariable("code") String code,
                                            @PathVariable("state") String state
    ) {
        LoginResp loginResp = authService.getToken(code, state);
        return ResponseData.success(loginResp);
    }

    @PostMapping("/logout")
    @Operation(summary = "登出")
    public ResponseData<Void> logout() {
        authService.logout();
        return ResponseData.success();
    }

}
