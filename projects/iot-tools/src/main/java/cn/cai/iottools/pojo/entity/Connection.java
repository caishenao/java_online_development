package cn.cai.iottools.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.annotation.Unique;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.io.Serializable;

/**
 * 连接
 * @author caishenao
 */
@Data
@Table(name = "connection")
@TableName("connection")
public class Connection implements Serializable {

    @TableId(type = IdType.ASSIGN_UUID)
    @Column(name = "id", type = MySqlTypeConstant.VARCHAR, isKey = true,comment = "连接id")
    private String id;

    @Column(name = "protocol_id", type = MySqlTypeConstant.VARCHAR, isNull = false, comment = "协议类型id")
    private String protocolId;

    @Column(name = "protocol_code", type = MySqlTypeConstant.VARCHAR, isNull = false, comment = "协议类型code")
    private String protocolCode;

    @Unique
    @Column(name = "name", type = MySqlTypeConstant.VARCHAR, length = 50,isNull = false, defaultValue = "", comment = "连接名称")
    private String name;

    @Column(name = "description", type = MySqlTypeConstant.VARCHAR, length = 100, defaultValue = "",comment = "连接描述")
    private String description;

    @Column(name = "self_start", type = MySqlTypeConstant.TINYINT, length = 1, defaultValue = "0", comment = "是否开机自启动")
    private Boolean selfStart;

    /**
     * 运行状态
     * 0: 停止
     * 1: 运行
     * 2: 异常
     */
    @Column(name = "run_status", type = MySqlTypeConstant.TINYINT, defaultValue = "0", comment = "运行状态")
    private Integer runStatus;
}
