package com.particle.area.adapter.web.admin;

import com.particle.area.client.api.IAreaApplicationService;
import com.particle.area.client.api.representation.IAreaRepresentationApplicationService;
import com.particle.area.client.dto.command.AreaCreateCommand;
import com.particle.area.client.dto.command.AreaUpdateCommand;
import com.particle.area.client.dto.command.representation.AreaPageQueryCommand;
import com.particle.area.client.dto.command.representation.AreaQueryListCommand;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
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
 * @since 2022-07-18
 */
@Api(tags = "区域pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/area")
public class AreaAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IAreaApplicationService iAreaApplicationService;
	@Autowired
	private IAreaRepresentationApplicationService iAreaRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:area:create')")
	@ApiOperation("添加区域")
	@PostMapping("/create")
	public SingleResponse<AreaVO> create(@RequestBody AreaCreateCommand areaCreateCommand){
		return iAreaApplicationService.create(areaCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:area:delete')")
	@ApiOperation("删除区域")
	@DeleteMapping("/delete")
	public SingleResponse<AreaVO> delete(@RequestBody IdCommand areaDeleteCommand){
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
	public SingleResponse<AreaVO> queryDetailForUpdate(IdCommand areaQueryDetailForUpdateCommand){
		return iAreaRepresentationApplicationService.queryDetailForUpdate(areaQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:area:detail')")
	@ApiOperation("区域详情展示")
	@GetMapping("/detail")
	public SingleResponse<AreaVO> queryDetail(IdCommand areaQueryDetailCommand){
		return iAreaRepresentationApplicationService.queryDetail(areaQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:area:queryList')")
	@ApiOperation("列表查询区域")
	@GetMapping("/list")
	public MultiResponse<AreaVO> queryList(AreaQueryListCommand areaQueryListCommand){
		return iAreaRepresentationApplicationService.queryList(areaQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:area:pageQuery')")
	@ApiOperation("分页查询区域")
	@GetMapping("/page")
	public PageResponse<AreaVO> pageQueryList(AreaPageQueryCommand areaPageQueryCommand){
		return iAreaRepresentationApplicationService.pageQuery(areaPageQueryCommand);
	}

}