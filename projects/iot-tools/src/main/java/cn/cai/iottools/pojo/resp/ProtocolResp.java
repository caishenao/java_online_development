package cn.cai.iottools.pojo.resp;

import lombok.Data;
import java.util.Collections;
import java.util.List;

/**
 * 协议响应数据
 *
 * @author caishenao
 */
@Data
public class ProtocolResp {


    /**
     * 协议id
     */
    private String id;

    /**
     * 协议名称
     */
    private String name;

    /**
     * 协议编码
     */
    private String code;

    /**
     * 描述
     */
    private String description;

    /**
     * 协议参数
     */
    private List<ProtocolParameterResp> children = Collections.emptyList();
}
