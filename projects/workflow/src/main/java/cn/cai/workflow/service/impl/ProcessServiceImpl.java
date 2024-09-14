package cn.cai.workflow.service.impl;

import cn.cai.workflow.pojo.dto.LaneDTO;
import cn.cai.workflow.pojo.dto.NodeDTO;
import cn.cai.workflow.pojo.vo.ProcessDetailsVO;
import cn.cai.workflow.service.ProcessService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.impl.identity.Authentication;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.repository.ProcessDefinitionQuery;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.*;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 流程定义业务层接口实现
 */
@Service
@AllArgsConstructor
public class ProcessServiceImpl implements ProcessService {

    private final RepositoryService repositoryService;

    private final IdentityService identityService;

    /**
     * 获取流程定义的详情信息
     *
     * @param processDefinitionKey 流程定义key
     * @param processDefinitionId  流程定义id（可以为空）
     * @return 流程定义详情信息
     */
    @Override
    public List<LaneDTO> getDetails(String processDefinitionKey, String processDefinitionId) {

        // 1. 查询流程定义信息
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey);
        ProcessDefinition processDefinition = null;
        if (StringUtils.isNotBlank(processDefinitionId)) {
            query.processDefinitionId(processDefinitionId);
        }else {
            query.latestVersion();
        }
        processDefinition = query.singleResult();

        // 2. 根据流程定义信息查询详细信息
        BpmnModelInstance bpmnModelInstance = repositoryService.getBpmnModelInstance(processDefinition.getId());
        Collection<Process> processCollection = bpmnModelInstance.getModelElementsByType(Process.class);
        List<LaneDTO> laneDTOList = new ArrayList<>();
        for (Process process : processCollection) {
            Collection<LaneSet> laneSets = process.getChildElementsByType(LaneSet.class);
            // 获取区段
            for (LaneSet laneSet : laneSets) {
                Collection<Lane> lanes = laneSet.getLanes();
                for (Lane lane : lanes) {
                    Collection<FlowNode> flowNodeRefs = lane.getFlowNodeRefs();
                    LaneDTO laneDTO = LaneDTO.converterLane(lane);
                    List<NodeDTO> nodeDTOS = NodeDTO.converterFlowNodeList(flowNodeRefs);
                    laneDTO.setNodeList(nodeDTOS);
                    laneDTOList.add(laneDTO);
                }
            }
        }
        return laneDTOList;
    }

    /**
     * 获取所有流程变量信息
     *
     * @return 流程变量信息
     */
    @Override
    public List<ProcessDefinition> list() {
        // 获取所有流程定义
        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery().latestVersion().list();
        return processDefinitionList;
    }
}
