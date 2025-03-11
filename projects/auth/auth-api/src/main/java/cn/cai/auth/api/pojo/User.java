package cn.cai.auth.api.pojo;

/**
 * 用户信息接口
 * @author 蔡
 */
public interface User {

    /**
     * 获取用户id
     * @return
     */
    String getId();


    /**
     * 获取用户名
     * @return
     */
    String getName();

    /**
     * 获取用户头像
     * @return
     */
    String getAvatar();

    /**
     * 获取用户密码
     * @return
     */
    String getPassword();

    /**
     * 获取用户手机号
     * @return
     */
    String getPhone();

    /**
     * 获取用户邮箱
     * @return
     */
    String getEmail();

    /**
     * 获取用户盐值
     * @return
     */
    String getSalt();
}
