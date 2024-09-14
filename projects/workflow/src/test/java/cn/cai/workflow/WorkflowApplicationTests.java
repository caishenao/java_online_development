package cn.cai.workflow;

import com.alibaba.fastjson2.JSONObject;
import org.casbin.casdoor.config.CasdoorAutoConfigure;
import org.casbin.casdoor.entity.User;
import org.casbin.casdoor.service.AuthService;
import org.casbin.casdoor.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class WorkflowApplicationTests {


    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Test
    void getAllUser() {
        try {
            List<User> users = userService.getUsers();
            System.out.println(JSONObject.toJSONString(users));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void paseUser() {
        String code = "1112223";
        String state = "built-in";
        String oAuthToken = authService.getOAuthToken(code, state);
        User user = authService.parseJwtToken(oAuthToken);
        System.out.println(user);
    }


//    @Test
//    void contextLoads() {
//        // 查询所有流程定义信息
//        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
//
//        for (ProcessDefinition processDefinition : list) {
//            // 根据流程定义信息获取xml
//            String id = processDefinition.getId();
//            BpmnModelInstance bpmnModelInstance = repositoryService.getBpmnModelInstance(processDefinition.getId());
//            String bpmnXml = bpmnModelInstance.toString();
//            System.out.println(bpmnXml);
//        }
//    }

}
