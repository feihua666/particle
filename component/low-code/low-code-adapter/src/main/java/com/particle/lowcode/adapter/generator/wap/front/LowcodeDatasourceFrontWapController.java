package com.particle.lowcode.adapter.generator.wap.front;

import com.particle.lowcode.client.generator.api.ILowcodeDatasourceApplicationService;
import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 低代码数据源前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Api(tags = "低代码数据源wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/lowcode-datasource")
public class LowcodeDatasourceFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ILowcodeDatasourceApplicationService iLowcodeDatasourceApplicationService;









}