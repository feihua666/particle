package com.particle.openplatform.adapter.doc.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDocTemplateExampleCodeApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放接口文档模板示例代码后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:26
 */
@Tag(name = "开放接口文档模板示例代码wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/openplatform_doc_api_doc_template_example_code")
public class OpenplatformDocApiDocTemplateExampleCodeAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IOpenplatformDocApiDocTemplateExampleCodeApplicationService iOpenplatformDocApiDocTemplateExampleCodeApplicationService;


}