package com.particle.openplatform.adapter.app.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.openplatform.client.app.api.IOpenplatformAppQuotaApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台应用额度后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
@Tag(name = "开放平台应用额度移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/openplatform_app_quota")
public class OpenplatformAppQuotaAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IOpenplatformAppQuotaApplicationService iOpenplatformAppQuotaApplicationService;


}
