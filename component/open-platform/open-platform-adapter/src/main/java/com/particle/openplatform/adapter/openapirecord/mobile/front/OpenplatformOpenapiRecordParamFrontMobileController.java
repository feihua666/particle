package com.particle.openplatform.adapter.openapirecord.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.openplatform.client.openapirecord.api.IOpenplatformOpenapiRecordParamApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台开放接口调用记录参数前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:03
 */
@Tag(name = "开放平台开放接口调用记录参数移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/openplatform_openapi_record_param")
public class OpenplatformOpenapiRecordParamFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IOpenplatformOpenapiRecordParamApplicationService iOpenplatformOpenapiRecordParamApplicationService;


}