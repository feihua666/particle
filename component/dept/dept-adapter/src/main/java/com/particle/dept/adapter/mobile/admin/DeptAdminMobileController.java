package com.particle.dept.adapter.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.dept.client.api.IDeptApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Tag(name = "部门移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/dept")
public class DeptAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDeptApplicationService iDeptApplicationService;


}