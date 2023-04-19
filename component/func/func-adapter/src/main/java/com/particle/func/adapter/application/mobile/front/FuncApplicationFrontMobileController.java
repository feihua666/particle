package com.particle.func.adapter.application.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.func.client.application.api.IFuncApplicationApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 功能应用前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@Api(tags = "功能应用移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/func_application")
public class FuncApplicationFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IFuncApplicationApplicationService iFuncApplicationApplicationService;


}