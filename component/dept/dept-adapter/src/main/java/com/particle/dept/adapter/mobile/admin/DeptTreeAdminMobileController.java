package com.particle.dept.adapter.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.dept.client.api.IDeptTreeApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门树后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
@Tag(name = "部门树移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/dept_tree")
public class DeptTreeAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDeptTreeApplicationService iDeptTreeApplicationService;


}