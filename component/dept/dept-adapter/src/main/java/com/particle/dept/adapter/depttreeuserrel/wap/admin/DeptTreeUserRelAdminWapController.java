package com.particle.dept.adapter.depttreeuserrel.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.dept.client.depttreeuserrel.api.IDeptTreeUserRelApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门树用户关系后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-12 17:28:43
 */
@Api(tags = "部门树用户关系wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/dept_tree_user_rel")
public class DeptTreeUserRelAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDeptTreeUserRelApplicationService iDeptTreeUserRelApplicationService;


}