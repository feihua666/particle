package com.particle.dataquery.adapter.datasource.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.dataquery.client.datasource.api.IDataQueryDatasourceApiApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据查询数据源接口前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Api(tags = "数据查询数据源接口pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/data_query_datasource_api")
public class DataQueryDatasourceApiFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataQueryDatasourceApiApplicationService iDataQueryDatasourceApiApplicationService;


}