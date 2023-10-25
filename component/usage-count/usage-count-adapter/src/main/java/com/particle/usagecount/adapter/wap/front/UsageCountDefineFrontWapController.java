package com.particle.usagecount.adapter.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.usagecount.client.api.IUsageCountDefineApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 使用次数定义前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:08
 */
@Tag(name = "使用次数定义wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/usage_count_define")
public class UsageCountDefineFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IUsageCountDefineApplicationService iUsageCountDefineApplicationService;


}