package com.particle.workflow.adapter.execution.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.workflow.client.execution.api.IWorkflowExecutionNodeApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 工作流节点执行实例后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
@Tag(name = "工作流节点执行实例移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/workflow_execution_node")
public class WorkflowExecutionNodeAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IWorkflowExecutionNodeApplicationService iWorkflowExecutionNodeApplicationService;


}