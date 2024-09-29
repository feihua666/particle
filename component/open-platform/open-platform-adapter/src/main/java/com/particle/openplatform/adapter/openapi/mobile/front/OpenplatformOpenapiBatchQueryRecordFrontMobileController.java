package com.particle.openplatform.adapter.openapi.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.openplatform.client.openapi.api.IOpenplatformOpenapiBatchQueryRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放接口批量查询记录前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:44:36
 */
@Tag(name = "开放接口批量查询记录移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/openplatform_openapi_batch_query_record")
public class OpenplatformOpenapiBatchQueryRecordFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IOpenplatformOpenapiBatchQueryRecordApplicationService iOpenplatformOpenapiBatchQueryRecordApplicationService;


}