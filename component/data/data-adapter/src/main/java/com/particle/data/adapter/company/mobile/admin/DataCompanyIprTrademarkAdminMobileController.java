package com.particle.data.adapter.company.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.data.client.company.api.IDataCompanyIprTrademarkApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业知识产权商标后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:14:45
 */
@Tag(name = "企业知识产权商标移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/data_company_ipr_trademark")
public class DataCompanyIprTrademarkAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataCompanyIprTrademarkApplicationService iDataCompanyIprTrademarkApplicationService;


}