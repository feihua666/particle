package com.particle.dept.adapter.deptuserrel.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.dept.client.deptuserrel.api.IDeptUserRelApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门用户关系后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-12 17:28:09
 */
@Api(tags = "部门用户关系移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/dept_user_rel")
public class DeptUserRelAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDeptUserRelApplicationService iDeptUserRelApplicationService;


}