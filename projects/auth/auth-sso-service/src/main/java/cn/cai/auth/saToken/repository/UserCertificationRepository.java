package cn.cai.auth.saToken.repository;

import cn.cai.auth.saToken.enums.CertificationTypeEnum;
import cn.cai.auth.saToken.pojo.entity.UserCertificationEntity;
import cn.cai.auth.saToken.pojo.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户认证仓储层定义
 *
 * @author 蔡
 */
public interface UserCertificationRepository extends IService<UserCertificationEntity> {

    /**
     * 查询认证类型
     * @param userId 用户id
     * @param certificationTypeEnum 认证枚举
     * @return 用户认证信息
     */
    UserCertificationEntity getByType(String userId, CertificationTypeEnum certificationTypeEnum);
}
