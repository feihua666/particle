package com.particle.func.adapter.application.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.func.client.application.api.IFuncApplicationApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 功能应用前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@Tag(name = "功能应用wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/func_application")
public class FuncApplicationFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IFuncApplicationApplicationService iFuncApplicationApplicationService;


}