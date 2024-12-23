package com.particle.openplatform.adapter.providerrecord.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.openplatform.client.providerrecord.api.IOpenplatformProviderRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台开放接口供应商调用记录后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:17:25
 */
@Tag(name = "开放平台开放接口供应商调用记录移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/openplatform_provider_record")
public class OpenplatformProviderRecordAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IOpenplatformProviderRecordApplicationService iOpenplatformProviderRecordApplicationService;


}
