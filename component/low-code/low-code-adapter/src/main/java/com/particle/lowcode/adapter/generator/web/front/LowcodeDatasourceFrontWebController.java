package com.particle.lowcode.adapter.generator.web.front;

import com.particle.lowcode.client.generator.api.ILowcodeDatasourceApplicationService;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 低代码数据源前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Api(tags = "低代码数据源pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/lowcode-datasource")
public class LowcodeDatasourceFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ILowcodeDatasourceApplicationService iLowcodeDatasourceApplicationService;









}