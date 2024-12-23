package com.particle.dataquery.adapter.datasource.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.dataquery.client.datasource.api.IDataQueryDatasourceApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据查询数据源后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
@Tag(name = "数据查询数据源移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/data_query_datasource")
public class DataQueryDatasourceAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataQueryDatasourceApplicationService iDataQueryDatasourceApplicationService;


}
