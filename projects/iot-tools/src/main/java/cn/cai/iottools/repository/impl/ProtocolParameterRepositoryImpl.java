package cn.cai.iottools.repository.impl;

import cn.cai.iottools.mapper.ProtocolParameterMapper;
import cn.cai.iottools.pojo.entity.ProtocolParameter;
import cn.cai.iottools.repository.ProtocolParameterRepository;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 协议参数数据层
 * @author caishenao
 */
@Repository
@AllArgsConstructor
public class ProtocolParameterRepositoryImpl implements ProtocolParameterRepository {

    private ProtocolParameterMapper protocolParameterMapper;

    /**
     * 根据协议id查询协议参数集合
     *
     * @param protocolIdSet 协议id集合
     * @return 协议参数集合
     */
    @Override
    public List<ProtocolParameter> listByProtocolIds(Set<String> protocolIdSet) {
        LambdaQueryWrapper<ProtocolParameter> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(ProtocolParameter::getProtocolId, protocolIdSet);
        List<ProtocolParameter> protocolParameterList = protocolParameterMapper.selectList(wrapper);
        return protocolParameterList;
    }

    /**
     * 根据协议类型id查询协议数据
     *
     * @param protocolId 协议id
     * @return 协议参数集合
     */
    @Override
    public List<ProtocolParameter> listByProtocolId(String protocolId) {
        LambdaQueryWrapper<ProtocolParameter> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProtocolParameter::getProtocolId, protocolId);
        List<ProtocolParameter> protocolParameterList = protocolParameterMapper.selectList(wrapper);
        return protocolParameterList;
    }
}
