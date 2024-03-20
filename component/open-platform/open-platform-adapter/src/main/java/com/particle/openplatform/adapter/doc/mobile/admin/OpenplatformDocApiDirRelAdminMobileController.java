package com.particle.openplatform.adapter.doc.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.openplatform.client.doc.api.IOpenplatformDocApiDirRelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放接口文档接口与目录关系后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:20
 */
@Tag(name = "开放接口文档接口与目录关系移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/openplatform_doc_api_dir_rel")
public class OpenplatformDocApiDirRelAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IOpenplatformDocApiDirRelApplicationService iOpenplatformDocApiDirRelApplicationService;


}