package cn.cai.auth.casdoor.pojo.dto;

import lombok.Data;

@Data
public class ResponseDTO <T, U>{

    /**
     * 状态符
     */
    private String status;

    /**
     * 相应信息
     */
    private String msg;

    private String sub;

    private String name;

    private T data;

    private U data2;
}
