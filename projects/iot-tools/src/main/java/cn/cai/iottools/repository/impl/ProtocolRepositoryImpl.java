package cn.cai.iottools.repository.impl;

import cn.cai.iottools.mapper.ProtocolMapper;
import cn.cai.iottools.pojo.entity.Protocol;
import cn.cai.iottools.repository.ProtocolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 协议管理数据仓储层接口实现
 *
 * @author caishenao
 */
@Repository
@AllArgsConstructor
public class ProtocolRepositoryImpl implements ProtocolRepository {

    private ProtocolMapper protocolMapper;

    /**
     * 根据id查询协议类型数据
     *
     * @param protocolId 协议id
     * @return 协议类型数据
     */
    @Override
    public Protocol getById(String protocolId) {
        Protocol protocol = protocolMapper.selectById(protocolId);
        return protocol;
    }
}
