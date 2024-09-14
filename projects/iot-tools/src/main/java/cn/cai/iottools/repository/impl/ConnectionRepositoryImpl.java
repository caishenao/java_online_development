package cn.cai.iottools.repository.impl;

import cn.cai.iottools.mapper.ConnectionMapper;
import cn.cai.iottools.pojo.entity.Connection;
import cn.cai.iottools.repository.ConnectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 连接实例仓储层接口实现
 *
 * @author caishenao
 */
@Repository
@AllArgsConstructor
public class ConnectionRepositoryImpl implements ConnectionRepository {

    private final ConnectionMapper connectionMapper;


    /**
     * 保存连接
     *
     * @param connection 连接
     */
    @Override
    public void save(Connection connection) {
        connectionMapper.insert(connection);
    }
}
