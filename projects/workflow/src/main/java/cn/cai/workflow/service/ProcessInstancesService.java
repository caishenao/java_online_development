package cn.cai.workflow.service;

import cn.cai.workflow.pojo.dto.HistoricActivityDTO;

import java.util.List;

/**
 * 流程实例业务实现接口层
 */
public interface ProcessInstancesService {

    /**
     * 根据流程定义key和流程定义id获取流程实例列表
     * @param processDefinitionKey 流程定义key
     * @param processDefinitionId 流程定义id
     */
    void listInstance(String processDefinitionKey, String processDefinitionId);

    /**
     * 根据流程实例id获取执行过和当前的任务结点信息
     * @param processInstanceId 流程实例id
     * @return 任务结点信息
     */
    List<HistoricActivityDTO> list(String processInstanceId);


}
