package com.particle.data.adapter.dynamicdata.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.data.client.dynamicdata.api.IDynamicDataCategoryApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 动态数据分类后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:37
 */
@Tag(name = "动态数据分类wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/dynamic_data_category")
public class DynamicDataCategoryAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDynamicDataCategoryApplicationService iDynamicDataCategoryApplicationService;


}