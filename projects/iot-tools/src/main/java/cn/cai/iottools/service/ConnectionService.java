package cn.cai.iottools.service;

import cn.cai.iottools.pojo.req.AddConnectionReq;

/**
 * 连接管理业务接口层
 *
 * @author caishenao
 */
public interface ConnectionService {


    /**
     * 新增连接
     * @param addConnectionReq 新增连接请求参数
     */
    void addConnection(AddConnectionReq addConnectionReq);
}
