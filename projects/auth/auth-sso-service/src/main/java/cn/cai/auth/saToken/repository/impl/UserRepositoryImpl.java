package cn.cai.auth.saToken.repository.impl;

import cn.cai.auth.saToken.mapper.UserMapper;
import cn.cai.auth.saToken.pojo.entity.UserEntity;
import cn.cai.auth.saToken.repository.UserRepository;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 用户信息仓储层实现
 * @author 蔡
 */
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl extends ServiceImpl<UserMapper, UserEntity> implements UserRepository {

    private final UserMapper userMapper;


    /**
     * 根据手机号获取用户信息
     *
     * @param phone 手机号
     * @return 用户信息
     */
    @Override
    public UserEntity getByPhone(String phone) {
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getPhone, phone);
        return userMapper.selectOne(queryWrapper);
    }
}
