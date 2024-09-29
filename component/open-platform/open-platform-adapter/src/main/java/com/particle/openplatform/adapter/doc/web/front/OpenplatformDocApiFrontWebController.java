package com.particle.openplatform.adapter.doc.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.api.IOpenplatformDocApiApplicationService;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocApiRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiQueryAllDetailCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDetailVO;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放接口文档接口前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
@Tag(name = "开放接口文档接口pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/openplatform_doc_api")
public class OpenplatformDocApiFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformDocApiApplicationService iOpenplatformDocApiApplicationService;
	@Autowired
	private IOpenplatformDocApiRepresentationApplicationService iOpenplatformDocApiRepresentationApplicationService;

	@Operation(summary = "开放接口文档接口详情展示")
	@GetMapping("/alldetail")
	public SingleResponse<OpenplatformDocApiDetailVO> queryAllDetail(OpenplatformDocApiQueryAllDetailCommand detailCommand){
		return iOpenplatformDocApiRepresentationApplicationService.queryAllDetail(detailCommand);
	}
	@Operation(summary = "列表查询开放接口文档接口")
	@GetMapping("/list")
	public MultiResponse<OpenplatformDocApiVO> queryList(OpenplatformDocApiQueryListCommand openplatformDocApiQueryListCommand){
		return iOpenplatformDocApiRepresentationApplicationService.queryList(openplatformDocApiQueryListCommand);
	}

	@Operation(summary = "分页查询开放接口文档接口")
	@GetMapping("/page")
	public PageResponse<OpenplatformDocApiVO> pageQueryList(OpenplatformDocApiPageQueryCommand openplatformDocApiPageQueryCommand){
		return iOpenplatformDocApiRepresentationApplicationService.pageQuery(openplatformDocApiPageQueryCommand);
	}
}