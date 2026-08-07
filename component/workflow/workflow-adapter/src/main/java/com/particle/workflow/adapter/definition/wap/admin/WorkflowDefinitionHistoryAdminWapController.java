package com.particle.workflow.adapter.definition.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.workflow.client.definition.api.IWorkflowDefinitionHistoryApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 工作流定义历史后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:56:12
 */
@Tag(name = "工作流定义历史wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/workflow_definition_history")
public class WorkflowDefinitionHistoryAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IWorkflowDefinitionHistoryApplicationService iWorkflowDefinitionHistoryApplicationService;


}