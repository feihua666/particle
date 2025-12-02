package com.particle.data.adapter.dynamicdata.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.data.client.dynamicdata.api.IDynamicDataIndicatorCategoryUploadRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 动态数据指标分类上传记录后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:59
 */
@Tag(name = "动态数据指标分类上传记录wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/dynamic_data_indicator_category_upload_record")
public class DynamicDataIndicatorCategoryUploadRecordAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDynamicDataIndicatorCategoryUploadRecordApplicationService iDynamicDataIndicatorCategoryUploadRecordApplicationService;


}