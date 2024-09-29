package com.particle.openplatform.adapter.provider.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.openplatform.client.provider.api.IOpenplatformProviderApiApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台供应商接口前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:53:52
 */
@Tag(name = "开放平台供应商接口移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/openplatform_provider_api")
public class OpenplatformProviderApiFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IOpenplatformProviderApiApplicationService iOpenplatformProviderApiApplicationService;


}