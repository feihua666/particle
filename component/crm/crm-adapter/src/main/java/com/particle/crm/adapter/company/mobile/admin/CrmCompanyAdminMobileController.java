package com.particle.crm.adapter.company.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.crm.client.company.api.ICrmCompanyApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客户公司后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-04-11 13:44:00
 */
@Tag(name = "客户公司移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/crm_company")
public class CrmCompanyAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ICrmCompanyApplicationService iCrmCompanyApplicationService;


}