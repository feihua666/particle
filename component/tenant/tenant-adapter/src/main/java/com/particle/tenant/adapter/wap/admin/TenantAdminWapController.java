package com.particle.tenant.adapter.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.tenant.client.api.ITenantApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@Tag(name = "租户wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/tenant")
public class TenantAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ITenantApplicationService iTenantApplicationService;


}