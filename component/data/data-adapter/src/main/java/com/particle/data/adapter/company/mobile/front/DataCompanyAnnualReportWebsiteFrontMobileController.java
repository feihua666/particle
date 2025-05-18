package com.particle.data.adapter.company.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.data.client.company.api.IDataCompanyAnnualReportWebsiteApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业年报网站网店前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:16
 */
@Tag(name = "企业年报网站网店移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/data_company_annual_report_website")
public class DataCompanyAnnualReportWebsiteFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataCompanyAnnualReportWebsiteApplicationService iDataCompanyAnnualReportWebsiteApplicationService;


}