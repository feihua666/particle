package com.particle.tracking.app.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tracking.app.structmapping.TrackingPageAppStructMapping;
import com.particle.tracking.client.dto.command.representation.TrackingPagePageQueryCommand;
import com.particle.tracking.client.dto.command.representation.TrackingPageQueryListCommand;
import com.particle.tracking.client.dto.data.TrackingPageVO;
import com.particle.tracking.infrastructure.dos.TrackingPageDO;
import com.particle.tracking.infrastructure.service.ITrackingPageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 埋点页面 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-05-10 11:39:06
 */
@Component
@Validated
public class TrackingPageQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ITrackingPageService iTrackingPageService;

	/**
	 * 执行 埋点页面 列表查询指令
	 * @param trackingPageQueryListCommand
	 * @return
	 */
	public MultiResponse<TrackingPageVO> execute(@Valid TrackingPageQueryListCommand trackingPageQueryListCommand) {
		List<TrackingPageDO> trackingPageDO = iTrackingPageService.list(trackingPageQueryListCommand);
		List<TrackingPageVO> trackingPageVOs = TrackingPageAppStructMapping.instance.trackingPageDOsToTrackingPageVOs(trackingPageDO);
		return MultiResponse.of(trackingPageVOs);
	}
	/**
	 * 执行 埋点页面 分页查询指令
	 * @param trackingPagePageQueryCommand
	 * @return
	 */
	public PageResponse<TrackingPageVO> execute(@Valid TrackingPagePageQueryCommand trackingPagePageQueryCommand) {
		Page<TrackingPageDO> page = iTrackingPageService.listPage(trackingPagePageQueryCommand);
		return TrackingPageAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 埋点页面 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<TrackingPageVO> executeDetail(IdCommand detailCommand) {
		TrackingPageDO byId = iTrackingPageService.getById(detailCommand.getId());
		TrackingPageVO trackingPageVO = TrackingPageAppStructMapping.instance.trackingPageDOToTrackingPageVO(byId);
		return SingleResponse.of(trackingPageVO);
	}
	/**
	 * 执行 埋点页面 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<TrackingPageVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		TrackingPageDO byId = iTrackingPageService.getById(detailForUpdateCommand.getId());
		TrackingPageVO trackingPageVO = TrackingPageAppStructMapping.instance.trackingPageDOToTrackingPageVO(byId);
		return SingleResponse.of(trackingPageVO);
	}

	@Autowired
	public void setITrackingPageService(ITrackingPageService iTrackingPageService) {
		this.iTrackingPageService = iTrackingPageService;
	}
}
