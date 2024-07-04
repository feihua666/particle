package com.particle.dataconstraint.adapter.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.dataconstraint.client.api.IDataScopeApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据范围后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:38
 */
@Tag(name = "数据范围移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/data_scope")
public class DataScopeAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataScopeApplicationService iDataScopeApplicationService;


}