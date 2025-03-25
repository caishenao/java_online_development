package cn.cai.basic.db.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.OffsetDateTime;

/**
 * 基础创建信息
 * @author 蔡
 */
@Data
@Schema(description = "基础创建信息")
public abstract class OnlyCreateInfo {

    @TableField("created_id")
    private String createdId;

    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private OffsetDateTime createdTime;
}
