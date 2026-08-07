package com.particle.workflow.adapter.execution.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.workflow.client.execution.api.IWorkflowExecutionApplicationService;
import com.particle.workflow.adapter.feign.client.execution.rpc.WorkflowExecutionRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 工作流执行实例远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:57:51
 */
@Tag(name = "工作流执行实例远程调用相关接口")
@RestController
@RequestMapping("/rpc/workflow_execution")
public class WorkflowExecutionRpcController extends AbstractBaseRpcAdapter implements WorkflowExecutionRpcFeignClient  {

	@Autowired
	private IWorkflowExecutionApplicationService iWorkflowExecutionApplicationService;


}