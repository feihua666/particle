package com.particle.data.adapter.company.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.data.client.company.api.IDataCompanyDiscreditedJudgmentDebtorApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业失信被执行人后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:58
 */
@Tag(name = "企业失信被执行人移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/data_company_discredited_judgment_debtor")
public class DataCompanyDiscreditedJudgmentDebtorAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataCompanyDiscreditedJudgmentDebtorApplicationService iDataCompanyDiscreditedJudgmentDebtorApplicationService;


}