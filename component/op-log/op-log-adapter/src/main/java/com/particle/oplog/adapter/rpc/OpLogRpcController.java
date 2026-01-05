package com.particle.oplog.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.adapter.feign.client.rpc.OpLogRpcFeignClient;
import com.particle.oplog.client.api.IOpLogApplicationService;
import com.particle.oplog.client.dto.command.OpLogCreateCommand;
import com.particle.oplog.client.dto.data.OpLogVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 操作日志远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:32:34
 */
@Tag(name = "操作日志远程调用相关接口")
@RestController
@RequestMapping("/rpc/op_log")
public class OpLogRpcController extends AbstractBaseRpcAdapter implements OpLogRpcFeignClient  {

	@Autowired
	private IOpLogApplicationService iOpLogApplicationService;


    @Operation(summary = "创建操作日志")
    @Override
    public SingleResponse<OpLogVO> create(@RequestBody OpLogCreateCommand opLogCreateCommand) {
        return iOpLogApplicationService.create(opLogCreateCommand);
    }
}
