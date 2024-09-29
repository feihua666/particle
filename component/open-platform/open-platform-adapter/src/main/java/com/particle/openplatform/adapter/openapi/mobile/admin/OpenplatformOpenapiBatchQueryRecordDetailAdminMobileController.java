package com.particle.openplatform.adapter.openapi.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.openplatform.client.openapi.api.IOpenplatformOpenapiBatchQueryRecordDetailApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放接口批量查询记录明细后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:46:36
 */
@Tag(name = "开放接口批量查询记录明细移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/openplatform_openapi_batch_query_record_detail")
public class OpenplatformOpenapiBatchQueryRecordDetailAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IOpenplatformOpenapiBatchQueryRecordDetailApplicationService iOpenplatformOpenapiBatchQueryRecordDetailApplicationService;


}