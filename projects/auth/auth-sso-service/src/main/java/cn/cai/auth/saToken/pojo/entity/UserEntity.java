package cn.cai.auth.saToken.pojo.entity;

import cn.cai.basic.db.pojo.entity.UpdateInfo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户实体
 * @author 31941
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "auth_user",autoResultMap = true)
public class UserEntity extends UpdateInfo {

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @Schema(description = "名称")
    @TableField(value = "name")
    private String name;

    @Schema(description = "手机号")
    @TableField(value = "phone")
    private String phone;

    @Schema(description = "头像")
    @TableField(value = "avatar")
    private String avatar;

    @Schema(description = "盐")
    @TableField(value = "salt")
    private String salt;

    @Schema(description = "默认app")
    @TableField(value = "default_app_id")
    private String defaultAppId;

    @Schema(description = "默认首页")
    @TableField(value = "default_home")
    private String defaultHome;
}
