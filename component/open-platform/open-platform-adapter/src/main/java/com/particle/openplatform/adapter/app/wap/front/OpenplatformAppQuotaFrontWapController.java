package com.particle.openplatform.adapter.app.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.openplatform.client.app.api.IOpenplatformAppQuotaApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台应用额度前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
@Tag(name = "开放平台应用额度wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/openplatform_app_quota")
public class OpenplatformAppQuotaFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IOpenplatformAppQuotaApplicationService iOpenplatformAppQuotaApplicationService;


}