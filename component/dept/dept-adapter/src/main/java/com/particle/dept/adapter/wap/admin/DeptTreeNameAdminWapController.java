package com.particle.dept.adapter.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.dept.client.api.IDeptTreeNameApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门树名称后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:42:10
 */
@Tag(name = "部门树名称wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/dept_tree_name")
public class DeptTreeNameAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDeptTreeNameApplicationService iDeptTreeNameApplicationService;


}