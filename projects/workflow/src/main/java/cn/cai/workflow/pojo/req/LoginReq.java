package cn.cai.workflow.pojo.req;

import lombok.Data;

@Data
public class LoginReq {
    private String username;
    private String password;
    private String code;
}
