package com.particle.data.adapter.company.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.data.client.company.api.IDataCompanyPrimeStaffPositionApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业主要人员职位后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-22 15:07:33
 */
@Tag(name = "企业主要人员职位wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/data_company_prime_staff_position")
public class DataCompanyPrimeStaffPositionAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDataCompanyPrimeStaffPositionApplicationService iDataCompanyPrimeStaffPositionApplicationService;


}