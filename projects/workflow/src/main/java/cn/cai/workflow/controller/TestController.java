package cn.cai.workflow.controller;

import cn.cai.workflow.pojo.req.LoginReq;
import cn.cai.workflow.service.CasdoorUserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

import org.casbin.casdoor.config.CasdoorConfiguration;
import org.casbin.casdoor.entity.User;
import org.casbin.casdoor.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {



    private final CasdoorConfiguration casdoorConfiguration;
    private final AuthService authService;

    private final CasdoorUserService casdoorUserService;

    @PostMapping("/login")
    @Operation(summary = "自定义登录至casdoor")
    public ResponseEntity<String> login(@RequestBody LoginReq loginReq) {
        String code = casdoorUserService.login(loginReq);
        String token = authService.getOAuthToken(code, casdoorConfiguration.applicationName);
        String result = "Bearer " + token;
        return ResponseEntity.ok(result);
    }


    @GetMapping("/callback")
    public ResponseEntity<User> callback(@RequestParam("code") String code, @RequestParam("state") String state) {
        String token = "";
        User user = null;
        try {
            token = authService.getOAuthToken(code, state);
            user = authService.parseJwtToken(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(user);
    }

}
