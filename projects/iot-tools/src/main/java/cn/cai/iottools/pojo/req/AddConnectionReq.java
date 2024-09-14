package cn.cai.iottools.pojo.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

/**
 * 新增请求
 *
 * @author caishenao
 */
@Data
public class AddConnectionReq {

    /**
     * 协议id
     */
    @NotBlank(message = "协议id不能为空")
    private String protocolId;

    /**
     * 连接名称
     */
    @NotBlank(message = "连接名称不能为空")
    private String name;

    /**
     * 连接描述
     */
    private String description;

    @NotNull(message = "连接参数不能为空")
    private Map<String, String> params;

}
