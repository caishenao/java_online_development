package cn.cai.workflow.controller;

import cn.cai.workflow.pojo.dto.HistoricActivityDTO;
import cn.cai.workflow.service.ProcessInstancesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/processInstances")
@AllArgsConstructor
public class ProcessInstancesController {


    private final ProcessInstancesService processInstancesService;


    @GetMapping(value = {
            "/listInstance/{processDefinitionKey}/{processDefinitionId}",
            "/listInstance/{processDefinitionKey}"
    })
    @Operation(summary = "根据流程定义key和id获取流程实例集合")
    public ResponseEntity<Void> listInstance(@PathVariable("processDefinitionKey") String processDefinitionKey, @PathVariable(name = "processDefinitionId", required = false) String processDefinitionId) {
        processInstancesService.listInstance(processDefinitionKey, processDefinitionId);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/listInstanceDetails/{processInstanceId}")
    @Operation(summary = "获取流程实例详细信息")
    public ResponseEntity<List<HistoricActivityDTO>> list(@PathVariable("processInstanceId") String processInstanceId) {
        List<HistoricActivityDTO> list = processInstancesService.list(processInstanceId);
        return ResponseEntity.ok(list);
    }
}
