package com.particle.workflow.adapter.execution.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.workflow.client.execution.api.IWorkflowExecutionNodeApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 工作流节点执行实例前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
@Tag(name = "工作流节点执行实例pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/workflow_execution_node")
public class WorkflowExecutionNodeFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IWorkflowExecutionNodeApplicationService iWorkflowExecutionNodeApplicationService;


}