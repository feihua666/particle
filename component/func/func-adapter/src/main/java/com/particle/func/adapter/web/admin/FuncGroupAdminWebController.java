package com.particle.func.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.func.client.api.IFuncGroupApplicationService;
import com.particle.func.client.api.representation.IFuncGroupRepresentationApplicationService;
import com.particle.func.client.dto.command.FuncGroupCreateCommand;
import com.particle.func.client.dto.command.FuncGroupUpdateCommand;
import com.particle.func.client.dto.command.representation.FuncGroupPageQueryCommand;
import com.particle.func.client.dto.command.representation.FuncGroupQueryListCommand;
import com.particle.func.client.dto.data.FuncGroupVO;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 功能组后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
@Tag(name = "功能组pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/func-group")
public class FuncGroupAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IFuncGroupApplicationService iFuncGroupApplicationService;
	@Autowired
	private IFuncGroupRepresentationApplicationService iFuncGroupRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:funcGroup:create')")
	@Operation(summary = "添加功能组")
	@PostMapping("/create")
	@OpLog(name = "添加功能组",module = OpLogConstants.Module.func,type = OpLogConstants.Type.create)
	public SingleResponse<FuncGroupVO> create(@RequestBody FuncGroupCreateCommand funcGroupCreateCommand){
		return iFuncGroupApplicationService.create(funcGroupCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcGroup:delete')")
	@Operation(summary = "删除功能组")
	@DeleteMapping("/delete")
	@OpLog(name = "删除功能组",module = OpLogConstants.Module.func,type = OpLogConstants.Type.delete)
	public SingleResponse<FuncGroupVO> delete(@RequestBody IdCommand funcGroupDeleteCommand){
		return iFuncGroupApplicationService.delete(funcGroupDeleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcGroup:update')")
	@Operation(summary = "更新功能组")
	@PutMapping("/update")
	@OpLog(name = "更新功能组",module = OpLogConstants.Module.func,type = OpLogConstants.Type.update)
	public SingleResponse<FuncGroupVO> update(@RequestBody FuncGroupUpdateCommand funcGroupUpdateCommand){
		return iFuncGroupApplicationService.update(funcGroupUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcGroup:update')")
	@Operation(summary = "功能组更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<FuncGroupVO> queryDetailForUpdate(IdCommand funcGroupQueryDetailForUpdateCommand){
		return iFuncGroupRepresentationApplicationService.queryDetailForUpdate(funcGroupQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcGroup:detail')")
	@Operation(summary = "功能组详情展示")
	@GetMapping("/detail")
	public SingleResponse<FuncGroupVO> queryDetail(IdCommand funcGroupQueryDetailCommand){
		return iFuncGroupRepresentationApplicationService.queryDetail(funcGroupQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcGroup:queryList')")
	@Operation(summary = "列表查询功能组")
	@GetMapping("/list")
	public MultiResponse<FuncGroupVO> queryList(FuncGroupQueryListCommand funcGroupQueryListCommand){
		return iFuncGroupRepresentationApplicationService.queryList(funcGroupQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:funcGroup:pageQuery')")
	@Operation(summary = "分页查询功能组")
	@GetMapping("/page")
	public PageResponse<FuncGroupVO> pageQueryList(FuncGroupPageQueryCommand funcGroupPageQueryCommand){
		return iFuncGroupRepresentationApplicationService.pageQuery(funcGroupPageQueryCommand);
	}

}