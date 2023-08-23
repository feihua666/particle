package com.particle.openplatform.adapter.provider.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.openplatform.client.provider.api.IOpenplatformProviderApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台开放接口供应商前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:58
 */
@Tag(name = "开放平台开放接口供应商pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/openplatform_provider")
public class OpenplatformProviderFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformProviderApplicationService iOpenplatformProviderApplicationService;


}