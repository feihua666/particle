package com.particle.navigation.adapter.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.navigation.client.api.INavigationSubmitApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 导航提交后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
@Tag(name = "导航提交移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/navigation_submit")
public class NavigationSubmitAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private INavigationSubmitApplicationService iNavigationSubmitApplicationService;


}