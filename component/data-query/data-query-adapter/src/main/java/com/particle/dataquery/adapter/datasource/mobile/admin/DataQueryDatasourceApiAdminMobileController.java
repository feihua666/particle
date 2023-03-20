package com.particle.dataquery.adapter.datasource.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.dataquery.client.datasource.api.IDataQueryDatasourceApiApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据查询数据源接口后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Api(tags = "数据查询数据源接口移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/data_query_datasource_api")
public class DataQueryDatasourceApiAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataQueryDatasourceApiApplicationService iDataQueryDatasourceApiApplicationService;


}