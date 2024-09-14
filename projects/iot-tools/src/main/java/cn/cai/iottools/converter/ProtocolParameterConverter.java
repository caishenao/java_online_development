package cn.cai.iottools.converter;

import cn.cai.iottools.pojo.entity.ProtocolParameter;
import cn.cai.iottools.pojo.req.AddProtocolParameterReq;
import cn.cai.iottools.pojo.resp.ProtocolParameterResp;

import java.util.ArrayList;
import java.util.List;

/**
 * 协议参数转换类
 *
 * @author caishenao
 */
public class ProtocolParameterConverter {

    /**
     * 新增协议参数请求转换为dao
     *
     * @param addProtocolParameterReq 新增协议参数请求
     * @param protocolId              协议id
     * @return 协议参数
     */
    public static ProtocolParameter addProtocolParameterReq2ProtocolParameter(AddProtocolParameterReq addProtocolParameterReq, String protocolId) {
        ProtocolParameter protocolParameter = new ProtocolParameter();
        protocolParameter.setProtocolId(protocolId);
        protocolParameter.setName(addProtocolParameterReq.getName());
        protocolParameter.setShowName(addProtocolParameterReq.getShowName());
        protocolParameter.setType(addProtocolParameterReq.getType());
        protocolParameter.setIsRequired(addProtocolParameterReq.getIsRequired());
        protocolParameter.setDefaultValue(addProtocolParameterReq.getDefaultValue());
        return protocolParameter;
    }

    /**
     * 新增协议参数请求列表转换为协议参数列表
     * @param addProtocolParameterReqList 新增协议参数请求列表
     * @param protocolId 协议id
     * @return 协议参数列表
     */
    public static List<ProtocolParameter> addProtocolParameterReqList2ProtocolParameterList(List<AddProtocolParameterReq> addProtocolParameterReqList, String protocolId) {
        List<ProtocolParameter> protocolParameterList = new ArrayList<>(addProtocolParameterReqList.size());
        for (AddProtocolParameterReq addProtocolParameterReq : addProtocolParameterReqList) {
            ProtocolParameter protocolParameter = addProtocolParameterReq2ProtocolParameter(addProtocolParameterReq, protocolId);
            protocolParameterList.add(protocolParameter);
        }
        return protocolParameterList;
    }


    public static ProtocolParameterResp protocolParameter2ProtocolParameterResp(ProtocolParameter protocolParameter) {
        ProtocolParameterResp protocolParameterResp = new ProtocolParameterResp();
        protocolParameterResp.setId(protocolParameter.getId());
        protocolParameterResp.setName(protocolParameter.getName());
        protocolParameterResp.setShowName(protocolParameter.getShowName());
        protocolParameterResp.setType(protocolParameter.getType());
        protocolParameterResp.setIsRequired(protocolParameter.getIsRequired());
        protocolParameterResp.setDefaultValue(protocolParameter.getDefaultValue());
        return protocolParameterResp;
    }

    public static List<ProtocolParameterResp> protocolParameterList2ProtocolParameterRespList(List<ProtocolParameter> protocolParameterList) {
        List<ProtocolParameterResp> protocolParameterRespList = new ArrayList<>(protocolParameterList.size());
        for (ProtocolParameter protocolParameter : protocolParameterList) {
            ProtocolParameterResp protocolParameterResp = protocolParameter2ProtocolParameterResp(protocolParameter);
            protocolParameterRespList.add(protocolParameterResp);
        }
        return protocolParameterRespList;
    }
}
