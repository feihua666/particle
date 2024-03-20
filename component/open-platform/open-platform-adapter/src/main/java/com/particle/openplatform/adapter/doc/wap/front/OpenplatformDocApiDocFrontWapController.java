package com.particle.openplatform.adapter.doc.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDocApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放接口文档前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:37
 */
@Tag(name = "开放接口文档wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/openplatform_doc_api_doc")
public class OpenplatformDocApiDocFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IOpenplatformDocApiDocApplicationService iOpenplatformDocApiDocApplicationService;


}