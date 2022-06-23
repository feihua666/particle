package com.particle.area.adapter.web;

import com.particle.area.client.api.IAreaApplicationService;
import com.particle.area.client.dto.command.CreateAreaCommand;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.security.security.login.LoginUser;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 区域管理前端适配器
 * 主要用于后台管理等
 * </p>
 *
 * @author yangwei
 * @since 2022-05-17 15:11
 */
@Api(tags = "区域后台管理相关接口")
@RestController
@RequestMapping("/admin/area")
public class AreaAdminController extends AbstractBaseWebAdapter {

	@Autowired
	private IAreaApplicationService areaApplicationService;

	@PreAuthorize("hasAuthority('admin:area:create')")
	@PostMapping
	public SingleResponse<AreaVO> create(@RequestBody CreateAreaCommand createAreaCommand, LoginUser loginUser){
		return areaApplicationService.create(createAreaCommand);
	}
}
