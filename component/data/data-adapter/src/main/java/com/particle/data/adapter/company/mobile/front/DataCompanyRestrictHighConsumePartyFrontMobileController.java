package com.particle.data.adapter.company.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.data.client.company.api.IDataCompanyRestrictHighConsumePartyApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业限制高消费当事人前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:32
 */
@Tag(name = "企业限制高消费当事人移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/data_company_restrict_high_consume_party")
public class DataCompanyRestrictHighConsumePartyFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataCompanyRestrictHighConsumePartyApplicationService iDataCompanyRestrictHighConsumePartyApplicationService;


}