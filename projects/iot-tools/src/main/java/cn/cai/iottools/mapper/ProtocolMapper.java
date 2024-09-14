package cn.cai.iottools.mapper;

import cn.cai.iottools.pojo.entity.Protocol;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 协议管理mapper层
 * @author caishenao
 */
@Mapper
public interface ProtocolMapper extends BaseMapper<Protocol> {

}
