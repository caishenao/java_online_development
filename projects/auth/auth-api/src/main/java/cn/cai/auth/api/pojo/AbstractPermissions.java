package cn.cai.auth.api.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 权限信息接口
 * @author 蔡
 */
@Setter
@Getter
@Schema(description = "抽象权限信息")
public class AbstractPermissions {

    @Schema(description = "权限id")
    private String id;

    @Schema(description = "权限名称")
    private String name;

    @Schema(description = "权限编码")
    private String code;
}
