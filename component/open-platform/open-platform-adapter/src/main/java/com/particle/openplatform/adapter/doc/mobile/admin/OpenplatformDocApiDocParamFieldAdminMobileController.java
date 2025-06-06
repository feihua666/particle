package com.particle.openplatform.adapter.doc.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDocParamFieldApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放接口文档参数字段后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:55
 */
@Tag(name = "开放接口文档参数字段移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/openplatform_doc_api_doc_param_field")
public class OpenplatformDocApiDocParamFieldAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IOpenplatformDocApiDocParamFieldApplicationService iOpenplatformDocApiDocParamFieldApplicationService;


}
