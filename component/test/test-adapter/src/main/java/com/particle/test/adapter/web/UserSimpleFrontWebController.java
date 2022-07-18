package com.particle.test.adapter.web;

import com.particle.test.client.api.IUserSimpleApplicationService;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 简单用户前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Api(tags = "简单用户pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/user-simple")
public class UserSimpleFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IUserSimpleApplicationService iUserSimpleApplicationService;









}