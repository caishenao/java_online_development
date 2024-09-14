package cn.cai.iottools.controller;
//
//import cn.cai.web.comment.response.ResponseData;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@AllArgsConstructor
//@Tag(name = "健康检查", description = "健康检查")
//@NoArgsConstructor
//@RefreshScope
//@Slf4j
//public class HealthController {
//
//
//    @Value("${name}")
//    private String name;
//
//    @GetMapping("/health")
//    @Operation(summary = "健康请求")
//    public ResponseData<String> health() {
//        log.info("请求健康请求");
//        return ResponseData.success(name + " is running");
//    }
//}
