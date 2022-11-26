package com.particle.user.adapter.identifier.web.front;

import com.particle.user.client.identifier.api.IUserIdentifierApplicationService;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户登录标识前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Api(tags = "用户登录标识pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/user-identifier")
public class UserIdentifierFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IUserIdentifierApplicationService iUserIdentifierApplicationService;









}