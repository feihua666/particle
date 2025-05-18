package com.particle.data.adapter.company.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.data.client.company.api.IDataCompanyCaseFilingApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业立案信息前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:36
 */
@Tag(name = "企业立案信息移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/data_company_case_filing")
public class DataCompanyCaseFilingFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataCompanyCaseFilingApplicationService iDataCompanyCaseFilingApplicationService;


}