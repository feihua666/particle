package com.particle.data.adapter.dynamictable.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.data.client.dynamictable.api.IDynamicTableUploadRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 动态数据表格上传记录前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:17
 */
@Tag(name = "动态数据表格上传记录移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/dynamic_table_upload_record")
public class DynamicTableUploadRecordFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDynamicTableUploadRecordApplicationService iDynamicTableUploadRecordApplicationService;


}
