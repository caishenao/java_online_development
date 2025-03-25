package cn.cai.auth.api.resq;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(name = "登录响应", description = "登录响应")
@Builder
public class LoginResp {

    @Schema(description = "请求token")
    private String accessToken;
}
