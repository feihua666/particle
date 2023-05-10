package com.particle.tracking.app.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tracking.app.structmapping.TrackingPageRecordAppStructMapping;
import com.particle.tracking.client.dto.command.representation.TrackingPageRecordPageQueryCommand;
import com.particle.tracking.client.dto.command.representation.TrackingPageRecordQueryListCommand;
import com.particle.tracking.client.dto.data.TrackingPageRecordVO;
import com.particle.tracking.infrastructure.dos.TrackingPageRecordDO;
import com.particle.tracking.infrastructure.service.ITrackingPageRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 页面埋点记录 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-05-10 11:44:01
 */
@Component
@Validated
public class TrackingPageRecordQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ITrackingPageRecordService iTrackingPageRecordService;

	/**
	 * 执行 页面埋点记录 列表查询指令
	 * @param trackingPageRecordQueryListCommand
	 * @return
	 */
	public MultiResponse<TrackingPageRecordVO> execute(@Valid TrackingPageRecordQueryListCommand trackingPageRecordQueryListCommand) {
		List<TrackingPageRecordDO> trackingPageRecordDO = iTrackingPageRecordService.list(trackingPageRecordQueryListCommand);
		List<TrackingPageRecordVO> trackingPageRecordVOs = TrackingPageRecordAppStructMapping.instance.trackingPageRecordDOsToTrackingPageRecordVOs(trackingPageRecordDO);
		return MultiResponse.of(trackingPageRecordVOs);
	}
	/**
	 * 执行 页面埋点记录 分页查询指令
	 * @param trackingPageRecordPageQueryCommand
	 * @return
	 */
	public PageResponse<TrackingPageRecordVO> execute(@Valid TrackingPageRecordPageQueryCommand trackingPageRecordPageQueryCommand) {
		Page<TrackingPageRecordDO> page = iTrackingPageRecordService.listPage(trackingPageRecordPageQueryCommand);
		return TrackingPageRecordAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 页面埋点记录 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<TrackingPageRecordVO> executeDetail(IdCommand detailCommand) {
		TrackingPageRecordDO byId = iTrackingPageRecordService.getById(detailCommand.getId());
		TrackingPageRecordVO trackingPageRecordVO = TrackingPageRecordAppStructMapping.instance.trackingPageRecordDOToTrackingPageRecordVO(byId);
		return SingleResponse.of(trackingPageRecordVO);
	}

	@Autowired
	public void setITrackingPageRecordService(ITrackingPageRecordService iTrackingPageRecordService) {
		this.iTrackingPageRecordService = iTrackingPageRecordService;
	}
}
