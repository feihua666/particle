package com.particle.data.adapter.dynamictable.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.data.client.dynamictable.api.IDynamicTableUploadRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 动态数据表格上传记录前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:17
 */
@Tag(name = "动态数据表格上传记录pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/dynamic_table_upload_record")
public class DynamicTableUploadRecordFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDynamicTableUploadRecordApplicationService iDynamicTableUploadRecordApplicationService;


}
