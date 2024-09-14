package cn.cai.workflow.serviceTask;

import org.springframework.stereotype.Service;

/**
 * 离开陆港
 */
@Service("departTheDryPort")
public class DepartTheDryPort {

    public void bpmnCall() {
        System.out.println("DepartTheDryPort.bpmnCall()");
    }
}
