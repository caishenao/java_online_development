package cn.cai.iottools.controller;

import cn.cai.iottools.pojo.req.AddConnectionReq;
import cn.cai.iottools.service.ConnectionService;
import cn.cai.web.comment.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 连接管理
 *
 * @author caishenao
 */
@RestController
@RequestMapping("/connection")
@AllArgsConstructor
@CrossOrigin
@Tag(name = "连接管理", description = "连接管理")
public class ConnectionController {


    private ConnectionService connectionService;

    @PostMapping("/addConnection")
    @Operation(summary = "新增连接")
    public ResponseData<Void> addConnection(@RequestBody AddConnectionReq addConnectionReq) {
        connectionService.addConnection(addConnectionReq);
        return ResponseData.success();
    }

}
