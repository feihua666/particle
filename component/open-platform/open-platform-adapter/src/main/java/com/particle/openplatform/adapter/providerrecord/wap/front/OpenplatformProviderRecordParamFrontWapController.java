package com.particle.openplatform.adapter.providerrecord.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.openplatform.client.providerrecord.api.IOpenplatformProviderRecordParamApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台开放接口供应商调用记录参数前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:18:54
 */
@Tag(name = "开放平台开放接口供应商调用记录参数wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/openplatform_provider_record_param")
public class OpenplatformProviderRecordParamFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IOpenplatformProviderRecordParamApplicationService iOpenplatformProviderRecordParamApplicationService;


}