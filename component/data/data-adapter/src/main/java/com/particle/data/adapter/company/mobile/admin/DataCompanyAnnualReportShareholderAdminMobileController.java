package com.particle.data.adapter.company.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.data.client.company.api.IDataCompanyAnnualReportShareholderApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业年报股东后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:37
 */
@Tag(name = "企业年报股东移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/data_company_annual_report_shareholder")
public class DataCompanyAnnualReportShareholderAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataCompanyAnnualReportShareholderApplicationService iDataCompanyAnnualReportShareholderApplicationService;


}