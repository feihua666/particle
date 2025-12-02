package com.particle.data.adapter.dynamictable.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.data.client.dynamictable.api.IDynamicTableUploadRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 动态数据表格上传记录后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:17
 */
@Tag(name = "动态数据表格上传记录移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/dynamic_table_upload_record")
public class DynamicTableUploadRecordAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDynamicTableUploadRecordApplicationService iDynamicTableUploadRecordApplicationService;


}
