package cn.cai.auth.casdoor.controller;

import cn.cai.web.comment.response.ResponseData;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.casbin.casdoor.entity.User;
import org.casbin.casdoor.service.AuthService;
import org.casbin.casdoor.service.TokenService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "认证相关接口")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final TokenService tokenService;

    @GetMapping("/getToken/{code}/{state}")
    @ResponseBody
    public ResponseData<String> getToken(@PathVariable("code") String code,
                           @PathVariable("state") String state
    ) {
        String authToken = authService.getOAuthToken(code, state);
        return ResponseData.success(authToken);
    }


    @GetMapping("/userInfo")
    @ResponseBody
    public ResponseData<User> userInfo(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        User user = authService.parseJwtToken(token);
        return ResponseData.success(user);
    }

}
