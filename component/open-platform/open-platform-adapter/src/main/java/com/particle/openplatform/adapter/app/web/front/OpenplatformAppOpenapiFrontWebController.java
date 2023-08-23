package com.particle.openplatform.adapter.app.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.openplatform.client.app.api.IOpenplatformAppOpenapiApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台应用与开放接口配置前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:49:06
 */
@Tag(name = "开放平台应用与开放接口配置pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/openplatform_app_openapi")
public class OpenplatformAppOpenapiFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformAppOpenapiApplicationService iOpenplatformAppOpenapiApplicationService;


}