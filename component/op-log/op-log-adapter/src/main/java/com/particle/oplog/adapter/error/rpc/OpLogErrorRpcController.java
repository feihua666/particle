package com.particle.oplog.adapter.error.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.adapter.feign.client.error.rpc.OpLogErrorRpcFeignClient;
import com.particle.oplog.client.dto.command.OpLogCreateCommand;
import com.particle.oplog.client.dto.data.OpLogVO;
import com.particle.oplog.client.error.api.IOpLogErrorApplicationService;
import com.particle.oplog.client.error.dto.command.OpLogErrorCreateCommand;
import com.particle.oplog.client.error.dto.data.OpLogErrorVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 操作异常日志远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:09
 */
@Tag(name = "操作异常日志远程调用相关接口")
@RestController
@RequestMapping("/rpc/op_log_error")
public class OpLogErrorRpcController extends AbstractBaseRpcAdapter implements OpLogErrorRpcFeignClient  {

	@Autowired
	private IOpLogErrorApplicationService iOpLogErrorApplicationService;

	@Operation(summary = "创建异常日志")
	@Override
	public SingleResponse<OpLogErrorVO> create(@RequestBody OpLogErrorCreateCommand opLogErrorCreateCommand) {
		return iOpLogErrorApplicationService.create(opLogErrorCreateCommand);
	}
}
