package com.particle.componentadmin.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.componentadmin.client.api.IAdminComponentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 组件前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:21:31
 */
@Tag(name = "组件pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/admin_component")
public class AdminComponentFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IAdminComponentApplicationService iAdminComponentApplicationService;


}