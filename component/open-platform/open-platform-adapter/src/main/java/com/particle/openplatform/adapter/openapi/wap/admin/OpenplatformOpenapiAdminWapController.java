package com.particle.openplatform.adapter.openapi.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.openplatform.client.openapi.api.IOpenplatformOpenapiApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台开放接口后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-08-08 11:13:24
 */
@Tag(name = "开放平台开放接口wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/openplatform_openapi")
public class OpenplatformOpenapiAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IOpenplatformOpenapiApplicationService iOpenplatformOpenapiApplicationService;


}