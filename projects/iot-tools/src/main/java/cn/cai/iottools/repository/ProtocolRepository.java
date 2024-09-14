package cn.cai.iottools.repository;

import cn.cai.iottools.pojo.entity.Protocol;

/**
 * 协议管理数据仓储层接口
 * @author caishenao
 */
public interface ProtocolRepository {

    /**
     * 根据id查询协议类型数据
     * @param protocolId 协议id
     * @return 协议类型数据
     */
    Protocol getById(String protocolId);
}
