package com.particle.openplatform.adapter.doc.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDocTemplateApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放接口文档模板前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:39
 */
@Tag(name = "开放接口文档模板wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/openplatform_doc_api_doc_template")
public class OpenplatformDocApiDocTemplateFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IOpenplatformDocApiDocTemplateApplicationService iOpenplatformDocApiDocTemplateApplicationService;


}