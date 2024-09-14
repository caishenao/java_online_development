package cn.cai.workflow.pojo.vo;

import cn.cai.workflow.pojo.dto.LaneDTO;
import lombok.Data;

import java.util.List;

/**
 * 流程定义详情
 */
@Data
public class ProcessDetailsVO {


    /**
     * 分区集合
     */
    private List<LaneDTO> laneList;
}
