package com.particle.openplatform.adapter.doc.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.MultiResponse;
import com.particle.openplatform.client.doc.api.IOpenplatformDocDirApplicationService;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocDirRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocDirQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocDirVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放接口目录前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:55:42
 */
@Tag(name = "开放接口目录pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/openplatform_doc_dir")
public class OpenplatformDocDirFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformDocDirApplicationService iOpenplatformDocDirApplicationService;
	@Autowired
	private IOpenplatformDocDirRepresentationApplicationService iOpenplatformDocDirRepresentationApplicationService;

	@Operation(summary = "列表查询开放接口目录")
	@GetMapping("/list")
	public MultiResponse<OpenplatformDocDirVO> queryList(OpenplatformDocDirQueryListCommand openplatformDocDirQueryListCommand){
		return iOpenplatformDocDirRepresentationApplicationService.queryList(openplatformDocDirQueryListCommand);
	}
}
