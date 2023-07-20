package com.particle.dept.adapter.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.dept.client.api.IDeptTreeNameApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门树名称前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:42:10
 */
@Tag(name = "部门树名称移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/dept_tree_name")
public class DeptTreeNameFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDeptTreeNameApplicationService iDeptTreeNameApplicationService;


}