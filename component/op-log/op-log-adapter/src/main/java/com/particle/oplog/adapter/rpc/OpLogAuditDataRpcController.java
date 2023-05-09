package com.particle.oplog.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.oplog.client.api.IOpLogAuditDataApplicationService;
import com.particle.oplog.adapter.feign.client.rpc.OpLogAuditDataRpcFeignClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 操作日志审计数据远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:33:30
 */
@Api(tags = "操作日志审计数据远程调用相关接口")
@RestController
@RequestMapping("/rpc/op_log_audit_data")
public class OpLogAuditDataRpcController extends AbstractBaseRpcAdapter implements OpLogAuditDataRpcFeignClient  {

	@Autowired
	private IOpLogAuditDataApplicationService iOpLogAuditDataApplicationService;


}