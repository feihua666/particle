package com.particle.func.adapter.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.func.client.api.IFuncApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 菜单功能后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Tag(name = "菜单功能移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/func")
public class FuncAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IFuncApplicationService iFuncApplicationService;








}