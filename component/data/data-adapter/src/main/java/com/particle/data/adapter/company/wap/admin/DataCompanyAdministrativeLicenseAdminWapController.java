package com.particle.data.adapter.company.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.data.client.company.api.IDataCompanyAdministrativeLicenseApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业行政许可后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:17:53
 */
@Tag(name = "企业行政许可wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/data_company_administrative_license")
public class DataCompanyAdministrativeLicenseAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDataCompanyAdministrativeLicenseApplicationService iDataCompanyAdministrativeLicenseApplicationService;


}