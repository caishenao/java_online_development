package cn.cai.workflow.pojo.dto;

import lombok.*;
import org.camunda.bpm.engine.history.HistoricActivityInstance;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class HistoricActivityDTO {

    /**
     * 流程实例id
     */
    private String id;

    /**
     * 流程定义id
     */
    private String activityId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 流程实例id
     */
    private String executionId;

    /**
     * 执行人id
     */
    private String assignee;

    /**
     * 执行器id
     */
    private String executorId;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 活动结点类型
     */
    private String activityType;

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 变量集合
     */
    private List<VariableDTO> variableList = Collections.emptyList();

    /**
     * 转换器转换为
     * @param historicActivityInstance 历史流程定义
     * @return DTO
     */
    public static HistoricActivityDTO converterHistoricActivityInstance(HistoricActivityInstance historicActivityInstance) {
        HistoricActivityDTO historicActivityDTO = new HistoricActivityDTO();
        historicActivityDTO.setId(historicActivityInstance.getId());
        historicActivityDTO.setActivityId(historicActivityInstance.getActivityId());
        historicActivityDTO.setActivityName(historicActivityInstance.getActivityName());
        historicActivityDTO.setExecutionId(historicActivityInstance.getExecutionId());
        historicActivityDTO.setAssignee(historicActivityInstance.getAssignee());
        historicActivityDTO.setExecutorId(historicActivityInstance.getAssignee());
        historicActivityDTO.setStartTime(historicActivityInstance.getStartTime());
        historicActivityDTO.setEndTime(historicActivityInstance.getEndTime());
        historicActivityDTO.setActivityType(historicActivityInstance.getActivityType());
        historicActivityDTO.setTaskId(historicActivityInstance.getTaskId());
        return historicActivityDTO;
    }


}
