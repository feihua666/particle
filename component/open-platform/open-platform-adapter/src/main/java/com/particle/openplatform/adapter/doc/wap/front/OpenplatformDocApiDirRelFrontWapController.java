package com.particle.openplatform.adapter.doc.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.openplatform.client.doc.api.IOpenplatformDocApiDirRelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放接口文档接口与目录关系前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:20
 */
@Tag(name = "开放接口文档接口与目录关系wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/openplatform_doc_api_dir_rel")
public class OpenplatformDocApiDirRelFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IOpenplatformDocApiDirRelApplicationService iOpenplatformDocApiDirRelApplicationService;


}