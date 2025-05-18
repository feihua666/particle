package com.particle.data.adapter.company.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.data.client.company.api.IDataCompanyVcProductApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业融资产品后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:14
 */
@Tag(name = "企业融资产品移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/data_company_vc_product")
public class DataCompanyVcProductAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataCompanyVcProductApplicationService iDataCompanyVcProductApplicationService;


}