package com.particle.usagecount.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.usagecount.client.api.IUsageCountRecordDetailApplicationService;
import com.particle.usagecount.client.api.representation.IUsageCountRecordDetailRepresentationApplicationService;
import com.particle.usagecount.client.dto.command.representation.UsageCountRecordDetailPageQueryCommand;
import com.particle.usagecount.client.dto.command.representation.UsageCountRecordDetailQueryListCommand;
import com.particle.usagecount.client.dto.data.UsageCountRecordDetailVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 使用次数记录明细后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-10-23 16:23:29
 */
@Tag(name = "使用次数记录明细pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/usage_count_record_detail")
public class UsageCountRecordDetailAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IUsageCountRecordDetailApplicationService iUsageCountRecordDetailApplicationService;
	@Autowired
	private IUsageCountRecordDetailRepresentationApplicationService iUsageCountRecordDetailRepresentationApplicationService;


	@PreAuthorize("hasAuthority('admin:web:usageCountRecordDetail:delete')")
	@Operation(summary = "删除使用次数记录明细")
	@DeleteMapping("/delete")
	@OpLog(name = "删除使用次数记录明细",module = OpLogConstants.Module.usageCount,type = OpLogConstants.Type.delete)
	public SingleResponse<UsageCountRecordDetailVO> delete(@RequestBody IdCommand deleteCommand){
		return iUsageCountRecordDetailApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:usageCountRecordDetail:detail')")
	@Operation(summary = "使用次数记录明细详情展示")
	@GetMapping("/detail")
	public SingleResponse<UsageCountRecordDetailVO> queryDetail(IdCommand detailCommand){
		return iUsageCountRecordDetailRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:usageCountRecordDetail:queryList')")
	@Operation(summary = "列表查询使用次数记录明细")
	@GetMapping("/list")
	public MultiResponse<UsageCountRecordDetailVO> queryList(UsageCountRecordDetailQueryListCommand usageCountRecordDetailQueryListCommand){
		return iUsageCountRecordDetailRepresentationApplicationService.queryList(usageCountRecordDetailQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:usageCountRecordDetail:pageQuery')")
	@Operation(summary = "分页查询使用次数记录明细")
	@GetMapping("/page")
	public PageResponse<UsageCountRecordDetailVO> pageQueryList(UsageCountRecordDetailPageQueryCommand usageCountRecordDetailPageQueryCommand){
		return iUsageCountRecordDetailRepresentationApplicationService.pageQuery(usageCountRecordDetailPageQueryCommand);
	}

}