package com.particle.area.adapter.mobile;

import com.particle.area.client.api.IAreaApplicationService;
import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 区域后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-07-14
 */
@Api(tags = "区域移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/area")
public class AreaAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IAreaApplicationService areaApplicationService;

}