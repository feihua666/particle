package com.particle.dept.adapter.deptuserrel.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.dept.client.deptuserrel.api.IDeptUserRelApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门用户关系前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 17:28:09
 */
@Api(tags = "部门用户关系wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/dept_user_rel")
public class DeptUserRelFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDeptUserRelApplicationService iDeptUserRelApplicationService;


}