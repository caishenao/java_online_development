package cn.cai.workflow.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.casbin.casdoor.service.AuthService;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@Tag(name = "登录", description = "登录")
@AllArgsConstructor
public class Login {

    private final AuthService authService;

    @GetMapping("/login")
    public void getUser(HttpServletResponse response) {
        String signinUrl = authService.getSigninUrl("http://localhost:8080/test/callback");
        System.out.println(signinUrl);
        // 重定向
        try {
            response.sendRedirect(signinUrl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
