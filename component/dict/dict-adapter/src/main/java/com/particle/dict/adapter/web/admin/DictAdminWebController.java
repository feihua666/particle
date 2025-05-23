package com.particle.dict.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.dict.client.api.IDictApplicationService;
import com.particle.dict.client.api.representation.IDictRepresentationApplicationService;
import com.particle.dict.client.dto.command.DictCreateCommand;
import com.particle.dict.client.dto.command.DictUpdateCommand;
import com.particle.dict.client.dto.command.representation.DictPageQueryCommand;
import com.particle.dict.client.dto.command.representation.DictQueryListCommand;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 字典后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Tag(name = "字典pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/dict")
public class DictAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDictApplicationService iDictApplicationService;
	@Autowired
	private IDictRepresentationApplicationService iDictRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:dict:create')")
	@Operation(summary = "添加字典")
	@PostMapping("/create")
	@OpLog(name = "添加字典",module = OpLogConstants.Module.dict,type = OpLogConstants.Type.create)
	public SingleResponse<DictVO> create(@RequestBody DictCreateCommand dictCreateCommand){
		return iDictApplicationService.create(dictCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dict:delete')")
	@Operation(summary = "删除字典")
	@DeleteMapping("/delete")
	@OpLog(name = "删除字典",module = OpLogConstants.Module.dict,type = OpLogConstants.Type.delete)
	public SingleResponse<DictVO> delete(@RequestBody IdCommand deleteCommand){
		deleteCommand.dcdo(DataConstraintConstants.data_object_dict_dict, DataConstraintContext.Action.delete.name());
		return iDictApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dict:update')")
	@Operation(summary = "更新字典")
	@PutMapping("/update")
	@OpLog(name = "更新字典",module = OpLogConstants.Module.dict,type = OpLogConstants.Type.update)
	public SingleResponse<DictVO> update(@RequestBody DictUpdateCommand dictUpdateCommand){
		dictUpdateCommand.dcdo(DataConstraintConstants.data_object_dict_dict,DataConstraintContext.Action.update.name());
		return iDictApplicationService.update(dictUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dict:update')")
	@Operation(summary = "字典更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<DictVO> queryDetailForUpdate(IdCommand dictQueryDetailForUpdateCommand){
		return iDictRepresentationApplicationService.queryDetailForUpdate(dictQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dict:detail')")
	@Operation(summary = "字典详情展示")
	@GetMapping("/detail")
	public SingleResponse<DictVO> queryDetail(IdCommand dictQueryDetailCommand){
		return iDictRepresentationApplicationService.queryDetail(dictQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dict:queryList')")
	@Operation(summary = "列表查询字典")
	@GetMapping("/list")
	public MultiResponse<DictVO> queryList(DictQueryListCommand dictQueryListCommand){
		dictQueryListCommand.dcdo(DataConstraintConstants.data_object_dict_dict,DataConstraintContext.Action.query.name());
		return iDictRepresentationApplicationService.queryList(dictQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dict:pageQuery')")
	@Operation(summary = "分页查询字典")
	@GetMapping("/page")
	public PageResponse<DictVO> pageQueryList(DictPageQueryCommand dictPageQueryCommand){
		dictPageQueryCommand.dcdo(DataConstraintConstants.data_object_dict_dict,DataConstraintContext.Action.query.name());
		return iDictRepresentationApplicationService.pageQuery(dictPageQueryCommand);
	}

}
