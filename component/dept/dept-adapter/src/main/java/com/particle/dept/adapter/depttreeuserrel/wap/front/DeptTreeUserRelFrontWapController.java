package com.particle.dept.adapter.depttreeuserrel.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.dept.client.depttreeuserrel.api.IDeptTreeUserRelApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门树用户关系前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:26:06
 */
@Api(tags = "部门树用户关系wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/dept_tree_user_rel")
public class DeptTreeUserRelFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDeptTreeUserRelApplicationService iDeptTreeUserRelApplicationService;


}