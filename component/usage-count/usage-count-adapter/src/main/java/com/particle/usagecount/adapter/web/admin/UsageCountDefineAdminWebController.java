package com.particle.usagecount.adapter.web.admin;

import com.particle.usagecount.client.api.IUsageCountDefineApplicationService;
import com.particle.usagecount.client.api.representation.IUsageCountDefineRepresentationApplicationService;
import com.particle.usagecount.client.dto.command.UsageCountDefineCreateCommand;
import com.particle.usagecount.client.dto.data.UsageCountDefineVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.usagecount.client.dto.command.UsageCountDefineUpdateCommand;
import com.particle.usagecount.client.dto.command.representation.UsageCountDefinePageQueryCommand;
import com.particle.usagecount.client.dto.command.representation.UsageCountDefineQueryListCommand;
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
import com.particle.global.dataaudit.op.OpLog;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
/**
 * <p>
 * 使用次数定义后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:08
 */
@Tag(name = "使用次数定义pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/usage_count_define")
public class UsageCountDefineAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IUsageCountDefineApplicationService iUsageCountDefineApplicationService;
	@Autowired
	private IUsageCountDefineRepresentationApplicationService iUsageCountDefineRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:usageCountDefine:create')")
	@Operation(summary = "添加使用次数定义")
	@PostMapping("/create")
	@OpLog(name = "添加使用次数定义",module = OpLogConstants.Module.usageCount,type = OpLogConstants.Type.create)
	public SingleResponse<UsageCountDefineVO> create(@RequestBody UsageCountDefineCreateCommand usageCountDefineCreateCommand){
		return iUsageCountDefineApplicationService.create(usageCountDefineCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:usageCountDefine:delete')")
	@Operation(summary = "删除使用次数定义")
	@DeleteMapping("/delete")
	@OpLog(name = "删除使用次数定义",module = OpLogConstants.Module.usageCount,type = OpLogConstants.Type.delete)
	public SingleResponse<UsageCountDefineVO> delete(@RequestBody IdCommand deleteCommand){
		return iUsageCountDefineApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:usageCountDefine:update')")
	@Operation(summary = "更新使用次数定义")
	@PutMapping("/update")
	@OpLog(name = "更新使用次数定义",module = OpLogConstants.Module.usageCount,type = OpLogConstants.Type.update)
	public SingleResponse<UsageCountDefineVO> update(@RequestBody UsageCountDefineUpdateCommand usageCountDefineUpdateCommand){
		return iUsageCountDefineApplicationService.update(usageCountDefineUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:usageCountDefine:update')")
	@Operation(summary = "使用次数定义更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<UsageCountDefineVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iUsageCountDefineRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:usageCountDefine:detail')")
	@Operation(summary = "使用次数定义详情展示")
	@GetMapping("/detail")
	public SingleResponse<UsageCountDefineVO> queryDetail(IdCommand detailCommand){
		return iUsageCountDefineRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:usageCountDefine:queryList')")
	@Operation(summary = "列表查询使用次数定义")
	@GetMapping("/list")
	public MultiResponse<UsageCountDefineVO> queryList(UsageCountDefineQueryListCommand usageCountDefineQueryListCommand){
		return iUsageCountDefineRepresentationApplicationService.queryList(usageCountDefineQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:usageCountDefine:pageQuery')")
	@Operation(summary = "分页查询使用次数定义")
	@GetMapping("/page")
	public PageResponse<UsageCountDefineVO> pageQueryList(UsageCountDefinePageQueryCommand usageCountDefinePageQueryCommand){
		return iUsageCountDefineRepresentationApplicationService.pageQuery(usageCountDefinePageQueryCommand);
	}

}