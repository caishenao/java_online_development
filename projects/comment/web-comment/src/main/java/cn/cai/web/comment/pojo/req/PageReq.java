package cn.cai.web.comment.pojo.req;

import lombok.Data;

/**
 * 分页请求参数
 */
@Data
public class PageReq {
    private Integer pageSize = 10;

    private Integer pageNum = 1;
}
