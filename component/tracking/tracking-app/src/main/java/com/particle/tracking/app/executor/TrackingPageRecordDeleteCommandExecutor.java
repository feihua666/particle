package com.particle.tracking.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.tracking.app.structmapping.TrackingPageRecordAppStructMapping;
import com.particle.tracking.client.dto.data.TrackingPageRecordVO;
import com.particle.tracking.domain.TrackingPageRecord;
import com.particle.tracking.domain.TrackingPageRecordId;
import com.particle.tracking.domain.gateway.TrackingPageRecordGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 页面埋点记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
@Component
@Validated
public class TrackingPageRecordDeleteCommandExecutor  extends AbstractBaseExecutor {

	private TrackingPageRecordGateway trackingPageRecordGateway;

	/**
	 * 执行 页面埋点记录 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<TrackingPageRecordVO> execute(@Valid IdCommand deleteCommand) {
		TrackingPageRecordId trackingPageRecordId = TrackingPageRecordId.of(deleteCommand.getId());
		TrackingPageRecord byId = trackingPageRecordGateway.getById(trackingPageRecordId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = trackingPageRecordGateway.delete(trackingPageRecordId,deleteCommand);
		if (delete) {
			return SingleResponse.of(TrackingPageRecordAppStructMapping.instance.toTrackingPageRecordVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param trackingPageRecordGateway
	 */
	@Autowired
	public void setTrackingPageRecordGateway(TrackingPageRecordGateway trackingPageRecordGateway) {
		this.trackingPageRecordGateway = trackingPageRecordGateway;
	}
}
