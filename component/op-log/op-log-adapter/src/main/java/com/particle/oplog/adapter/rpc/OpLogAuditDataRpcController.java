package com.particle.oplog.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.oplog.adapter.feign.client.rpc.OpLogAuditDataRpcFeignClient;
import com.particle.oplog.client.api.IOpLogAuditDataApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "操作日志审计数据远程调用相关接口")
@RestController
@RequestMapping("/rpc/op_log_audit_data")
public class OpLogAuditDataRpcController extends AbstractBaseRpcAdapter implements OpLogAuditDataRpcFeignClient  {

	@Autowired
	private IOpLogAuditDataApplicationService iOpLogAuditDataApplicationService;


}
