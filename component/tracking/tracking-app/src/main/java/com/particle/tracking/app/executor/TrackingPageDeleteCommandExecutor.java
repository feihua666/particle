package com.particle.tracking.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.tracking.app.structmapping.TrackingPageAppStructMapping;
import com.particle.tracking.client.dto.data.TrackingPageVO;
import com.particle.tracking.domain.TrackingPage;
import com.particle.tracking.domain.TrackingPageId;
import com.particle.tracking.domain.gateway.TrackingPageGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 埋点页面 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
@Component
@Validated
public class TrackingPageDeleteCommandExecutor  extends AbstractBaseExecutor {

	private TrackingPageGateway trackingPageGateway;

	/**
	 * 执行 埋点页面 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<TrackingPageVO> execute(@Valid IdCommand deleteCommand) {
		TrackingPageId trackingPageId = TrackingPageId.of(deleteCommand.getId());
		TrackingPage byId = trackingPageGateway.getById(trackingPageId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = trackingPageGateway.delete(trackingPageId,deleteCommand);
		if (delete) {
			return SingleResponse.of(TrackingPageAppStructMapping.instance.toTrackingPageVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param trackingPageGateway
	 */
	@Autowired
	public void setTrackingPageGateway(TrackingPageGateway trackingPageGateway) {
		this.trackingPageGateway = trackingPageGateway;
	}
}
