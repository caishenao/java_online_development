package cn.cai.web.comment.pojo;

import lombok.Data;

/**
 * 基础用户信息
 * @author 蔡
 */
@Data
public abstract class BasicUser {

    /**
     * 用户id
     */
    private String id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 头像
     */
    private String avatar;

}
