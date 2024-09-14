package cn.cai.workflow.serviceTask;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

/**
 * 装载货箱
 */
@Service("loadingCrate")
@Slf4j
public class LoadingCrate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("司机确认对货箱进行装载");
    }
}
