package cn.cai.auth.controller;

import cn.cai.web.comment.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查
 *
 * @author caishenao
 */
@RestController
@Tag(name = "健康检查", description = "健康检查")
@Slf4j
public class HealthController {

    @GetMapping("/health")
    @Operation(summary = "健康检查")
    public ResponseData<Void> health() {
        log.info("访问了健康检查接口");
        log.error("这是错误信息");
        log.warn("这是警告信息");
        log.debug("这是调试信息");
        return ResponseData.success();
    }
}
