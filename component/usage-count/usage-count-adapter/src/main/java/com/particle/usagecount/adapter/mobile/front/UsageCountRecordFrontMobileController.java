package com.particle.usagecount.adapter.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.usagecount.client.api.IUsageCountRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 使用次数记录前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
@Tag(name = "使用次数记录移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/usage_count_record")
public class UsageCountRecordFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IUsageCountRecordApplicationService iUsageCountRecordApplicationService;


}