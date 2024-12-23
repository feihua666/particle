package com.particle.usagecount.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.usagecount.client.api.IUsageCountConfigApplicationService;
import com.particle.usagecount.client.api.representation.IUsageCountConfigRepresentationApplicationService;
import com.particle.usagecount.client.dto.command.UsageCountConfigCreateCommand;
import com.particle.usagecount.client.dto.command.UsageCountConfigUpdateCommand;
import com.particle.usagecount.client.dto.command.representation.UsageCountConfigPageQueryCommand;
import com.particle.usagecount.client.dto.command.representation.UsageCountConfigQueryListCommand;
import com.particle.usagecount.client.dto.data.UsageCountConfigVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 使用次数配置后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
@Tag(name = "使用次数配置pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/usage_count_config")
public class UsageCountConfigAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IUsageCountConfigApplicationService iUsageCountConfigApplicationService;
	@Autowired
	private IUsageCountConfigRepresentationApplicationService iUsageCountConfigRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:usageCountConfig:create')")
	@Operation(summary = "添加使用次数配置")
	@PostMapping("/create")
	@OpLog(name = "添加使用次数配置",module = OpLogConstants.Module.usageCount,type = OpLogConstants.Type.create)
	public SingleResponse<UsageCountConfigVO> create(@RequestBody UsageCountConfigCreateCommand usageCountConfigCreateCommand){
		return iUsageCountConfigApplicationService.create(usageCountConfigCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:usageCountConfig:delete')")
	@Operation(summary = "删除使用次数配置")
	@DeleteMapping("/delete")
	@OpLog(name = "删除使用次数配置",module = OpLogConstants.Module.usageCount,type = OpLogConstants.Type.delete)
	public SingleResponse<UsageCountConfigVO> delete(@RequestBody IdCommand deleteCommand){
		return iUsageCountConfigApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:usageCountConfig:update')")
	@Operation(summary = "更新使用次数配置")
	@PutMapping("/update")
	@OpLog(name = "更新使用次数配置",module = OpLogConstants.Module.usageCount,type = OpLogConstants.Type.update)
	public SingleResponse<UsageCountConfigVO> update(@RequestBody UsageCountConfigUpdateCommand usageCountConfigUpdateCommand){
		return iUsageCountConfigApplicationService.update(usageCountConfigUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:usageCountConfig:update')")
	@Operation(summary = "使用次数配置更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<UsageCountConfigVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iUsageCountConfigRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:usageCountConfig:detail')")
	@Operation(summary = "使用次数配置详情展示")
	@GetMapping("/detail")
	public SingleResponse<UsageCountConfigVO> queryDetail(IdCommand detailCommand){
		return iUsageCountConfigRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:usageCountConfig:queryList')")
	@Operation(summary = "列表查询使用次数配置")
	@GetMapping("/list")
	public MultiResponse<UsageCountConfigVO> queryList(UsageCountConfigQueryListCommand usageCountConfigQueryListCommand){
		return iUsageCountConfigRepresentationApplicationService.queryList(usageCountConfigQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:usageCountConfig:pageQuery')")
	@Operation(summary = "分页查询使用次数配置")
	@GetMapping("/page")
	public PageResponse<UsageCountConfigVO> pageQueryList(UsageCountConfigPageQueryCommand usageCountConfigPageQueryCommand){
		return iUsageCountConfigRepresentationApplicationService.pageQuery(usageCountConfigPageQueryCommand);
	}

}
