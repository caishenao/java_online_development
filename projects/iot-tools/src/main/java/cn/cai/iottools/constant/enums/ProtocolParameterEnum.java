package cn.cai.iottools.constant.enums;

import lombok.Getter;

/**
 * 协议参数枚举
 */
@Getter
public enum ProtocolParameterEnum {


    STRING(1, "字符串"),

    INT(2, "数字");

    /**
     * 编码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String description;

    ProtocolParameterEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }



}
