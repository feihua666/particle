package com.particle.area.adapter.web;

import com.particle.area.client.api.IAreaApplicationService;
import com.particle.area.client.dto.command.*;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 区域后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-07-14
 */
@Api(tags = "区域pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/area")
public class AreaAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IAreaApplicationService areaApplicationService;

	@PreAuthorize("hasAuthority('admin:web:area:create')")
	@ApiOperation("添加区域")
	@PostMapping
	public SingleResponse<AreaVO> create(@RequestBody AreaCreateCommand areaCreateCommand){
		return areaApplicationService.create(areaCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:area:delete')")
	@ApiOperation("删除区域")
	@DeleteMapping
	public SingleResponse<AreaVO> delete(@RequestBody AreaDeleteCommand areaDeleteCommand){
		return areaApplicationService.delete(areaDeleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:area:update')")
	@ApiOperation("更新区域")
	@PutMapping
	public SingleResponse<AreaVO> update(@RequestBody AreaUpdateCommand areaUpdateCommand){
		return areaApplicationService.update(areaUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:area:update')")
	@ApiOperation("区域更新详情")
	@DeleteMapping
	public SingleResponse<AreaVO> queryDetailForUpdate(@RequestBody AreaQueryDetailForUpdateCommand areaQueryDetailForUpdateCommand){
		return areaApplicationService.queryDetailForUpdate(areaQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:area:detail')")
	@ApiOperation("区域详情展示")
	@GetMapping
	public SingleResponse<AreaVO> queryDetail(@RequestBody AreaQueryDetailCommand areaQueryDetailCommand){
		return areaApplicationService.queryDetail(areaQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:area:detail')")
	@ApiOperation("列表查询区域")
	@GetMapping
	public MultiResponse<AreaVO> queryList(@RequestBody AreaQueryListCommand areaQueryListCommand){
		return areaApplicationService.queryList(areaQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:area:pageQuery')")
	@ApiOperation("分页查询区域")
	@GetMapping
	public PageResponse<AreaVO> pageQueryList(@RequestBody AreaPageQueryCommand areaPageQueryCommand){
		return areaApplicationService.pageQuery(areaPageQueryCommand);
	}
}