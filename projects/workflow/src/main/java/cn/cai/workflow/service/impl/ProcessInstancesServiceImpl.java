package cn.cai.workflow.service.impl;

import cn.cai.workflow.pojo.dto.HistoricActivityDTO;
import cn.cai.workflow.pojo.dto.VariableDTO;
import cn.cai.workflow.service.ProcessInstancesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 流程实例业务层接口实现
 */
@Service
@AllArgsConstructor
@Slf4j
public class ProcessInstancesServiceImpl implements ProcessInstancesService {

    private final HistoryService historyService;

    private final TaskService taskService;

    /**
     * 根据流程定义key和流程定义id获取流程实例列表
     *
     * @param processDefinitionKey 流程定义key
     * @param processDefinitionId  流程定义id
     */
    @Override
    public void listInstance(String processDefinitionKey, String processDefinitionId) {
        // 1. 查询历史流程信息
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .processDefinitionId(processDefinitionId)
                .orderByHistoricActivityInstanceEndTime().asc()
                .list();
        // 2. 遍历输出
        for (HistoricActivityInstance historicActivityInstance : list) {
            String activityId = historicActivityInstance.getActivityId();
        }
    }

    /**
     * 根据流程实例id获取执行过和当前的任务结点信息
     *
     * @param processInstanceId 流程实例id
     */
    @Override
    public List<HistoricActivityDTO> list(String processInstanceId) {
        // 1. 获取流程实例历史
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                .orderByHistoricActivityInstanceEndTime().asc().list();

        List<HistoricActivityDTO> historicActivityDTOList = new ArrayList<>(list.size());

        // 2. 遍历输出
        for (HistoricActivityInstance historicActivityInstance : list) {
            HistoricActivityDTO historicActivityDTO = HistoricActivityDTO.converterHistoricActivityInstance(historicActivityInstance);
            if(StringUtils.isNotBlank(historicActivityDTO.getTaskId())) {
                Map<String, Object> variables = taskService.getVariables(historicActivityDTO.getTaskId());
                List<VariableDTO> variableDTOS = VariableDTO.converterVariablesMap(variables);
                historicActivityDTO.setVariableList(variableDTOS);
            }
            historicActivityDTOList.add(historicActivityDTO);
        }

        // 3. 返回
        return historicActivityDTOList;
    }
}
