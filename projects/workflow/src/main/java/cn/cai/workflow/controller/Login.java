package cn.cai.workflow.controller;

import cn.cai.web.comment.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.casbin.casdoor.entity.User;
import org.casbin.casdoor.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@Tag(name = "登录", description = "登录")
@AllArgsConstructor
@Slf4j
public class Login {

    private final AuthService authService;

    @GetMapping("/login")
    @Operation(summary = "根据验证码获取数据")
    @Parameters({
            @Parameter(name = "code", description = "授权码"),
            @Parameter(name = "state", description = "随机字符串")
    })
    public ResponseData<String> login(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state) {
        String token = authService.getOAuthToken(code, state);
        User user = authService.parseJwtToken(token);
        log.info("用户信息:{}", user);
        // 对用户信息
        return ResponseData.success(token);
    }

}
