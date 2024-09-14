package cn.cai.iottools.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.annotation.Unique;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 协议实体
 */
@Data
@ToString
@EqualsAndHashCode
@TableName("protocol")
@Table(name = "protocol")
public class Protocol {

    /**
     * 协议id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @Column(name = "id", comment = "协议id", type = MySqlTypeConstant.VARCHAR, isKey = true)
    private String id;

    /**
     * 协议名称
     */
    @Column(name = "name", type = MySqlTypeConstant.VARCHAR, length = 100, isNull = false, comment = "协议名称")
    private String name;

    /**
     * 协议编码
     */
    @Column(name = "code", comment = "协议编码", type =MySqlTypeConstant.VARCHAR, length = 50, isNull = false)
    @Unique
    private String code;

    /**
     * 描述
     */
    @Column(name = "description", type =MySqlTypeConstant.VARCHAR, length = 255, defaultValue = "", comment = "描述")
    private String description;

}
