package cn.cai.iottools.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.mysql.cj.MysqlConnection;
import lombok.Data;

import java.io.Serializable;

/**
 * 连接参数
 * @author caishenao
 */
@Data
@Table(name = "connection_parameter")
@TableName("connection_parameter")
public class ConnectionParameter implements Serializable {

    /**
     * 参数id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @Column(name = "id", comment = "协议id", type = MySqlTypeConstant.VARCHAR, isKey = true)
    private String id;

    /**
     * 连接id
     */
    @Column(name = "connection_id", type = MySqlTypeConstant.VARCHAR, isNull = false, comment = "连接id")
    private String connectionId;

    /**
     * 协议的参数类型
     */
    @Column(name = "protocol_parameter_id", type = MySqlTypeConstant.VARCHAR, isNull = false, comment = "协议参数id")
    private String protocolParameterId;

    /**
     * 连接的实际值
     */
    @Column(name = "connection_value", type = MySqlTypeConstant.VARCHAR, isNull = false, comment = "连接值")
    private String connectionValue;

}
