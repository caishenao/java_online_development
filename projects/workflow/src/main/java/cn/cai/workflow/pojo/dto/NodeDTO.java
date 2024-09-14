package cn.cai.workflow.pojo.dto;

import lombok.*;
import org.camunda.bpm.model.bpmn.instance.FlowNode;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 结点VO
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class NodeDTO {
    /**
     * 结点id
     */
    private String id;

    /**
     * 结点名称
     */
    private String name;

    /**
     * 结点类型
     */
    private String type;

    /**
     * 结点信息转换器
     * @param node 流程结点
     * @return 结点VO
     */
    public static NodeDTO converterFlowNode(FlowNode node) {
        NodeDTO vo = new NodeDTO();
        vo.setId(node.getId());
        vo.setName(node.getName());
        vo.setType(node.getElementType().getTypeName());
        return vo;
    }

    /**
     * 结点信息集合转换器
     * @param nodes 流程结点集合
     * @return 结点VO集合
     */
    public static List<NodeDTO> converterFlowNodeList(Collection<FlowNode> nodes) {
        return nodes.stream().map(NodeDTO::converterFlowNode).collect(Collectors.toList());
    }


}
