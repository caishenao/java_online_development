package cn.cai.workflow.controller;

import cn.cai.workflow.pojo.dto.LaneDTO;
import cn.cai.workflow.pojo.vo.ProcessDefinitionVO;
import cn.cai.workflow.pojo.vo.ProcessDetailsVO;
import cn.cai.workflow.service.ProcessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.camunda.bpm.engine.repository.ProcessDefinition;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customize/process")
@AllArgsConstructor
@Tag(description = "自定义流程定义相关接口", name = "ProcessController")
public class ProcessController {

    private final ProcessService processService;

    @GetMapping("/getDetails/{processDefinitionKey}/{processDefinitionId}")
    @Operation(summary = "根据流程定义key获取流程详情", description = "根据流程定义key获取流程详情")
    @Parameters({
            @Parameter(name = "processDefinitionKey", description = "流程定义key"),
            @Parameter(name = "processDefinitionId", description = "流程定义id,可以为空，为空查询lastVersion", required = false)
    })
    public ResponseEntity<ProcessDetailsVO> getDetails(@PathVariable("processDefinitionKey") String processDefinitionKey, @PathVariable(name = "processDefinitionId", required = false) String processDefinitionId) {
        List<LaneDTO> laneDTOList = processService.getDetails(processDefinitionKey, processDefinitionId);
        ProcessDetailsVO processDetailsVO = new ProcessDetailsVO();
        processDetailsVO.setLaneList(laneDTOList);
        return ResponseEntity.ok(processDetailsVO);
    }

    @GetMapping("/list")
    @Operation(summary = "获取所有流程定义")
    public ResponseEntity<List<ProcessDefinitionVO>> list() {
        List<ProcessDefinition> processDefinitionList = processService.list();
        List<ProcessDefinitionVO> voList = ProcessDefinitionVO.convertProcessDefinition(processDefinitionList);
        return ResponseEntity.ok(voList);
    }


//    @GetMapping("/getVariableByProcessDefinitionKey/{processDefinitionKey}")
//    @Operation(summary = "根据流程定义key获取流程变量")
//    public ResponseEntity<List<Map<String, Object>>> getVariableByProcessDefinitionKey(@PathVariable("processDefinitionKey") String processDefinitionKey) {
//        // 根据流程定义key获取流程变量
//        List<Map<String, Object>> results = new ArrayList<>();
//        // 1. 获取流程实例
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);
//        // 2. 根据流程实例获取每个执行流
//        List<Execution> executionList = runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).list();
//        // 3. 获取每个执行流的变量
//        for (Execution execution : executionList) {
//            Map<String, Object> variables = runtimeService.getVariables(execution.getId());
//            results.add(variables);
//        }
//        // 5. 删除流程实例
//        runtimeService.deleteProcessInstance(processInstance.getId(), "变量查询");
//        return ResponseEntity.ok(results);
//
//    }

}
