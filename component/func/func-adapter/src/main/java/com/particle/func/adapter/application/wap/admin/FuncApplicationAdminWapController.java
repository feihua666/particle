package com.particle.func.adapter.application.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.func.client.application.api.IFuncApplicationApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 功能应用后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@Tag(name = "功能应用wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/func_application")
public class FuncApplicationAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IFuncApplicationApplicationService iFuncApplicationApplicationService;


}