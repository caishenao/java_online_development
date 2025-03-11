package cn.cai.example.controller;

import cn.cai.web.comment.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "测试")
@RefreshScope
public class ExampleController {


    @Value("${env:code}")
    private String env;

    @GetMapping("/test")
    @Operation(summary = "测试")
    public ResponseData<String> test(){
        return ResponseData.success("test");
    }


    @GetMapping("/testConfig")
    @Operation(summary = "测试配置")
    public ResponseData<String> testConfig(){
        return ResponseData.success(env);
    }
}
