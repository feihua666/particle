package com.particle.lowcode.adapter.generator.wap.admin;

import com.particle.lowcode.client.generator.api.ILowcodeDatasourceApplicationService;
import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 低代码数据源后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Api(tags = "低代码数据源wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/lowcode-datasource")
public class LowcodeDatasourceAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ILowcodeDatasourceApplicationService iLowcodeDatasourceApplicationService;









}