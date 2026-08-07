package com.particle.workflow.adapter.execution.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.workflow.client.execution.api.IWorkflowExecutionNodeApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 工作流节点执行实例前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
@Tag(name = "工作流节点执行实例移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/workflow_execution_node")
public class WorkflowExecutionNodeFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IWorkflowExecutionNodeApplicationService iWorkflowExecutionNodeApplicationService;


}