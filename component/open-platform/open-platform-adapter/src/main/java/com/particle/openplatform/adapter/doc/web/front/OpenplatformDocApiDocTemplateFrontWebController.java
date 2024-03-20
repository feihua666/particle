package com.particle.openplatform.adapter.doc.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDocTemplateApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放接口文档模板前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:39
 */
@Tag(name = "开放接口文档模板pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/openplatform_doc_api_doc_template")
public class OpenplatformDocApiDocTemplateFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformDocApiDocTemplateApplicationService iOpenplatformDocApiDocTemplateApplicationService;


}