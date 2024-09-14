package cn.cai.iottools.repository;

import cn.cai.iottools.pojo.entity.Connection;

/**
 * 连接数据仓储层
 * @author caishenao
 */
public interface ConnectionRepository {
    /**
     * 保存连接
     * @param connection 连接
     */
    void save(Connection connection);
}
