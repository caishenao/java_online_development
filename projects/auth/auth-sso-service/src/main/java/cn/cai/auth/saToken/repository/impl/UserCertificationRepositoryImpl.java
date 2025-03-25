package cn.cai.auth.saToken.repository.impl;

import cn.cai.auth.saToken.enums.CertificationTypeEnum;
import cn.cai.auth.saToken.mapper.UserCertificationMapper;
import cn.cai.auth.saToken.pojo.entity.UserCertificationEntity;
import cn.cai.auth.saToken.repository.UserCertificationRepository;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 用户认证仓储层实现
 *
 * @author 蔡
 */
@Repository
@RequiredArgsConstructor
public class UserCertificationRepositoryImpl extends ServiceImpl<UserCertificationMapper, UserCertificationEntity> implements UserCertificationRepository {

    private final UserCertificationMapper userCertificationMapper;

    /**
     * 查询认证类型
     *
     * @param userId                用户id
     * @param certificationTypeEnum 认证枚举
     * @return 用户认证信息
     */
    @Override
    public UserCertificationEntity getByType(String userId, CertificationTypeEnum certificationTypeEnum) {
        LambdaQueryWrapper<UserCertificationEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserCertificationEntity::getUserId, userId);
        queryWrapper.eq(UserCertificationEntity::getType, certificationTypeEnum);
        return userCertificationMapper.selectOne(queryWrapper);
    }
}
