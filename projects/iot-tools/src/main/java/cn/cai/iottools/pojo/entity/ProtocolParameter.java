package cn.cai.iottools.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 协议参数实体
 *
 * @author caishenao
 */

@Data
@EqualsAndHashCode
@ToString
@Table(name = "protocol_parameter")
@TableName("protocol_parameter")
public class ProtocolParameter {

    /**
     * 协议参数id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @Column(name = "id", type = MySqlTypeConstant.VARCHAR, isKey = true, comment = "主键id")
    private String id;

    /**
     * 协议id
     */
    @Column(name = "protocol_id", type = MySqlTypeConstant.VARCHAR, isNull = false, comment = "协议id")
    private String protocolId;

    /**
     * 协议参数展示名称
     */
    @Column(name = "show_name", type = MySqlTypeConstant.VARCHAR, isNull = false, length = 100, comment = "协议参数展示名称")
    private String showName;

    /**
     * 协议参数名称
     */
    @Column(name = "name", type = MySqlTypeConstant.VARCHAR, isNull = false, length = 100,defaultValue = "", comment = "协议参数名称")
    private String name;

    /**
     * 默认值
     */
    @Column(name = "default_value", type = MySqlTypeConstant.VARCHAR, length = 100, defaultValue = "", comment = "默认值")
    private String defaultValue;

    /**
     * 协议参数类型
     * 1: 字符串
     * 2: 数字
     */
    @Column(name = "type", type = MySqlTypeConstant.TINYINT, isNull = false, comment = "协议参数类型")
    private Integer type;

    /**
     * 是否必填
     * true: 必填
     * false: 不必填s
     */
    @Column(name = "is_required", type = MySqlTypeConstant.TINYINT, length = 1, isNull = false, comment = "是否必填")
    private Boolean isRequired;

}
