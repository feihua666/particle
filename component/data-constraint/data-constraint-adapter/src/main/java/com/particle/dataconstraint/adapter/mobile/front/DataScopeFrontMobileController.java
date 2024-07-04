package com.particle.dataconstraint.adapter.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.dataconstraint.client.api.IDataScopeApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据范围前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:38
 */
@Tag(name = "数据范围移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/data_scope")
public class DataScopeFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataScopeApplicationService iDataScopeApplicationService;


}