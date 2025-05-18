package com.particle.data.adapter.company.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.data.client.company.api.IDataCompanyRestrictHighConsumePartyApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业限制高消费当事人后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:32
 */
@Tag(name = "企业限制高消费当事人wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/data_company_restrict_high_consume_party")
public class DataCompanyRestrictHighConsumePartyAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDataCompanyRestrictHighConsumePartyApplicationService iDataCompanyRestrictHighConsumePartyApplicationService;


}