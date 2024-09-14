package cn.cai.iottools.service.impl;

import cn.cai.iottools.converter.ProtocolConverter;
import cn.cai.iottools.converter.ProtocolParameterConverter;
import cn.cai.iottools.mapper.ProtocolMapper;
import cn.cai.iottools.mapper.ProtocolParameterMapper;
import cn.cai.iottools.pojo.entity.Protocol;
import cn.cai.iottools.pojo.entity.ProtocolParameter;
import cn.cai.iottools.pojo.req.AddProtocolReq;
import cn.cai.iottools.pojo.resp.ProtocolParameterResp;
import cn.cai.iottools.pojo.resp.ProtocolResp;
import cn.cai.iottools.repository.ProtocolParameterRepository;
import cn.cai.iottools.service.ProtocolService;
import cn.cai.web.comment.utils.ValidatorUtils;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 协议管理业务层实现
 * @author caishenao
 */
@Service
@AllArgsConstructor
@Slf4j
@Repository
public class ProtocolServiceImpl implements ProtocolService {

    private ProtocolMapper protocolMapper;

    private ProtocolParameterMapper protocolParameterMapper;

    private ProtocolParameterRepository protocolParameterRepository;

    /**
     * 根据协议id查询协议类型和参数
     *
     * @param id 协议类型id
     * @return 协议类型和参数
     */
    @Override
    public ProtocolResp getProtocolById(String id) {
        // 1. 根据id查询协议类型id
        Protocol protocol = protocolMapper.selectById(id);
        if(protocol == null) {
            log.warn("协议类型id不存在: {}", id);
            // TODO 包业务警告类型的异常
            return null;
        }

        // 2. 根据协议类型查询协议参数
        List<ProtocolParameter> protocolParameterList = protocolParameterRepository.listByProtocolId(id);

        // 3. 拼装
        ProtocolResp protocolResp = ProtocolConverter.protocol2ProtocolResp(protocol);
        List<ProtocolParameterResp> protocolParameterRespList = ProtocolParameterConverter.protocolParameterList2ProtocolParameterRespList(protocolParameterList);
        protocolResp.setChildren(protocolParameterRespList);
        return protocolResp;
    }

    /**
     * 新增协议
     *
     * @param addProtocolReq 协议信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addProtocol(AddProtocolReq addProtocolReq) {
        // 1. 注解校验
        ValidatorUtils.validateEntity(addProtocolReq);

        // 2. 将req转换为dao
        Protocol protocol = ProtocolConverter.addProtocolReq2Protocol(addProtocolReq);

        // 3. 新增协议数据
        protocolMapper.insert(protocol);

        // 4. 转换为协议参数
        List<ProtocolParameter> protocolParameterList = ProtocolParameterConverter.addProtocolParameterReqList2ProtocolParameterList(addProtocolReq.getParameterReqList(), protocol.getId());

        // 5. 保存协议参数
        if(!CollectionUtils.isEmpty(protocolParameterList)) {
            protocolParameterMapper.insert(protocolParameterList);
        }
    }

    /**
     * 查询协议和协议参数列表
     *
     * @return 协议列表
     */
    @Override
    public List<ProtocolResp> listProtocol() {
        // 1. 查询协议类型
        List<Protocol> protocolList = protocolMapper.selectList(null);
        // 2. 查询协议参数
        if (CollectionUtils.isEmpty(protocolList)) {
            log.warn("协议列表为空");
            return Lists.newArrayList();
        }

        // 3. 拼装信息
        Set<String> protocolIdSet = protocolList.stream().map(Protocol::getId).collect(Collectors.toSet());
        List<ProtocolParameter> parameterList = protocolParameterRepository.listByProtocolIds(protocolIdSet);
        Map<String, List<ProtocolParameter>> groupByProtocolId = parameterList.stream().collect(Collectors.groupingBy(ProtocolParameter::getProtocolId));

        List<ProtocolResp> protocolRespList = new ArrayList<>(protocolList.size());
        for (Protocol protocol : protocolList) {
            // 转换
            ProtocolResp protocolResp = ProtocolConverter.protocol2ProtocolResp(protocol);
            // 拼装参数
            if (groupByProtocolId.containsKey(protocol.getId())) {
                List<ProtocolParameter> protocolParameterList = groupByProtocolId.get(protocol.getId());
                List<ProtocolParameterResp> protocolParameterRespList = ProtocolParameterConverter.protocolParameterList2ProtocolParameterRespList(protocolParameterList);
                protocolResp.setChildren(protocolParameterRespList);
            }
            protocolRespList.add(protocolResp);
        }
        return protocolRespList;
    }
}
