package cn.cai.workflow.service.impl;

import org.camunda.bpm.container.ExecutorService;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.history.HistoricExternalTaskLog;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.camunda.bpm.engine.variable.value.TypedValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
public class ProcessInstancesServiceImplTest {


    @Autowired
    private HistoryService historyService;

    @Autowired
    private TaskService taskService;



    @Test
    void obtainTheInstanceHistory() {
        // 根据流程实例id获取执行的记录记录
        String id = "03a09dd1-7198-11ef-a26d-02004c4f4f50";
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(id)
                .orderByHistoricActivityInstanceEndTime().asc().list();
        for (HistoricActivityInstance historicActivityInstance : list) {

            String activityName = historicActivityInstance.getActivityName();
            String assignee = historicActivityInstance.getAssignee();
            String executionId = historicActivityInstance.getExecutionId();
            // 获取执行结果
            String activityId = historicActivityInstance.getActivityId();
            Date startTime = historicActivityInstance.getStartTime();
            Date endTime = historicActivityInstance.getEndTime();
            String activityType = historicActivityInstance.getActivityType();
            String taskId = historicActivityInstance.getTaskId();
            if(taskId != null) {
                Map<String, Object> variables = taskService.getVariables(taskId);
                for (String s : variables.keySet()) {
                    Object o = variables.get(s);
                    System.out.println(s + " = " + o);
                }
            }

            System.out.println("activityName = " + activityName + "  activityType = " + activityType + ", assignee = " + assignee + ", executionId = " + executionId + ", activityId = " + activityId + ", startTime = " + startTime + ", endTime = " + endTime);

//            System.out.println("activityName = " + activityName + "  activityType = " + activityType + ", assignee = " + assignee + ", executionId = " + executionId + ", activityId = " + activityId + ", startTime = " + startTime + ", endTime = " + endTime);
        }
    }
}
