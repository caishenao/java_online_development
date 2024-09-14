package cn.cai.workflow.pojo.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class VariableDTO {

    /**
     * 变量名称
     */
    private String name;

    /**
     * 变量值
     */
    private String value;

    /**
     * DTO转换器
     * @param variableMap 变量map
     * @return 变量集合
     */
    public static List<VariableDTO> converterVariablesMap(Map<String, Object> variableMap) {
        if(CollectionUtils.isEmpty(variableMap)) {
            return Collections.emptyList();
        }
        List<VariableDTO> dtoList = new ArrayList<>();
        for (String name : variableMap.keySet()) {
            VariableDTO dto = new VariableDTO();
            dto.setName(name);
            dto.setValue(variableMap.get(name).toString());
            dtoList.add(dto);
        }
        return dtoList;
    }
}
