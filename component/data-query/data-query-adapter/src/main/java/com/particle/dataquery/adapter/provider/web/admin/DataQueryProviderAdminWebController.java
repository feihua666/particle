package com.particle.dataquery.adapter.provider.web.admin;

import com.particle.dataquery.client.provider.api.IDataQueryProviderApplicationService;
import com.particle.dataquery.client.provider.api.representation.IDataQueryProviderRepresentationApplicationService;
import com.particle.dataquery.client.provider.dto.command.DataQueryProviderCreateCommand;
import com.particle.dataquery.client.provider.dto.data.DataQueryProviderVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.client.provider.dto.command.DataQueryProviderUpdateCommand;
import com.particle.dataquery.client.provider.dto.command.representation.DataQueryProviderPageQueryCommand;
import com.particle.dataquery.client.provider.dto.command.representation.DataQueryProviderQueryListCommand;
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
 * 数据查询供应商后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@Api(tags = "数据查询供应商pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_query_provider")
public class DataQueryProviderAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataQueryProviderApplicationService iDataQueryProviderApplicationService;
	@Autowired
	private IDataQueryProviderRepresentationApplicationService iDataQueryProviderRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:dataQueryProvider:create')")
	@ApiOperation("添加数据查询供应商")
	@PostMapping("/create")
	public SingleResponse<DataQueryProviderVO> create(@RequestBody DataQueryProviderCreateCommand dataQueryProviderCreateCommand){
		return iDataQueryProviderApplicationService.create(dataQueryProviderCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryProvider:delete')")
	@ApiOperation("删除数据查询供应商")
	@DeleteMapping("/delete")
	public SingleResponse<DataQueryProviderVO> delete(@RequestBody IdCommand deleteCommand){
		return iDataQueryProviderApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryProvider:update')")
	@ApiOperation("更新数据查询供应商")
	@PutMapping("/update")
	public SingleResponse<DataQueryProviderVO> update(@RequestBody DataQueryProviderUpdateCommand dataQueryProviderUpdateCommand){
		return iDataQueryProviderApplicationService.update(dataQueryProviderUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryProvider:update')")
	@ApiOperation("数据查询供应商更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<DataQueryProviderVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iDataQueryProviderRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryProvider:detail')")
	@ApiOperation("数据查询供应商详情展示")
	@GetMapping("/detail")
	public SingleResponse<DataQueryProviderVO> queryDetail(IdCommand detailCommand){
		return iDataQueryProviderRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryProvider:queryList')")
	@ApiOperation("列表查询数据查询供应商")
	@GetMapping("/list")
	public MultiResponse<DataQueryProviderVO> queryList(DataQueryProviderQueryListCommand dataQueryProviderQueryListCommand){
		return iDataQueryProviderRepresentationApplicationService.queryList(dataQueryProviderQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataQueryProvider:pageQuery')")
	@ApiOperation("分页查询数据查询供应商")
	@GetMapping("/page")
	public PageResponse<DataQueryProviderVO> pageQueryList(DataQueryProviderPageQueryCommand dataQueryProviderPageQueryCommand){
		return iDataQueryProviderRepresentationApplicationService.pageQuery(dataQueryProviderPageQueryCommand);
	}

}