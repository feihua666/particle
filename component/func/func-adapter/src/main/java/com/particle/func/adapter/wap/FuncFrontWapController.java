package com.particle.func.adapter.wap;

import com.particle.func.client.api.IFuncApplicationService;
import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 菜单功能前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Api(tags = "菜单功能wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/func")
public class FuncFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IFuncApplicationService iFuncApplicationService;









}