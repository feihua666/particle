package com.particle.data.adapter.company.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.data.client.company.api.IDataCompanyVcFinancingInvestInstitutionRelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业融资历史投资机构关系后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:28
 */
@Tag(name = "企业融资历史投资机构关系移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/data_company_vc_financing_invest_institution_rel")
public class DataCompanyVcFinancingInvestInstitutionRelAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataCompanyVcFinancingInvestInstitutionRelApplicationService iDataCompanyVcFinancingInvestInstitutionRelApplicationService;


}