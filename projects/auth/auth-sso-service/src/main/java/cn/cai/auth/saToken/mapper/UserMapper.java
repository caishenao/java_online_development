package cn.cai.auth.saToken.mapper;

import cn.cai.auth.saToken.pojo.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户mapper
 * @author 蔡
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
