package com.particle.dataquery.adapter.dataapi.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.dataquery.client.dataapi.api.IDataQueryDataApiApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据查询数据接口后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Tag(name = "数据查询数据接口wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/data_query_data_api")
public class DataQueryDataApiAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDataQueryDataApiApplicationService iDataQueryDataApiApplicationService;


}