package cn.cai.workflow.service;

import cn.cai.workflow.pojo.dto.LaneDTO;
import cn.cai.workflow.pojo.vo.ProcessDefinitionVO;
import cn.cai.workflow.pojo.vo.ProcessDetailsVO;
import org.camunda.bpm.engine.repository.ProcessDefinition;

import java.util.List;

/**
 * 自定义流程定义业务层接口
 */
public interface ProcessService {

    /**
     * 获取流程定义的详情信息
     * @param processDefinitionKey 流程定义key
     * @param processDefinitionId 流程定义id（可以为空）
     * @return 流程定义详情信息
     */
    List<LaneDTO> getDetails(String processDefinitionKey, String processDefinitionId);


    /**
     * 获取所有流程变量信息
     * @return 流程变量信息
     */
    List<ProcessDefinition> list();


}
