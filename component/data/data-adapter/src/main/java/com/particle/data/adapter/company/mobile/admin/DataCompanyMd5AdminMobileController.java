package com.particle.data.adapter.company.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.data.client.company.api.IDataCompanyMd5ApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业md5后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:59
 */
@Tag(name = "企业md5移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/data_company_md5")
public class DataCompanyMd5AdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataCompanyMd5ApplicationService iDataCompanyMd5ApplicationService;


}
