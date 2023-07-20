package com.particle.tracking.adapter.web.admin;

import com.particle.tracking.client.api.ITrackingPageRecordApplicationService;
import com.particle.tracking.client.api.representation.ITrackingPageRecordRepresentationApplicationService;
import com.particle.tracking.client.dto.command.TrackingPageRecordCreateCommand;
import com.particle.tracking.client.dto.data.TrackingPageRecordVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.tracking.client.dto.command.TrackingPageRecordUpdateCommand;
import com.particle.tracking.client.dto.command.representation.TrackingPageRecordPageQueryCommand;
import com.particle.tracking.client.dto.command.representation.TrackingPageRecordQueryListCommand;
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
 * 页面埋点记录后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
@Tag(name = "页面埋点记录pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/tracking_page_record")
public class TrackingPageRecordAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ITrackingPageRecordApplicationService iTrackingPageRecordApplicationService;
	@Autowired
	private ITrackingPageRecordRepresentationApplicationService iTrackingPageRecordRepresentationApplicationService;


	@PreAuthorize("hasAuthority('admin:web:trackingPageRecord:delete')")
	@Operation(summary = "删除页面埋点记录")
	@DeleteMapping("/delete")
	@OpLog(name = "删除页面埋点记录",module = OpLogConstants.Module.tracking,type = OpLogConstants.Type.delete)
	public SingleResponse<TrackingPageRecordVO> delete(@RequestBody IdCommand deleteCommand){
		return iTrackingPageRecordApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:trackingPageRecord:detail')")
	@Operation(summary = "页面埋点记录详情展示")
	@GetMapping("/detail")
	public SingleResponse<TrackingPageRecordVO> queryDetail(IdCommand detailCommand){
		return iTrackingPageRecordRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:trackingPageRecord:queryList')")
	@Operation(summary = "列表查询页面埋点记录")
	@GetMapping("/list")
	public MultiResponse<TrackingPageRecordVO> queryList(TrackingPageRecordQueryListCommand trackingPageRecordQueryListCommand){
		return iTrackingPageRecordRepresentationApplicationService.queryList(trackingPageRecordQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:trackingPageRecord:pageQuery')")
	@Operation(summary = "分页查询页面埋点记录")
	@GetMapping("/page")
	public PageResponse<TrackingPageRecordVO> pageQueryList(TrackingPageRecordPageQueryCommand trackingPageRecordPageQueryCommand){
		return iTrackingPageRecordRepresentationApplicationService.pageQuery(trackingPageRecordPageQueryCommand);
	}

}