package com.particle.dept.adapter.deptuserrel.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.dept.client.deptuserrel.api.IDeptUserRelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门用户关系前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:28:42
 */
@Tag(name = "部门用户关系移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/dept_user_rel")
public class DeptUserRelFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDeptUserRelApplicationService iDeptUserRelApplicationService;


}