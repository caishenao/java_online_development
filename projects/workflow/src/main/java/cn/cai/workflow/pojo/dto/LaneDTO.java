package cn.cai.workflow.pojo.dto;

import lombok.*;
import org.camunda.bpm.model.bpmn.instance.Lane;

import java.util.Collections;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class LaneDTO {

    /**
     * 泳道id
     */
    private String id;

    /**
     * 泳道名称
     */
    private String name;

    /**
     * 结点id
     */
    private List<NodeDTO> nodeList = Collections.emptyList();

    /**
     * 泳道转换器
     * @param lane 泳道
     * @return LaneVO
     */
    public static LaneDTO converterLane(Lane lane){
        LaneDTO vo = new LaneDTO();
        vo.setId(lane.getId());
        vo.setName(lane.getName());
        return vo;
    }
}
