package com.particle.data.adapter.company.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.data.client.company.api.IDataCompanyIprPatentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业知识产权专利后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:48
 */
@Tag(name = "企业知识产权专利wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/data_company_ipr_patent")
public class DataCompanyIprPatentAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDataCompanyIprPatentApplicationService iDataCompanyIprPatentApplicationService;


}