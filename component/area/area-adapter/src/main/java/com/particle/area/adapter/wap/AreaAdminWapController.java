package com.particle.area.adapter.wap;

import com.particle.area.client.api.IAreaApplicationService;
import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 区域后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@Api(tags = "区域wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/area")
public class AreaAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IAreaApplicationService iAreaApplicationService;









}