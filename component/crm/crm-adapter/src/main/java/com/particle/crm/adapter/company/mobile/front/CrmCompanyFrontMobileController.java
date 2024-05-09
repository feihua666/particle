package com.particle.crm.adapter.company.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.crm.client.company.api.ICrmCompanyApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客户公司前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-04-11 13:44:00
 */
@Tag(name = "客户公司移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/crm_company")
public class CrmCompanyFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ICrmCompanyApplicationService iCrmCompanyApplicationService;


}