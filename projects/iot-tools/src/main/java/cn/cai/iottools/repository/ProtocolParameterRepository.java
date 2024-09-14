package cn.cai.iottools.repository;

import cn.cai.iottools.pojo.entity.ProtocolParameter;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;

/**
 * 协议参数数据层
 *
 * @author caishenao
 */
public interface ProtocolParameterRepository {
    /**
     * 根据协议id查询协议参数集合
     * @param protocolIdSet 协议id集合
     * @return 协议参数集合
     */
    List<ProtocolParameter> listByProtocolIds(@NotNull Set<String> protocolIdSet);

    /**
     * 根据协议类型id查询协议数据
     * @param protocolId 协议id
     * @return 协议参数集合
     */
    List<ProtocolParameter> listByProtocolId(String protocolId);
}
