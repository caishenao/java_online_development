package cn.cai.iottools.controller;

import cn.cai.iottools.pojo.resp.ProtocolParameterResp;
import cn.cai.iottools.service.ProtocolParameterService;
import cn.cai.web.comment.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 协议参数管理
 *
 * @author cai
 */
@RestController
@RequestMapping("/protocolParameter")
@CrossOrigin
@Tag(name = "协议管理", description = "协议参数管理")
@AllArgsConstructor
public class ProtocolParameterController {

    private ProtocolParameterService protocolParameterService;


    @GetMapping("/listByProtocolId/{protocolId}")
    @Operation(summary = "根据协议id查询")
    public ResponseData<List<ProtocolParameterResp>> listByProtocolId(@PathVariable("protocolId") String protocolId) {
        List<ProtocolParameterResp> protocolParameterRespList = protocolParameterService.listByProtocolId(protocolId);
        return ResponseData.success(protocolParameterRespList);
    }

}
