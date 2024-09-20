package cn.cai.workflow.service.impl;

import cn.cai.web.comment.utils.AuthenticationUtils;
import cn.cai.workflow.service.JobService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.casbin.casdoor.entity.Role;
import org.casbin.casdoor.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义任务逻辑层接口实现
 */
@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService {



    private final TaskService taskService;

    /**
     * 获取自己的待办任务
     */
    @Override
    public void listTodo() {
        // 1. 获取当前用户
        User currentUser = AuthenticationUtils.getCurrentUser();
        // 2. 获取当前用户待办任务
        List<Role> roles = currentUser.roles;
        List<String> groupNameList = roles.stream().filter(item -> item.isEnabled).map(item -> item.name).collect(Collectors.toList());
        List<Task> list = taskService.createTaskQuery()
                .includeAssignedTasks().list();



    }
}
