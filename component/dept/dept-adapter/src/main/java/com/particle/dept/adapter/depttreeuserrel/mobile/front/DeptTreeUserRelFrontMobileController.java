package com.particle.dept.adapter.depttreeuserrel.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.dept.client.depttreeuserrel.api.IDeptTreeUserRelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门树用户关系前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:26:06
 */
@Tag(name = "部门树用户关系移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/dept_tree_user_rel")
public class DeptTreeUserRelFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDeptTreeUserRelApplicationService iDeptTreeUserRelApplicationService;


}