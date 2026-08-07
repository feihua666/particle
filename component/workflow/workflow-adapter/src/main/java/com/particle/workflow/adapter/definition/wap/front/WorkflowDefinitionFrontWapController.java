package com.particle.workflow.adapter.definition.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.workflow.client.definition.api.IWorkflowDefinitionApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 工作流定义前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:48
 */
@Tag(name = "工作流定义wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/workflow_definition")
public class WorkflowDefinitionFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IWorkflowDefinitionApplicationService iWorkflowDefinitionApplicationService;


}