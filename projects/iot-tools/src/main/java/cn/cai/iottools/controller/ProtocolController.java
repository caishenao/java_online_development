package cn.cai.iottools.controller;

import cn.cai.iottools.pojo.req.AddProtocolReq;
import cn.cai.iottools.pojo.resp.ProtocolResp;
import cn.cai.iottools.service.ProtocolService;
import cn.cai.web.comment.response.ResponseData;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cai
 */
@RestController
@RequestMapping("/protocol")
@Tag(name = "协议管理", description = "协议管理")
@AllArgsConstructor
@CrossOrigin
public class ProtocolController {

    private ProtocolService protocolService;


    @PostMapping("")
    @Operation(summary = "添加协议")
    public ResponseData<Void> addProtocol(@RequestBody AddProtocolReq addProtocolReq) {
        protocolService.addProtocol(addProtocolReq);
        return ResponseData.success();
    }

//    @GetMapping("/page")
//    @Operation(summary = "分页查询协议")
//    public ResponseData<Void> pageProtocol() {
//
//    }

    @GetMapping("/list")
    @Operation(summary = "查询协议列表")
    public ResponseData<List<ProtocolResp>> listProtocol() {
        List<ProtocolResp> protocolRespList = protocolService.listProtocol();
        return ResponseData.success(protocolRespList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据id查询协议")
    public ResponseData<ProtocolResp> getProtocolById(@PathVariable("id") String id) {
        ProtocolResp protocolResp = protocolService.getProtocolById(id);
        return ResponseData.success(protocolResp);
    }
}
