package com.particle.area.adapter.web;

import com.particle.area.client.api.IAreaApplicationService;
import com.particle.area.client.dto.command.AreaCreateCommand;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.area.client.dto.command.AreaQueryDetailForUpdateCommand;
import com.particle.area.client.dto.command.AreaQueryDetailCommand;
import com.particle.area.client.dto.command.AreaDeleteCommand;
import com.particle.area.client.dto.command.AreaUpdateCommand;
import com.particle.area.client.dto.command.AreaPageQueryCommand;
import com.particle.area.client.dto.command.AreaQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
/**
 * <p>
 * 区域后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@Api(tags = "区域pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/area")
public class AreaAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IAreaApplicationService iAreaApplicationService;

	@PreAuthorize("hasAuthority('admin:web:area:create')")
	@ApiOperation("添加区域")
	@PostMapping("/create")
	public SingleResponse<AreaVO> create(@RequestBody AreaCreateCommand areaCreateCommand){
		return iAreaApplicationService.create(areaCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:area:delete')")
	@ApiOperation("删除区域")
	@DeleteMapping("/delete")
	public SingleResponse<AreaVO> delete(@RequestBody AreaDeleteCommand areaDeleteCommand){
		return iAreaApplicationService.delete(areaDeleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:area:update')")
	@ApiOperation("更新区域")
	@PutMapping("/update")
	public SingleResponse<AreaVO> update(@RequestBody AreaUpdateCommand areaUpdateCommand){
		return iAreaApplicationService.update(areaUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:area:update')")
	@ApiOperation("区域更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<AreaVO> queryDetailForUpdate(AreaQueryDetailForUpdateCommand areaQueryDetailForUpdateCommand){
		return iAreaApplicationService.queryDetailForUpdate(areaQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:area:detail')")
	@ApiOperation("区域详情展示")
	@GetMapping("/detail")
	public SingleResponse<AreaVO> queryDetail(AreaQueryDetailCommand areaQueryDetailCommand){
		return iAreaApplicationService.queryDetail(areaQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:area:queryList')")
	@ApiOperation("列表查询区域")
	@GetMapping("/list")
	public MultiResponse<AreaVO> queryList(AreaQueryListCommand areaQueryListCommand){
		return iAreaApplicationService.queryList(areaQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:area:pageQuery')")
	@ApiOperation("分页查询区域")
	@GetMapping("/page")
	public PageResponse<AreaVO> pageQueryList(AreaPageQueryCommand areaPageQueryCommand){
		return iAreaApplicationService.pageQuery(areaPageQueryCommand);
	}

}