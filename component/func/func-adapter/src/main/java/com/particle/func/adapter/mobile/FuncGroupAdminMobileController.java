package com.particle.func.adapter.mobile;

import com.particle.func.client.api.IFuncGroupApplicationService;
import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 功能组后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Api(tags = "功能组移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/func-group")
public class FuncGroupAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IFuncGroupApplicationService iFuncGroupApplicationService;








}