package com.particle.dataquery.adapter.dataapi.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.dataquery.client.dataapi.api.IDataQueryDataApiApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据查询数据接口前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Api(tags = "数据查询数据接口移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/data_query_data_api")
public class DataQueryDataApiFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataQueryDataApiApplicationService iDataQueryDataApiApplicationService;


}