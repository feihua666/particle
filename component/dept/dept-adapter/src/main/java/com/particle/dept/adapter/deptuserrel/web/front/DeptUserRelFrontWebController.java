package com.particle.dept.adapter.deptuserrel.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.dept.client.deptuserrel.api.IDeptUserRelApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门用户关系前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:28:42
 */
@Api(tags = "部门用户关系pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/dept_user_rel")
public class DeptUserRelFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDeptUserRelApplicationService iDeptUserRelApplicationService;


}