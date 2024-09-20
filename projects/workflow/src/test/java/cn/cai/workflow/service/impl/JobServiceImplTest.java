package cn.cai.workflow.service.impl;

import com.google.common.collect.Lists;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.rest.util.EngineUtil;
import org.camunda.bpm.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JobServiceImplTest {

    @Autowired
    private JobServiceImpl jobService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private IdentityService identityService;

    @Test
    public void todoListTest() {
        ProcessEngine engine = EngineUtil.lookupProcessEngine("default");
        List<String> groups = Lists.newArrayList("camunda-admin");
        identityService.setAuthentication("demo", groups);
        List<Task> list = taskService.createTaskQuery()
                .includeAssignedTasks().list();

        for (Task task : list) {
            String id = task.getId();
            String name = task.getName();
            System.out.println(id + " " + name);
        }
    }
}
