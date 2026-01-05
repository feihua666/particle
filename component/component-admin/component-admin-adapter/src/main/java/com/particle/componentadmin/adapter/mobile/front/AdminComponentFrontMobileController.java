package com.particle.componentadmin.adapter.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.componentadmin.client.api.IAdminComponentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 组件前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:21:31
 */
@Tag(name = "组件移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/admin_component")
public class AdminComponentFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IAdminComponentApplicationService iAdminComponentApplicationService;


}