package com.particle.data.adapter.company.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.data.client.company.api.IDataCompanyAbnormalApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业经营异常后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-05-29 10:47:31
 */
@Tag(name = "企业经营异常wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/data_company_abnormal")
public class DataCompanyAbnormalAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDataCompanyAbnormalApplicationService iDataCompanyAbnormalApplicationService;


}