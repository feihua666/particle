package com.particle.usagecount.adapter.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.usagecount.client.api.IUsageCountConfigApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 使用次数配置后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
@Tag(name = "使用次数配置移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/usage_count_config")
public class UsageCountConfigAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IUsageCountConfigApplicationService iUsageCountConfigApplicationService;


}