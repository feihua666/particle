package com.particle.func.adapter.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.func.client.api.IFuncGroupApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 功能组后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Api(tags = "功能组wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/func-group")
public class FuncGroupAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IFuncGroupApplicationService iFuncGroupApplicationService;









}