package com.particle.usagecount.adapter.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.usagecount.client.api.IUsageCountRecordDetailApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 使用次数记录明细前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-10-23 16:23:29
 */
@Tag(name = "使用次数记录明细移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/usage_count_record_detail")
public class UsageCountRecordDetailFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IUsageCountRecordDetailApplicationService iUsageCountRecordDetailApplicationService;


}