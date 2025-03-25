package cn.cai.auth.saToken.repository;

import cn.cai.auth.saToken.pojo.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户信息仓储层定义
 *
 * @author 蔡
 */
public interface UserRepository extends IService<UserEntity> {


    /**
     * 根据手机号获取用户信息
     *
     * @param phone 手机号
     * @return 用户信息
     */
    UserEntity getByPhone(String phone);
}
