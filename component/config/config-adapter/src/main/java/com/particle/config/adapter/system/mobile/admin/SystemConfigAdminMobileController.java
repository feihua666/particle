package com.particle.config.adapter.system.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.config.client.system.api.ISystemConfigApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统参数配置后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-05-30 10:29:04
 */
@Tag(name = "系统参数配置移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/system_config")
public class SystemConfigAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ISystemConfigApplicationService iSystemConfigApplicationService;


}