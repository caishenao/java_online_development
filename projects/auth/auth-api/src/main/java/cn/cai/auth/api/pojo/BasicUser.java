package cn.cai.auth.api.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 抽象用户信息
 * @author 蔡
 */
@Schema(description = "抽象用户信息")
@Setter
@Getter
public class BasicUser {

    @Schema(description = "用户id")
    private String userId;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "手机号")
    private String phone;
}
