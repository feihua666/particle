package com.particle.lowcode.adapter.generator.web.admin;

import com.particle.lowcode.client.generator.api.ILowcodeDatasourceApplicationService;
import com.particle.lowcode.client.generator.api.representation.ILowcodeDatasourceRepresentationApplicationService;
import com.particle.lowcode.client.generator.dto.command.LowcodeDatasourceCreateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeDatasourceVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeDatasourceUpdateCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeDatasourcePageQueryCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeDatasourceQueryListCommand;
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
 * 低代码数据源后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Api(tags = "低代码数据源pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/lowcode-datasource")
public class LowcodeDatasourceAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ILowcodeDatasourceApplicationService iLowcodeDatasourceApplicationService;
	@Autowired
	private ILowcodeDatasourceRepresentationApplicationService iLowcodeDatasourceRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:lowcodeDatasource:create')")
	@ApiOperation("添加低代码数据源")
	@PostMapping("/create")
	public SingleResponse<LowcodeDatasourceVO> create(@RequestBody LowcodeDatasourceCreateCommand lowcodeDatasourceCreateCommand){
		return iLowcodeDatasourceApplicationService.create(lowcodeDatasourceCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeDatasource:delete')")
	@ApiOperation("删除低代码数据源")
	@DeleteMapping("/delete")
	public SingleResponse<LowcodeDatasourceVO> delete(@RequestBody IdCommand deleteCommand){
		return iLowcodeDatasourceApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeDatasource:update')")
	@ApiOperation("更新低代码数据源")
	@PutMapping("/update")
	public SingleResponse<LowcodeDatasourceVO> update(@RequestBody LowcodeDatasourceUpdateCommand lowcodeDatasourceUpdateCommand){
		return iLowcodeDatasourceApplicationService.update(lowcodeDatasourceUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeDatasource:update')")
	@ApiOperation("低代码数据源更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<LowcodeDatasourceVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iLowcodeDatasourceRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeDatasource:detail')")
	@ApiOperation("低代码数据源详情展示")
	@GetMapping("/detail")
	public SingleResponse<LowcodeDatasourceVO> queryDetail(IdCommand detailCommand){
		return iLowcodeDatasourceRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeDatasource:queryList')")
	@ApiOperation("列表查询低代码数据源")
	@GetMapping("/list")
	public MultiResponse<LowcodeDatasourceVO> queryList(LowcodeDatasourceQueryListCommand lowcodeDatasourceQueryListCommand){
		return iLowcodeDatasourceRepresentationApplicationService.queryList(lowcodeDatasourceQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeDatasource:pageQuery')")
	@ApiOperation("分页查询低代码数据源")
	@GetMapping("/page")
	public PageResponse<LowcodeDatasourceVO> pageQueryList(LowcodeDatasourcePageQueryCommand lowcodeDatasourcePageQueryCommand){
		return iLowcodeDatasourceRepresentationApplicationService.pageQuery(lowcodeDatasourcePageQueryCommand);
	}

}