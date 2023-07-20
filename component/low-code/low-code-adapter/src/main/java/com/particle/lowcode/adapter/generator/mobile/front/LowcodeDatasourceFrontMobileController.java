package com.particle.lowcode.adapter.generator.mobile.front;

import com.particle.lowcode.client.generator.api.ILowcodeDatasourceApplicationService;
import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 低代码数据源前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Tag(name = "低代码数据源移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/lowcode-datasource")
public class LowcodeDatasourceFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ILowcodeDatasourceApplicationService iLowcodeDatasourceApplicationService;









}