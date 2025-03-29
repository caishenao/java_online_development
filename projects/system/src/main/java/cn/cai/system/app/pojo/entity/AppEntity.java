package cn.cai.system.app.pojo.entity;

import cn.cai.basic.db.pojo.entity.UpdateInfo;
import cn.cai.system.app.enums.AppTypeEnum;
import cn.cai.web.comment.pojo.req.PageReq;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "应用实体")
public class AppEntity extends UpdateInfo {

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String name;

    private String code;

    private String description;

    private String icon;

    private String url;

    private String defaultHome;

    private Integer sort;

    private AppTypeEnum type;
}
