package com.particle.dataconstraint.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.dataconstraint.client.api.IDataScopeCustomDataRelApplicationService;
import com.particle.dataconstraint.client.api.representation.IDataScopeCustomDataRelRepresentationApplicationService;
import com.particle.dataconstraint.client.dto.command.DataScopeAssignCustomDataCommand;
import com.particle.dataconstraint.client.dto.command.DataScopeCustomDataRelCreateCommand;
import com.particle.dataconstraint.client.dto.command.DataScopeCustomDataRelUpdateCommand;
import com.particle.dataconstraint.client.dto.command.representation.DataScopeCustomDataRelPageQueryCommand;
import com.particle.dataconstraint.client.dto.command.representation.DataScopeCustomDataRelQueryListCommand;
import com.particle.dataconstraint.client.dto.data.DataScopeCustomDataRelVO;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 数据范围自定义数据关系后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:55
 */
@Tag(name = "数据范围自定义数据关系pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/data_scope_custom_data_rel")
public class DataScopeCustomDataRelAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataScopeCustomDataRelApplicationService iDataScopeCustomDataRelApplicationService;
	@Autowired
	private IDataScopeCustomDataRelRepresentationApplicationService iDataScopeCustomDataRelRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:dataScopeCustomDataRel:create')")
	@Operation(summary = "添加数据范围自定义数据关系")
	@PostMapping("/create")
	@OpLog(name = "添加数据范围自定义数据关系",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
	public SingleResponse<DataScopeCustomDataRelVO> create(@RequestBody DataScopeCustomDataRelCreateCommand dataScopeCustomDataRelCreateCommand){
		return iDataScopeCustomDataRelApplicationService.create(dataScopeCustomDataRelCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataScopeCustomDataRel:delete')")
	@Operation(summary = "删除数据范围自定义数据关系")
	@DeleteMapping("/delete")
	@OpLog(name = "删除数据范围自定义数据关系",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
	public SingleResponse<DataScopeCustomDataRelVO> delete(@RequestBody IdCommand deleteCommand){
		return iDataScopeCustomDataRelApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataScopeCustomDataRel:update')")
	@Operation(summary = "更新数据范围自定义数据关系")
	@PutMapping("/update")
	@OpLog(name = "更新数据范围自定义数据关系",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
	public SingleResponse<DataScopeCustomDataRelVO> update(@RequestBody DataScopeCustomDataRelUpdateCommand dataScopeCustomDataRelUpdateCommand){
		return iDataScopeCustomDataRelApplicationService.update(dataScopeCustomDataRelUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataScopeCustomDataRel:update')")
	@Operation(summary = "数据范围自定义数据关系更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<DataScopeCustomDataRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iDataScopeCustomDataRelRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataScopeCustomDataRel:detail')")
	@Operation(summary = "数据范围自定义数据关系详情展示")
	@GetMapping("/detail")
	public SingleResponse<DataScopeCustomDataRelVO> queryDetail(IdCommand detailCommand){
		return iDataScopeCustomDataRelRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataScopeCustomDataRel:queryList')")
	@Operation(summary = "列表查询数据范围自定义数据关系")
	@GetMapping("/list")
	public MultiResponse<DataScopeCustomDataRelVO> queryList(DataScopeCustomDataRelQueryListCommand dataScopeCustomDataRelQueryListCommand){
		return iDataScopeCustomDataRelRepresentationApplicationService.queryList(dataScopeCustomDataRelQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dataScopeCustomDataRel:pageQuery')")
	@Operation(summary = "分页查询数据范围自定义数据关系")
	@GetMapping("/page")
	public PageResponse<DataScopeCustomDataRelVO> pageQueryList(DataScopeCustomDataRelPageQueryCommand dataScopeCustomDataRelPageQueryCommand){
		return iDataScopeCustomDataRelRepresentationApplicationService.pageQuery(dataScopeCustomDataRelPageQueryCommand);
	}

	@Operation(summary = "数据范围分配自定义数据")
	@PreAuthorize("hasAuthority('admin:web:dataScopeCustomDataRel:dataScopeAssignCustomData')")
	@PostMapping("/dataScope/assign/customData")
	@ResponseStatus(HttpStatus.CREATED)
	public Response dataScopeAssignCustomData(@RequestBody DataScopeAssignCustomDataCommand cf) {
		return iDataScopeCustomDataRelApplicationService.dataScopeAssignCustomData(cf);
	}

	@Operation(summary = "根据数据范围ID查询已分配的自定义数据id")
	@PreAuthorize("hasAuthority('admin:web:dataScopeCustomDataRel:queryCustomDataIdsByDataScopeId')")
	@GetMapping("/queryCustomDataIdsByDataScopeId")
	@ResponseStatus(HttpStatus.OK)
	public MultiResponse<Long> queryCustomDataIdsByDataScopeId(IdCommand dataScopeIdCommand) {
		return iDataScopeCustomDataRelRepresentationApplicationService.queryCustomDataIdsByDataScopeId( dataScopeIdCommand);
	}

	@Operation(summary = "清空数据范围下的所有自定义数据")
	@PreAuthorize("hasAuthority('admin:web:dataScopeCustomDataRel:deleteByDataScopeId')")
	@DeleteMapping("/deleteByDataScopeId")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Response deleteByDataScopeId(@RequestBody IdCommand dataScopeIdCommand) {
		return iDataScopeCustomDataRelApplicationService.deleteByDataScopeId(dataScopeIdCommand);
	}
}