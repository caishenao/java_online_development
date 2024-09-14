package cn.cai.iottools.converter;

import cn.cai.iottools.pojo.entity.Connection;
import cn.cai.iottools.pojo.entity.Protocol;
import cn.cai.iottools.pojo.req.AddConnectionReq;

/**
 * 连接转换器
 *
 * @author caishenao
 */
public class ConnectionConverter {


    /**
     * 连接请求转换为连接实体
     * @param addConnectionReq 新增连接请求
     * @param protocol 协议
     * @return 连接实体
     */
    public static Connection addConnectionReq2Connection(AddConnectionReq addConnectionReq, Protocol protocol) {
        Connection connection = new Connection();
        connection.setName(addConnectionReq.getName());
        connection.setDescription(addConnectionReq.getDescription());
        connection.setProtocolId(protocol.getId());
        connection.setProtocolCode(protocol.getCode());
        return connection;
    }
}
