package cn.cai.workflow.serviceTask;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 分配车辆
 */
@Service("assignVehicle")
@Slf4j
public class AssignVehicle implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Map<String, Object> variables = execution.getVariables();
        log.info("执行分配车辆任务，参数：{}", variables);
        log.info("自动分配车辆，将车辆与货箱绑定");
    }
}
