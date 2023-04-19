package com.particle.dept.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.dept.client.api.IDeptApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Api(tags = "部门pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/dept")
public class DeptFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDeptApplicationService iDeptApplicationService;


}