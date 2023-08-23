package com.particle.openplatform.adapter.app.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.openplatform.client.app.api.IOpenplatformAppApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台应用后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:40:15
 */
@Tag(name = "开放平台应用wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/openplatform_app")
public class OpenplatformAppAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IOpenplatformAppApplicationService iOpenplatformAppApplicationService;


}