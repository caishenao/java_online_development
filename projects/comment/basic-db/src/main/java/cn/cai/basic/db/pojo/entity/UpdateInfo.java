package cn.cai.basic.db.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;

/**
 * 基础更新信息
 * @author 蔡
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class UpdateInfo extends OnlyCreateInfo{

    @TableField(value = "updated_id")
    private String updatedId;

    @TableField(value = "updated_time", fill = FieldFill.UPDATE)
    private OffsetDateTime updatedTime;
}
