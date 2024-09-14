package cn.cai.iottools.mapper;

import cn.cai.iottools.pojo.entity.ConnectionParameter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 连接参数接口层实现
 *
 * @author cai
 */
@Mapper
public interface ConnectionParameterMapper extends BaseMapper<ConnectionParameter> {
}
