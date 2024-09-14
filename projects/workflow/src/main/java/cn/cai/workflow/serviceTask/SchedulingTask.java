package cn.cai.workflow.serviceTask;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 调度任务生成订单
 */
@Service("schedulingTask")
@Slf4j
public class SchedulingTask implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Map<String, Object> variables = execution.getVariables();
        log.info("variables:{}", variables);
        log.info("调度任务生成订单");
    }
}
