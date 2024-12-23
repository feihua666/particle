package com.particle.lowcode.adapter.generator.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.lowcode.client.generator.api.ILowcodeDatasourceApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 低代码数据源后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Tag(name = "低代码数据源移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/lowcode-datasource")
public class LowcodeDatasourceAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ILowcodeDatasourceApplicationService iLowcodeDatasourceApplicationService;








}
