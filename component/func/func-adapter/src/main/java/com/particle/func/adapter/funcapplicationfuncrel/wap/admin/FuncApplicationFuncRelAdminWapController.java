package com.particle.func.adapter.funcapplicationfuncrel.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.func.client.funcapplicationfuncrel.api.IFuncApplicationFuncRelApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 功能应用功能关系后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:15:29
 */
@Api(tags = "功能应用功能关系wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/func_application_func_rel")
public class FuncApplicationFuncRelAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IFuncApplicationFuncRelApplicationService iFuncApplicationFuncRelApplicationService;


}