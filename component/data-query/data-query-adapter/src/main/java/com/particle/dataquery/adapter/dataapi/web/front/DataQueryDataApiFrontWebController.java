package com.particle.dataquery.adapter.dataapi.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.dataquery.client.dataapi.api.IDataQueryDataApiApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据查询数据接口前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Tag(name = "数据查询数据接口pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/data_query_data_api")
public class DataQueryDataApiFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataQueryDataApiApplicationService iDataQueryDataApiApplicationService;


}