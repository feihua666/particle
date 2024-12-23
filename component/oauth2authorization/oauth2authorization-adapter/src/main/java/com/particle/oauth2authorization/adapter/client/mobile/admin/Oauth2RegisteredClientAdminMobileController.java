package com.particle.oauth2authorization.adapter.client.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.oauth2authorization.client.client.api.IOauth2RegisteredClientApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * oauth2客户端后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
@Tag(name = "oauth2客户端移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/oauth2_registered_client")
public class Oauth2RegisteredClientAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IOauth2RegisteredClientApplicationService iOauth2RegisteredClientApplicationService;


}
