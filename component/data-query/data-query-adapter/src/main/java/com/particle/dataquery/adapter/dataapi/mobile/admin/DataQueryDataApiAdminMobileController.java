package com.particle.dataquery.adapter.dataapi.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.dataquery.client.dataapi.api.IDataQueryDataApiApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据查询数据接口后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Api(tags = "数据查询数据接口移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/data_query_data_api")
public class DataQueryDataApiAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataQueryDataApiApplicationService iDataQueryDataApiApplicationService;


}