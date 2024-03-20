package com.particle.openplatform.adapter.doc.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.openplatform.client.doc.api.IOpenplatformDocApiDocTemplateExampleCodeApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放接口文档模板示例代码后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:26
 */
@Tag(name = "开放接口文档模板示例代码移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/openplatform_doc_api_doc_template_example_code")
public class OpenplatformDocApiDocTemplateExampleCodeAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IOpenplatformDocApiDocTemplateExampleCodeApplicationService iOpenplatformDocApiDocTemplateExampleCodeApplicationService;


}