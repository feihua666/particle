package com.particle.user.adapter.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.user.client.api.IUserApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Tag(name = "用户wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/user")
public class UserAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IUserApplicationService iUserApplicationService;









}
