package com.particle.openplatform.adapter.app.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.openplatform.client.app.api.IOpenplatformAppApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台应用前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:40:15
 */
@Tag(name = "开放平台应用移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/openplatform_app")
public class OpenplatformAppFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IOpenplatformAppApplicationService iOpenplatformAppApplicationService;


}