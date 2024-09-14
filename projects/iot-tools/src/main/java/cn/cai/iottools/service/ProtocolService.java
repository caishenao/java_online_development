package cn.cai.iottools.service;


import cn.cai.iottools.pojo.req.AddProtocolReq;
import cn.cai.iottools.pojo.resp.ProtocolResp;

import java.util.List;

/**
 * 协议业务层
 *
 * @author caishenao
 */
public interface ProtocolService {

    /**
     * 根据协议id查询协议类型和参数
     * @param id 协议类型id
     * @return 协议类型和参数
     */
    ProtocolResp getProtocolById(String id);

    /**
     * 新增协议
     * @param addProtocolReq 协议信息
     */
    void addProtocol(AddProtocolReq addProtocolReq);

    /**
     * 查询协议和协议参数列表
     *
     * @return 协议列表
     */
    List<ProtocolResp> listProtocol();


}
