package com.particle.dataquery.adapter.datasource.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.dataquery.client.datasource.api.IDataQueryDatasourceApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据查询数据源前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
@Tag(name = "数据查询数据源pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/data_query_datasource")
public class DataQueryDatasourceFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataQueryDatasourceApplicationService iDataQueryDatasourceApplicationService;


}