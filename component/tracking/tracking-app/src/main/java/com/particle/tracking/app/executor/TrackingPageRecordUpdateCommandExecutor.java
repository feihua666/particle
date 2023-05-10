package com.particle.tracking.app.executor;

import com.particle.tracking.app.structmapping.TrackingPageRecordAppStructMapping;
import com.particle.tracking.client.dto.command.TrackingPageRecordUpdateCommand;
import com.particle.tracking.client.dto.data.TrackingPageRecordVO;
import com.particle.tracking.domain.TrackingPageRecord;
import com.particle.tracking.domain.TrackingPageRecordId;
import com.particle.tracking.domain.gateway.TrackingPageRecordGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 页面埋点记录 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class TrackingPageRecordUpdateCommandExecutor  extends AbstractBaseExecutor {

	private TrackingPageRecordGateway trackingPageRecordGateway;

	/**
	 * 执行 页面埋点记录 更新指令
	 * @param trackingPageRecordUpdateCommand
	 * @return
	 */
	public SingleResponse<TrackingPageRecordVO> execute(@Valid TrackingPageRecordUpdateCommand trackingPageRecordUpdateCommand) {
		TrackingPageRecord trackingPageRecord = createByTrackingPageRecordUpdateCommand(trackingPageRecordUpdateCommand);
		trackingPageRecord.setUpdateControl(trackingPageRecordUpdateCommand);
		boolean save = trackingPageRecordGateway.save(trackingPageRecord);
		if (save) {
			return SingleResponse.of(TrackingPageRecordAppStructMapping.instance.toTrackingPageRecordVO(trackingPageRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param trackingPageRecordUpdateCommand
	 * @return
	 */
	private TrackingPageRecord createByTrackingPageRecordUpdateCommand(TrackingPageRecordUpdateCommand trackingPageRecordUpdateCommand){
		TrackingPageRecord trackingPageRecord = TrackingPageRecord.create();
		TrackingPageRecordUpdateCommandToTrackingPageRecordMapping.instance.fillTrackingPageRecordByTrackingPageRecordUpdateCommand(trackingPageRecord, trackingPageRecordUpdateCommand);
		return trackingPageRecord;
	}

	@Mapper
	interface TrackingPageRecordUpdateCommandToTrackingPageRecordMapping{
		TrackingPageRecordUpdateCommandToTrackingPageRecordMapping instance = Mappers.getMapper(TrackingPageRecordUpdateCommandToTrackingPageRecordMapping.class );

		default TrackingPageRecordId map(Long id){
			if (id == null) {
				return null;
			}
			return TrackingPageRecordId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param trackingPageRecord
		 * @param trackingPageRecordUpdateCommand
		 */
		void fillTrackingPageRecordByTrackingPageRecordUpdateCommand(@MappingTarget TrackingPageRecord trackingPageRecord, TrackingPageRecordUpdateCommand trackingPageRecordUpdateCommand);
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
