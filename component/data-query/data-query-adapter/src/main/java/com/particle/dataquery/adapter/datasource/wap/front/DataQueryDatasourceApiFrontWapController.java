package com.particle.dataquery.adapter.datasource.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.dataquery.client.datasource.api.IDataQueryDatasourceApiApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据查询数据源接口前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Tag(name = "数据查询数据源接口wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/data_query_datasource_api")
public class DataQueryDatasourceApiFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDataQueryDatasourceApiApplicationService iDataQueryDatasourceApiApplicationService;


}