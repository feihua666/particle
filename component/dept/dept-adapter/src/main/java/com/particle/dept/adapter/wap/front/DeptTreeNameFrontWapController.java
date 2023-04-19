package com.particle.dept.adapter.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.dept.client.api.IDeptTreeNameApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门树名称前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:42:10
 */
@Api(tags = "部门树名称wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/dept_tree_name")
public class DeptTreeNameFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDeptTreeNameApplicationService iDeptTreeNameApplicationService;


}