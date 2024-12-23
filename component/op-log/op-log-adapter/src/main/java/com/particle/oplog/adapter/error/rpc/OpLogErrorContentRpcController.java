package com.particle.oplog.adapter.error.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.oplog.adapter.feign.client.error.rpc.OpLogErrorContentRpcFeignClient;
import com.particle.oplog.client.error.api.IOpLogErrorContentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 操作异常日志内容远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:59
 */
@Tag(name = "操作异常日志内容远程调用相关接口")
@RestController
@RequestMapping("/rpc/op_log_error_content")
public class OpLogErrorContentRpcController extends AbstractBaseRpcAdapter implements OpLogErrorContentRpcFeignClient  {

	@Autowired
	private IOpLogErrorContentApplicationService iOpLogErrorContentApplicationService;


}
