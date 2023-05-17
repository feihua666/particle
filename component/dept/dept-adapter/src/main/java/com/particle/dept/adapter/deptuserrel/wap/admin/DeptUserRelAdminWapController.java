package com.particle.dept.adapter.deptuserrel.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.dept.client.deptuserrel.api.IDeptUserRelApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门用户关系后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:28:42
 */
@Api(tags = "部门用户关系wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/dept_user_rel")
public class DeptUserRelAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDeptUserRelApplicationService iDeptUserRelApplicationService;


}