package com.particle.workflow.adapter.execution.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.workflow.client.execution.api.IWorkflowExecutionNodeApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 工作流节点执行实例后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
@Tag(name = "工作流节点执行实例wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/workflow_execution_node")
public class WorkflowExecutionNodeAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IWorkflowExecutionNodeApplicationService iWorkflowExecutionNodeApplicationService;


}