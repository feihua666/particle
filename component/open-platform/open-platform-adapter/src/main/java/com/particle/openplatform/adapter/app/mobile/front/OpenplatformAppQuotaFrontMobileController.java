package com.particle.openplatform.adapter.app.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.openplatform.client.app.api.IOpenplatformAppQuotaApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台应用额度前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
@Tag(name = "开放平台应用额度移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/openplatform_app_quota")
public class OpenplatformAppQuotaFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IOpenplatformAppQuotaApplicationService iOpenplatformAppQuotaApplicationService;


}