package com.particle.openplatform.adapter.doc.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDocExampleCodeApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放接口文档示例代码前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:11
 */
@Tag(name = "开放接口文档示例代码移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/openplatform_doc_api_doc_example_code")
public class OpenplatformDocApiDocExampleCodeFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IOpenplatformDocApiDocExampleCodeApplicationService iOpenplatformDocApiDocExampleCodeApplicationService;


}