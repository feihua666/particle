package com.particle.dept.adapter.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.dept.client.api.IDeptApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Api(tags = "部门移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/dept")
public class DeptFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDeptApplicationService iDeptApplicationService;


}