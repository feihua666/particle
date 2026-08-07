package com.particle.workflow.adapter.definition.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.workflow.client.definition.api.IWorkflowDefinitionApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 工作流定义后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:48
 */
@Tag(name = "工作流定义移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/workflow_definition")
public class WorkflowDefinitionAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IWorkflowDefinitionApplicationService iWorkflowDefinitionApplicationService;


}