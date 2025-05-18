package com.particle.data.adapter.company.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.data.client.company.api.IDataCompanyVcProductCompetitiveProductRelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业融资产品竞品关系后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:00
 */
@Tag(name = "企业融资产品竞品关系移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/data_company_vc_product_competitive_product_rel")
public class DataCompanyVcProductCompetitiveProductRelAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataCompanyVcProductCompetitiveProductRelApplicationService iDataCompanyVcProductCompetitiveProductRelApplicationService;


}