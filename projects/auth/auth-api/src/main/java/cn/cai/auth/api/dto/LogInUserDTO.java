package cn.cai.auth.api.dto;

import cn.cai.auth.api.pojo.User;

/**
 * 登录用户信息接口
 * @author 蔡
 */
public interface LogInUserDTO extends User {

    String getIp();

    String getToken();

    Long getExpireTime();

    String getRefreshToken();

    Long getRefreshExpireTime();


}
