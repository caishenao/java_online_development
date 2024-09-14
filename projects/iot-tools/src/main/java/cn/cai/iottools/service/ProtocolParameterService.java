package cn.cai.iottools.service;

import cn.cai.iottools.pojo.resp.ProtocolParameterResp;

import java.util.List;

/**
 * 协议参数业务接口
 *
 * @author caishenao
 */
public interface ProtocolParameterService {
    /**
     * 根据协议id查询协议参数
     * @param protocolId 协议id
     * @return 协议参数列表
     */
    List<ProtocolParameterResp> listByProtocolId(String protocolId);
}
