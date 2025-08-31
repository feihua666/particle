package com.particle.user.adapter.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.user.client.api.IUserExtraInfoApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户扩展信息后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-08-30 23:39:50
 */
@Tag(name = "用户扩展信息移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/user_extra_info")
public class UserExtraInfoAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IUserExtraInfoApplicationService iUserExtraInfoApplicationService;


}