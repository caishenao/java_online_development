package cn.cai.iottools.service.impl;

import cn.cai.iottools.converter.ConnectionConverter;
import cn.cai.iottools.pojo.entity.Connection;
import cn.cai.iottools.pojo.entity.Protocol;
import cn.cai.iottools.pojo.entity.ProtocolParameter;
import cn.cai.iottools.pojo.req.AddConnectionReq;
import cn.cai.iottools.repository.ConnectionRepository;
import cn.cai.iottools.repository.ProtocolParameterRepository;
import cn.cai.iottools.repository.ProtocolRepository;
import cn.cai.iottools.service.ConnectionService;
import cn.cai.web.comment.utils.ValidatorUtils;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 连接管理业务层实现
 *
 * @author caishenao
 */
@Service
@Slf4j
@AllArgsConstructor
public class ConnectionServiceImpl implements ConnectionService {


    private final ConnectionRepository connectionRepository;

    private final ProtocolRepository protocolRepository;

    private final ProtocolParameterRepository protocolParameterRepository;

    /**
     * TODO 新增连接
     *
     * @param addConnectionReq 新增连接请求参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addConnection(AddConnectionReq addConnectionReq) {
        // 1. 注解校验
        ValidatorUtils.validateEntity(addConnectionReq);
        // 2. 业务校验
        // 2.1 查询协议是否存在
        Protocol protocol = protocolRepository.getById(addConnectionReq.getProtocolId());
        if (protocol == null) {
            log.error("协议不存在，协议id：{}", addConnectionReq.getProtocolId());
            // TODO 抛出自定义异常
            throw new RuntimeException("协议不存在");
        }
        // 2.2 根据协议获取协议参数
        List<ProtocolParameter> protocolParameterList = protocolParameterRepository.listByProtocolId(protocol.getId());

        // 2.3 校验参数和请求是否需要
        Map<String, String> actualParameters = verifyTheParameters(protocolParameterList, addConnectionReq.getParams());

        // 3. 转换为连接对象和连接参数存储
        Connection connection = ConnectionConverter.addConnectionReq2Connection(addConnectionReq, protocol);
        connectionRepository.save(connection);
        // 3.1

        // 4. 判断是否自启动
        // 4.1 自启动：根据协议code调用工厂获取
    }

    /**
     * 校验新增连接的参数
     * @param protocolParameterList 协议参数集合
     * @param params 参数
     * @return 返回实际参数
     */
    private Map<String, String> verifyTheParameters(List<ProtocolParameter> protocolParameterList, @NotNull(message = "连接参数不能为空") Map<String, String> params) {
        Map<String, ProtocolParameter> defineParameterMap = protocolParameterList.stream().collect(Collectors.toMap(ProtocolParameter::getName, Function.identity()));
        Map<String, String> actualParameters = new HashMap<>();
        for (String name : defineParameterMap.keySet()) {
            // 判断入参中是否有该参数
            if (params.containsKey(name) && StringUtils.isNotBlank(params.get(name))) {
                // 存在，并且参数不为空，存入实际值
                actualParameters.put(name, params.get(name));
            }else {
                ProtocolParameter defineParameter = defineParameterMap.get(name);
                if (defineParameter.getIsRequired()) {
                    // 必填
                    log.error("必填参数缺失，参数名：{}", name);
                    throw new RuntimeException("必填参数缺失");
                }else if (StringUtils.isNotBlank(defineParameter.getDefaultValue())){
                    // 非必填，有默认值，存入默认值
                    actualParameters.put(name, defineParameter.getDefaultValue());
                }
            }
        }
        return actualParameters;
    }

}
