package com.particle.func.adapter.mobile.front;

import com.particle.func.client.api.IFuncGroupApplicationService;
import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 功能组前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
@Api(tags = "功能组移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/func-group")
public class FuncGroupFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IFuncGroupApplicationService iFuncGroupApplicationService;









}