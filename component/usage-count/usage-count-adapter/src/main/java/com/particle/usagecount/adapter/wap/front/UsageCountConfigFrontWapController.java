package com.particle.usagecount.adapter.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.usagecount.client.api.IUsageCountConfigApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 使用次数配置前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
@Tag(name = "使用次数配置wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/usage_count_config")
public class UsageCountConfigFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IUsageCountConfigApplicationService iUsageCountConfigApplicationService;


}