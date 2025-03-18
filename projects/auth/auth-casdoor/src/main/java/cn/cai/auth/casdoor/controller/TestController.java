package cn.cai.auth.casdoor.controller;

import cn.cai.web.comment.response.ResponseData;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "测试")
@RefreshScope
public class TestController {

    @Value("${test:pro}")
    private String test;

    @GetMapping("/test")
    public ResponseData<String> test() {
        return ResponseData.success(test);
    }

}
