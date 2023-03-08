package com.particle.dataquery.adapter.provider.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.dataquery.client.provider.api.IDataQueryProviderApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据查询供应商后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@Api(tags = "数据查询供应商移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/data_query_provider")
public class DataQueryProviderAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataQueryProviderApplicationService iDataQueryProviderApplicationService;


}