package com.particle.usagecount.adapter.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.usagecount.client.api.IUsageCountRecordDetailApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 使用次数记录明细后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-10-23 16:23:29
 */
@Tag(name = "使用次数记录明细wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/usage_count_record_detail")
public class UsageCountRecordDetailAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IUsageCountRecordDetailApplicationService iUsageCountRecordDetailApplicationService;


}