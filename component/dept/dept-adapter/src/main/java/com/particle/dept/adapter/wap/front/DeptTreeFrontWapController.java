package com.particle.dept.adapter.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.dept.client.api.IDeptTreeApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门树前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
@Tag(name = "部门树wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/dept_tree")
public class DeptTreeFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDeptTreeApplicationService iDeptTreeApplicationService;


}