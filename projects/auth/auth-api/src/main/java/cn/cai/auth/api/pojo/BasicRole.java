package cn.cai.auth.api.pojo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 蔡
 */
@Setter
@Getter
@Schema(description = "抽象角色信息")
public class BasicRole {
    @Schema(description = "角色id")
    private String roleId;

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "角色编码")
    private String code;
}
