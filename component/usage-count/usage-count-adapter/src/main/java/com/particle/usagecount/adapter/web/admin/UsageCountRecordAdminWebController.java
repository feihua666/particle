package com.particle.usagecount.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.usagecount.client.api.IUsageCountRecordApplicationService;
import com.particle.usagecount.client.api.representation.IUsageCountRecordRepresentationApplicationService;
import com.particle.usagecount.client.dto.command.representation.UsageCountRecordPageQueryCommand;
import com.particle.usagecount.client.dto.command.representation.UsageCountRecordQueryListCommand;
import com.particle.usagecount.client.dto.data.UsageCountRecordVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 使用次数记录后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
@Tag(name = "使用次数记录pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/usage_count_record")
public class UsageCountRecordAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IUsageCountRecordApplicationService iUsageCountRecordApplicationService;
	@Autowired
	private IUsageCountRecordRepresentationApplicationService iUsageCountRecordRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:usageCountRecord:delete')")
	@Operation(summary = "删除使用次数记录")
	@DeleteMapping("/delete")
	@OpLog(name = "删除使用次数记录",module = OpLogConstants.Module.usageCount,type = OpLogConstants.Type.delete)
	public SingleResponse<UsageCountRecordVO> delete(@RequestBody IdCommand deleteCommand){
		return iUsageCountRecordApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:usageCountRecord:detail')")
	@Operation(summary = "使用次数记录详情展示")
	@GetMapping("/detail")
	public SingleResponse<UsageCountRecordVO> queryDetail(IdCommand detailCommand){
		return iUsageCountRecordRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:usageCountRecord:queryList')")
	@Operation(summary = "列表查询使用次数记录")
	@GetMapping("/list")
	public MultiResponse<UsageCountRecordVO> queryList(UsageCountRecordQueryListCommand usageCountRecordQueryListCommand){
		return iUsageCountRecordRepresentationApplicationService.queryList(usageCountRecordQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:usageCountRecord:pageQuery')")
	@Operation(summary = "分页查询使用次数记录")
	@GetMapping("/page")
	public PageResponse<UsageCountRecordVO> pageQueryList(UsageCountRecordPageQueryCommand usageCountRecordPageQueryCommand){
		return iUsageCountRecordRepresentationApplicationService.pageQuery(usageCountRecordPageQueryCommand);
	}

}