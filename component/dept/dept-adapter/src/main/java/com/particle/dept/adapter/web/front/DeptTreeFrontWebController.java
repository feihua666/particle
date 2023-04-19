package com.particle.dept.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.dept.client.api.IDeptTreeApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门树前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
@Api(tags = "部门树pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/dept_tree")
public class DeptTreeFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDeptTreeApplicationService iDeptTreeApplicationService;


}