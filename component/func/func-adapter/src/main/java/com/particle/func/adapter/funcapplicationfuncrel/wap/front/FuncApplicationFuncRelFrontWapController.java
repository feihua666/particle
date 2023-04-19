package com.particle.func.adapter.funcapplicationfuncrel.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.func.client.funcapplicationfuncrel.api.IFuncApplicationFuncRelApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 功能应用功能关系前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:15:29
 */
@Api(tags = "功能应用功能关系wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/func_application_func_rel")
public class FuncApplicationFuncRelFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IFuncApplicationFuncRelApplicationService iFuncApplicationFuncRelApplicationService;


}