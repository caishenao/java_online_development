package cn.cai.workflow.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.*;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.xml.type.ModelElementType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

@SpringBootTest
@Slf4j
public class ProcessServiceImplTest {

    @Autowired
    private RepositoryService repositoryService;
    @Test
    void testListNode() {
        String processDefinitionKey = "Process_07otfs2";
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).latestVersion().singleResult();
        BpmnModelInstance bpmnModelInstance = repositoryService.getBpmnModelInstance(processDefinition.getId());
        Collection<Process> processCollection = bpmnModelInstance.getModelElementsByType(Process.class);
        for (Process process : processCollection) {

            Collection<LaneSet> laneSets = process.getChildElementsByType(LaneSet.class);
            // 获取区段
            for (LaneSet laneSet : laneSets) {
                Collection<Lane> lanes = laneSet.getLanes();
                for (Lane lane : lanes) {
                    Collection<FlowNode> flowNodeRefs = lane.getFlowNodeRefs();
                    for (FlowNode flowNodeRef : flowNodeRefs) {
                        String name = flowNodeRef.getName();
                        String id = flowNodeRef.getId();
                        System.out.println("结点信息:"+"name:" + name + ",id:" + id);
                    }
                    String name = lane.getName();
                    String id = lane.getId();
                    System.out.println("泳道信息："+"name:" + name + ",id:" + id);
                }

            }


            // 流元件
            Collection<FlowElement> flowElements = process.getFlowElements();
            for (FlowElement flowElement : flowElements) {
                String id = flowElement.getId();
                String name = flowElement.getName();
                ModelElementType elementType = flowElement.getElementType();
                System.out.println("id:" + id + ",name:" + name + ",elementType:" + elementType.getTypeName());
            }

//            ProcessType processType = process.getProcessType();
//            log.info("processType:{}",processType);
//            String name = process.getName();
//            log.info("name:{}",name);
//            String id = process.getId();
//            log.info("id:{}",id);
        }
//        Model model = bpmnModelInstance.getModel();
//        String modelName = model.getModelName();
//        log.info("modelName:{}",modelName);
    }
}
