package com.particle.data.adapter.dynamictable.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.data.client.dynamictable.api.IDynamicTableApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 动态数据表格后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:29:35
 */
@Tag(name = "动态数据表格移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/dynamic_table")
public class DynamicTableAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDynamicTableApplicationService iDynamicTableApplicationService;


}