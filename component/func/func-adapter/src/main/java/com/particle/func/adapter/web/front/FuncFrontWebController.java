package com.particle.func.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.func.client.api.IFuncApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 菜单功能前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Api(tags = "菜单功能pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/func")
public class FuncFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IFuncApplicationService iFuncApplicationService;









}