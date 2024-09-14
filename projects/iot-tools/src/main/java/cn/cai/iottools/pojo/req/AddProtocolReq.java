package cn.cai.iottools.pojo.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 新增协议请求
 *
 * @author caishenao
 */
@Data
public class AddProtocolReq implements Serializable {

    /**
     * 协议名称
     */
    @NotBlank(message = "协议名称不能为空！")
    private String name;

    /**
     * 协议编码
     */
    @NotBlank(message = "协议编码不能为空！")
    private String code;

    /**
     * 协议描述
     */
    private String description;


    @NotNull(message = "协议参数不能为空!")
    private List<AddProtocolParameterReq> parameterReqList = new ArrayList<>();

}
