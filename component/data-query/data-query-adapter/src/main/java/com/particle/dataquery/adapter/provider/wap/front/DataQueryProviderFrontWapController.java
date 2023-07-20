package com.particle.dataquery.adapter.provider.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.dataquery.client.provider.api.IDataQueryProviderApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据查询供应商前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@Tag(name = "数据查询供应商wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/data_query_provider")
public class DataQueryProviderFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDataQueryProviderApplicationService iDataQueryProviderApplicationService;


}