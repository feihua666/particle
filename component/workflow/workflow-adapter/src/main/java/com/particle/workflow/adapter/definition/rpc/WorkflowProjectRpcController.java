package com.particle.workflow.adapter.definition.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.workflow.client.definition.api.IWorkflowProjectApplicationService;
import com.particle.workflow.adapter.feign.client.definition.rpc.WorkflowProjectRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 工作流项目远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:12
 */
@Tag(name = "工作流项目远程调用相关接口")
@RestController
@RequestMapping("/rpc/workflow_project")
public class WorkflowProjectRpcController extends AbstractBaseRpcAdapter implements WorkflowProjectRpcFeignClient  {

	@Autowired
	private IWorkflowProjectApplicationService iWorkflowProjectApplicationService;


}