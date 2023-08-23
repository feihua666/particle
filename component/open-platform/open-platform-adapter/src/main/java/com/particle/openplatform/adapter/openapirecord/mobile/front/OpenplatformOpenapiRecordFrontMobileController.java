package com.particle.openplatform.adapter.openapirecord.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.openplatform.client.openapirecord.api.IOpenplatformOpenapiRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台开放接口调用记录前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:13:46
 */
@Tag(name = "开放平台开放接口调用记录移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/openplatform_openapi_record")
public class OpenplatformOpenapiRecordFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IOpenplatformOpenapiRecordApplicationService iOpenplatformOpenapiRecordApplicationService;


}