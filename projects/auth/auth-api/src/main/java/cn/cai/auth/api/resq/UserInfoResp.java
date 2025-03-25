package cn.cai.auth.api.resq;

import cn.cai.auth.api.pojo.BasicRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@Schema(description = "用户信息")
public class UserInfoResp {

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private String userId;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;

    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "电话")
    private String phone;

    /**
     * 角色集合
     */
    private List<BasicRole> roles = Collections.emptyList();
}
