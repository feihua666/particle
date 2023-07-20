package com.particle.func.adapter.funcapplicationfuncrel.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.func.client.funcapplicationfuncrel.api.IFuncApplicationFuncRelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 功能应用功能关系前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:15:29
 */
@Tag(name = "功能应用功能关系pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/func_application_func_rel")
public class FuncApplicationFuncRelFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IFuncApplicationFuncRelApplicationService iFuncApplicationFuncRelApplicationService;


}