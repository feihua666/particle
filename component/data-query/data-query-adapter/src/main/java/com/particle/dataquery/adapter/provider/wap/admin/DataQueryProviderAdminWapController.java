package com.particle.dataquery.adapter.provider.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.dataquery.client.provider.api.IDataQueryProviderApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据查询供应商后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@Api(tags = "数据查询供应商wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/data_query_provider")
public class DataQueryProviderAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDataQueryProviderApplicationService iDataQueryProviderApplicationService;


}