package com.particle.test.adapter.wap;

import com.particle.test.client.api.IUserSimpleApplicationService;
import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 简单用户后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Api(tags = "简单用户wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/user-simple")
public class UserSimpleAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IUserSimpleApplicationService iUserSimpleApplicationService;









}