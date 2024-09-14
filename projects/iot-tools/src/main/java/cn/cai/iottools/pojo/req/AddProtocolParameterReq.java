package cn.cai.iottools.pojo.req;

import cn.cai.iottools.constant.enums.ProtocolParameterEnum;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 协议参数新增请求
 *
 * @author caishenao
 */
@Data
public class AddProtocolParameterReq {

    /**
     * 协议参数展示名称
     */
    @NotBlank(message = "协议参数展示名称不能为空")
    private String showName;

    /**
     * 协议参数名称
     */
    @NotBlank(message = "协议参数名称不能为空")
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
    @NotBlank(message = "协议参数类型不能为空")
    private Integer type = ProtocolParameterEnum.STRING.getCode();

    /**
     * 是否必填
     * true: 必填
     * false: 不必填
     */
    private Boolean isRequired = Boolean.FALSE;
}
