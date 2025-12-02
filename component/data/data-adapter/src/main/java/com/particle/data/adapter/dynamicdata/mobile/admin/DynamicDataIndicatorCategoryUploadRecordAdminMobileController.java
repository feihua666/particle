package com.particle.data.adapter.dynamicdata.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.data.client.dynamicdata.api.IDynamicDataIndicatorCategoryUploadRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 动态数据指标分类上传记录后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:59
 */
@Tag(name = "动态数据指标分类上传记录移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/dynamic_data_indicator_category_upload_record")
public class DynamicDataIndicatorCategoryUploadRecordAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDynamicDataIndicatorCategoryUploadRecordApplicationService iDynamicDataIndicatorCategoryUploadRecordApplicationService;


}