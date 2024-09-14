package cn.cai.workflow.pojo.vo;

import lombok.*;
import org.camunda.bpm.engine.repository.ProcessDefinition;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class ProcessDefinitionVO {

    /**
     * 流程定义ID
     */
    private String id;

    /**
     * 流程定义名称
     */
    private String name;

    /**
     * 流程定义key
     */
    private String key;

    /**
     * 流程定义版本
     */
    private Integer version;

    /**
     * 流程定义名称
     */
    private String resourceName;

    /**
     * 将流程定义转换为自己的VO
     * @param list 流程定义信息
     * @return 流程定义VO
     */
    public static List<ProcessDefinitionVO> convertProcessDefinition(List<ProcessDefinition> list) {
        List<ProcessDefinitionVO> voList = new ArrayList<>(list.size());
        for (ProcessDefinition processDefinition : list) {
            ProcessDefinitionVO vo = new ProcessDefinitionVO();
            vo.setId(processDefinition.getId());
            vo.setName(processDefinition.getName());
            vo.setKey(processDefinition.getKey());
            vo.setVersion(processDefinition.getVersion());
            vo.setResourceName(processDefinition.getResourceName());
            voList.add(vo);
        }
        return voList;
    }
}
