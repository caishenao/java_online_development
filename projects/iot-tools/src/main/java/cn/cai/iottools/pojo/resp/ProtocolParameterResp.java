package cn.cai.iottools.pojo.resp;

import lombok.Data;

/**
 * 协议参数响应实体类
 *
 * @author caishenao
 */
@Data
public class ProtocolParameterResp {

    /**
     * 协议参数id
     */
    private String id;

    /**
     * 协议id
     */
    private String protocolId;

    /**
     * 协议参数展示名称
     */
    private String showName;

    /**
     * 协议参数名称
     */
    private String name;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 协议参数类型
     * 1: 字符串
     * 2: 数字
     */
    private Integer type;

    /**
     * 是否必填
     * true: 必填
     * false: 不必填s
     */
    private Boolean isRequired;


}
