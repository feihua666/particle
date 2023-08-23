package com.particle.openplatform.adapter.openapi.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.openplatform.client.openapi.api.IOpenplatformOpenapiFeeApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台开放接口费用前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:59:32
 */
@Tag(name = "开放平台开放接口费用wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/openplatform_openapi_fee")
public class OpenplatformOpenapiFeeFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IOpenplatformOpenapiFeeApplicationService iOpenplatformOpenapiFeeApplicationService;


}