package cn.cai.workflow.serviceTask;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

/**
 * 分配货箱
 */
@Service("assignCrates")
@Slf4j
public class AssignCrates implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // 获取司机和车辆信息，根据车辆的装载量分配排队最少的车辆
        log.info("获取司机和车辆信息，根据车辆的装载量分配排队最少的车辆");
    }
}
