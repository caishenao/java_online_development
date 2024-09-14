package cn.cai.iottools.converter;

import cn.cai.iottools.pojo.entity.Protocol;
import cn.cai.iottools.pojo.req.AddProtocolReq;
import cn.cai.iottools.pojo.resp.ProtocolResp;

/**
 * 协议类型转换器
 *
 * @author caishenao
 */
public class ProtocolConverter {

    /**
     * 协议新增请求转换为dao
     * @param addProtocolReq 新增协议请求
     * @return 协议dao
     */
    public static Protocol addProtocolReq2Protocol(AddProtocolReq addProtocolReq) {
        Protocol protocol = new Protocol();
        protocol.setCode(addProtocolReq.getCode());
        protocol.setName(addProtocolReq.getName());
        protocol.setDescription(addProtocolReq.getDescription());
        return protocol;
    }

    public static ProtocolResp protocol2ProtocolResp(Protocol protocol) {
        ProtocolResp protocolResp = new ProtocolResp();
        protocolResp.setId(protocol.getId());
        protocolResp.setCode(protocol.getCode());
        protocolResp.setName(protocol.getName());
        protocolResp.setDescription(protocol.getDescription());
        return protocolResp;
    }
}
