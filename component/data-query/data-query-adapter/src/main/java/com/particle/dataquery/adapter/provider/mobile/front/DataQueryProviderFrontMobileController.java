package com.particle.dataquery.adapter.provider.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.dataquery.client.provider.api.IDataQueryProviderApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据查询供应商前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@Tag(name = "数据查询供应商移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/data_query_provider")
public class DataQueryProviderFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataQueryProviderApplicationService iDataQueryProviderApplicationService;


}