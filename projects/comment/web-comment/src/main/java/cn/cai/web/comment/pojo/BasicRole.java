package cn.cai.web.comment.pojo;

import lombok.Data;

/**
 * 基础角色
 * @author 蔡
 */
@Data
public abstract class BasicRole {
    /**
     * id
     */
    private String id;

    /**
     * 名称
     */
    private String name;
}
