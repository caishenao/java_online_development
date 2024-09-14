package cn.cai.workflow.serviceTask;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("verificationEnd")
@Slf4j
public class VerificationEnd implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("验证结束");
    }
}
