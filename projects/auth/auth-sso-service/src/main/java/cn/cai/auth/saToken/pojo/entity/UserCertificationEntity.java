package cn.cai.auth.saToken.pojo.entity;

import cn.cai.auth.saToken.enums.CertificationTypeEnum;
import cn.cai.basic.db.pojo.entity.UpdateInfo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户认证信息实体
 * @author 蔡
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "用户认证信息")
@TableName(value = "user_certification",autoResultMap = true)
public class UserCertificationEntity extends UpdateInfo {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Schema(description = "主键")
    private String id;

    @TableField(value = "user_id")
    @Schema(description = "用户id")
    private String userId;

    @TableField(value = "type")
    @Schema(description = "认证类型")
    private CertificationTypeEnum type;

    @TableField(value = "value")
    @Schema(description = "认证值")
    private String value;

    @TableField(value = "last_value")
    @Schema(description = "上次认证值")
    private String lastValue;
}
