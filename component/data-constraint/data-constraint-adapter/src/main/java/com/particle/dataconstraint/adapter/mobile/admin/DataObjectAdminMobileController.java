package com.particle.dataconstraint.adapter.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.dataconstraint.client.api.IDataObjectApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据对象后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@Tag(name = "数据对象移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/data_object")
public class DataObjectAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataObjectApplicationService iDataObjectApplicationService;


}