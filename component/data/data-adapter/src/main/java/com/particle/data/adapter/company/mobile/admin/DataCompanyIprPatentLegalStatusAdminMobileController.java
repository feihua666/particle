package com.particle.data.adapter.company.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.data.client.company.api.IDataCompanyIprPatentLegalStatusApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业知识产权专利法律状态后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:03
 */
@Tag(name = "企业知识产权专利法律状态移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/data_company_ipr_patent_legal_status")
public class DataCompanyIprPatentLegalStatusAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataCompanyIprPatentLegalStatusApplicationService iDataCompanyIprPatentLegalStatusApplicationService;


}