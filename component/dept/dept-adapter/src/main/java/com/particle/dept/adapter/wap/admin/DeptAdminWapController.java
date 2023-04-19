package com.particle.dept.adapter.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.dept.client.api.IDeptApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Api(tags = "部门wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/dept")
public class DeptAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDeptApplicationService iDeptApplicationService;


}