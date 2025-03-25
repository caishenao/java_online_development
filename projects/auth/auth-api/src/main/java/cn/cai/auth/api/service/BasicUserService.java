package cn.cai.auth.api.service;

import cn.cai.auth.api.resq.LoginResp;
import cn.cai.auth.api.resq.UserInfoResp;

/**
 * 用户服务业务层定义
 *
 * @author 蔡
 */
public interface BasicUserService {





    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    UserInfoResp getUserInfo();
}
