package cn.cai.workflow.controller;

import cn.cai.workflow.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/customize/job")
@RestController
@AllArgsConstructor
@Tag(name = "自定义任务接口", description = "自定义任务接口")
public class JobController {

    private final JobService jobService;


    @GetMapping("/listTodo")
    @Operation(summary = "查询待办任务集合")
    public ResponseEntity<Void> listTodo() {

        return ResponseEntity.ok().build();
    }


}
