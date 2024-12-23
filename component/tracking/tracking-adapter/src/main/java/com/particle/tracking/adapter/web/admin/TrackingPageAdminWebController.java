package com.particle.tracking.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tracking.client.api.ITrackingPageApplicationService;
import com.particle.tracking.client.api.representation.ITrackingPageRepresentationApplicationService;
import com.particle.tracking.client.dto.command.TrackingPageCreateCommand;
import com.particle.tracking.client.dto.command.TrackingPageUpdateCommand;
import com.particle.tracking.client.dto.command.representation.TrackingPagePageQueryCommand;
import com.particle.tracking.client.dto.command.representation.TrackingPageQueryListCommand;
import com.particle.tracking.client.dto.data.TrackingPageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 埋点页面后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
@Tag(name = "埋点页面pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/tracking_page")
public class TrackingPageAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ITrackingPageApplicationService iTrackingPageApplicationService;
	@Autowired
	private ITrackingPageRepresentationApplicationService iTrackingPageRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:trackingPage:create')")
	@Operation(summary = "添加埋点页面")
	@PostMapping("/create")
	@OpLog(name = "添加埋点页面",module = OpLogConstants.Module.tracking,type = OpLogConstants.Type.create)
	public SingleResponse<TrackingPageVO> create(@RequestBody TrackingPageCreateCommand trackingPageCreateCommand){
		return iTrackingPageApplicationService.create(trackingPageCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:trackingPage:delete')")
	@Operation(summary = "删除埋点页面")
	@DeleteMapping("/delete")
	@OpLog(name = "删除埋点页面",module = OpLogConstants.Module.tracking,type = OpLogConstants.Type.delete)
	public SingleResponse<TrackingPageVO> delete(@RequestBody IdCommand deleteCommand){
		return iTrackingPageApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:trackingPage:update')")
	@Operation(summary = "更新埋点页面")
	@PutMapping("/update")
	@OpLog(name = "更新埋点页面",module = OpLogConstants.Module.tracking,type = OpLogConstants.Type.update)
	public SingleResponse<TrackingPageVO> update(@RequestBody TrackingPageUpdateCommand trackingPageUpdateCommand){
		return iTrackingPageApplicationService.update(trackingPageUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:trackingPage:update')")
	@Operation(summary = "埋点页面更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<TrackingPageVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iTrackingPageRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:trackingPage:detail')")
	@Operation(summary = "埋点页面详情展示")
	@GetMapping("/detail")
	public SingleResponse<TrackingPageVO> queryDetail(IdCommand detailCommand){
		return iTrackingPageRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:trackingPage:queryList')")
	@Operation(summary = "列表查询埋点页面")
	@GetMapping("/list")
	public MultiResponse<TrackingPageVO> queryList(TrackingPageQueryListCommand trackingPageQueryListCommand){
		return iTrackingPageRepresentationApplicationService.queryList(trackingPageQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:trackingPage:pageQuery')")
	@Operation(summary = "分页查询埋点页面")
	@GetMapping("/page")
	public PageResponse<TrackingPageVO> pageQueryList(TrackingPagePageQueryCommand trackingPagePageQueryCommand){
		return iTrackingPageRepresentationApplicationService.pageQuery(trackingPagePageQueryCommand);
	}

}
