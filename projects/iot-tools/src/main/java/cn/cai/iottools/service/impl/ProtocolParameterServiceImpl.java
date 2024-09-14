package cn.cai.iottools.service.impl;

import cn.cai.iottools.converter.ProtocolParameterConverter;
import cn.cai.iottools.pojo.entity.ProtocolParameter;
import cn.cai.iottools.pojo.resp.ProtocolParameterResp;
import cn.cai.iottools.repository.ProtocolParameterRepository;
import cn.cai.iottools.service.ProtocolParameterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 协议参数业务实现层
 *
 * @author caishenao
 */
@Service
@AllArgsConstructor
@Slf4j
public class ProtocolParameterServiceImpl implements ProtocolParameterService {

    private ProtocolParameterRepository protocolParameterRepository;

    /**
     * 根据协议id查询协议参数
     *
     * @param protocolId 协议id
     * @return 协议参数列表
     */
    @Override
    public List<ProtocolParameterResp> listByProtocolId(String protocolId) {
        List<ProtocolParameter> protocolParameterList = protocolParameterRepository.listByProtocolId(protocolId);
        List<ProtocolParameterResp> protocolParameterRespList = ProtocolParameterConverter.protocolParameterList2ProtocolParameterRespList(protocolParameterList);
        return protocolParameterRespList;
    }
}
