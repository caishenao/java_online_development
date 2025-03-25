package cn.cai.auth.api.service;

import cn.cai.auth.api.resq.LoginResp;

/**
 * 认证服务业务层定义
 * @author 蔡
 */
public interface BasicAuthService {

    /**
     * 根据code获取token
     * @param code code
     * @param state 随机字符串
     * @return token
     */
    default LoginResp getToken(String code, String state){
        return null;
    };


    /**
     * 根据用户名、密码登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录响应
     */
    default LoginResp loginByUsername(String username, String password) {
        return null;
    }

    /**
     * 登出
     */
    void logout();


}
