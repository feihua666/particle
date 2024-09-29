package com.particle.openplatform.adapter.openapi.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.openplatform.client.openapi.api.IOpenplatformOpenapiBatchQueryRecordDetailApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放接口批量查询记录明细前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:46:36
 */
@Tag(name = "开放接口批量查询记录明细pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/openplatform_openapi_batch_query_record_detail")
public class OpenplatformOpenapiBatchQueryRecordDetailFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformOpenapiBatchQueryRecordDetailApplicationService iOpenplatformOpenapiBatchQueryRecordDetailApplicationService;


}