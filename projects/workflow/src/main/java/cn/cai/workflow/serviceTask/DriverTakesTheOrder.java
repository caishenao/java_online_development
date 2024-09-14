package cn.cai.workflow.serviceTask;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * 司机接单业务实现
 *
 */
public class DriverTakesTheOrder implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("司机接单业务实现");
    }
}
