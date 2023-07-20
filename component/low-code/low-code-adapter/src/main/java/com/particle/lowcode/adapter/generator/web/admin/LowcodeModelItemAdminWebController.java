package com.particle.lowcode.adapter.generator.web.admin;

import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.lowcode.client.generator.api.ILowcodeModelItemApplicationService;
import com.particle.lowcode.client.generator.api.representation.ILowcodeModelItemRepresentationApplicationService;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelItemCreateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelItemVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelItemUpdateCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeModelItemPageQueryCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeModelItemQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
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
 * 低代码模型项目后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Tag(name = "低代码模型项目pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/lowcode-model-item")
public class LowcodeModelItemAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ILowcodeModelItemApplicationService iLowcodeModelItemApplicationService;
	@Autowired
	private ILowcodeModelItemRepresentationApplicationService iLowcodeModelItemRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:lowcodeModelItem:create')")
	@Operation(summary = "添加低代码模型项目")
	@PostMapping("/create")
	@OpLog(name = "添加低代码模型项目",module = OpLogConstants.Module.lowCode,type = OpLogConstants.Type.create)
	public SingleResponse<LowcodeModelItemVO> create(@RequestBody LowcodeModelItemCreateCommand lowcodeModelItemCreateCommand){
		return iLowcodeModelItemApplicationService.create(lowcodeModelItemCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeModelItem:delete')")
	@Operation(summary = "删除低代码模型项目")
	@DeleteMapping("/delete")
	@OpLog(name = "删除低代码模型项目",module = OpLogConstants.Module.lowCode,type = OpLogConstants.Type.delete)
	public SingleResponse<LowcodeModelItemVO> delete(@RequestBody IdCommand deleteCommand){
		return iLowcodeModelItemApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeModelItem:update')")
	@Operation(summary = "更新低代码模型项目")
	@PutMapping("/update")
	@OpLog(name = "更新低代码模型项目",module = OpLogConstants.Module.lowCode,type = OpLogConstants.Type.update)
	public SingleResponse<LowcodeModelItemVO> update(@RequestBody LowcodeModelItemUpdateCommand lowcodeModelItemUpdateCommand){
		return iLowcodeModelItemApplicationService.update(lowcodeModelItemUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeModelItem:update')")
	@Operation(summary = "低代码模型项目更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<LowcodeModelItemVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iLowcodeModelItemRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeModelItem:detail')")
	@Operation(summary = "低代码模型项目详情展示")
	@GetMapping("/detail")
	public SingleResponse<LowcodeModelItemVO> queryDetail(IdCommand detailCommand){
		return iLowcodeModelItemRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeModelItem:queryList')")
	@Operation(summary = "列表查询低代码模型项目")
	@GetMapping("/list")
	public MultiResponse<LowcodeModelItemVO> queryList(LowcodeModelItemQueryListCommand lowcodeModelItemQueryListCommand){
		return iLowcodeModelItemRepresentationApplicationService.queryList(lowcodeModelItemQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:lowcodeModelItem:pageQuery')")
	@Operation(summary = "分页查询低代码模型项目")
	@GetMapping("/page")
	public PageResponse<LowcodeModelItemVO> pageQueryList(LowcodeModelItemPageQueryCommand lowcodeModelItemPageQueryCommand){
		return iLowcodeModelItemRepresentationApplicationService.pageQuery(lowcodeModelItemPageQueryCommand);
	}

}