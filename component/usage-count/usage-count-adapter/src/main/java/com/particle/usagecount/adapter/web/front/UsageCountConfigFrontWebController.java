package com.particle.usagecount.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.usagecount.client.api.IUsageCountConfigApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 使用次数配置前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
@Tag(name = "使用次数配置pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/usage_count_config")
public class UsageCountConfigFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IUsageCountConfigApplicationService iUsageCountConfigApplicationService;


}