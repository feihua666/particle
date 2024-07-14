package com.particle.data.adapter.company.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.data.client.company.api.IDataCompanyApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Tag(name = "企业wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/data_company")
public class DataCompanyAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDataCompanyApplicationService iDataCompanyApplicationService;


}