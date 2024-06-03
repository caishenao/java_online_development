package cn.cai.auth.controller;

import cn.cai.web.comment.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查
 *
 * @author caishenao
 */
@RestController
@Tag(name = "健康检查", description = "健康检查")
public class HealthController {

    @GetMapping("/health")
    @Operation(summary = "健康检查")
    public ResponseData<Void> health() {
        return ResponseData.success();
    }
}
