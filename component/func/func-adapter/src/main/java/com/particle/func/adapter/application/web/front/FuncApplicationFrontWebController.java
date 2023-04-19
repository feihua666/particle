package com.particle.func.adapter.application.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.func.client.application.api.IFuncApplicationApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 功能应用前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@Api(tags = "功能应用pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/func_application")
public class FuncApplicationFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IFuncApplicationApplicationService iFuncApplicationApplicationService;


}