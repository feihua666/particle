package com.particle.openplatform.adapter.doc.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.openplatform.client.doc.api.IOpenplatformDocDirNameApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放接口目录名称后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:53:48
 */
@Tag(name = "开放接口目录名称移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/openplatform_doc_dir_name")
public class OpenplatformDocDirNameAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IOpenplatformDocDirNameApplicationService iOpenplatformDocDirNameApplicationService;


}
