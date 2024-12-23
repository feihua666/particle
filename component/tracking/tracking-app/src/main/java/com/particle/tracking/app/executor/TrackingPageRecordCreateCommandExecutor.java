package com.particle.tracking.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.tracking.client.dto.command.TrackingPageRecordCreateCommand;
import com.particle.tracking.domain.TrackingPageRecord;
import com.particle.tracking.domain.gateway.TrackingPageRecordGateway;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
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
public class TrackingPageRecordCreateCommandExecutor  extends AbstractBaseExecutor {

	private TrackingPageRecordGateway trackingPageRecordGateway;

	/**
	 * 执行页面埋点记录添加指令
	 * @param trackingPageRecordCreateCommand
	 * @return
	 */
	public SingleResponse<AbstractBaseIdVO> execute(@Valid TrackingPageRecordCreateCommand trackingPageRecordCreateCommand) {
		TrackingPageRecord trackingPageRecord = createByTrackingPageRecordCreateCommand(trackingPageRecordCreateCommand);
		trackingPageRecord.setAddControl(trackingPageRecordCreateCommand);
		boolean save = trackingPageRecordGateway.save(trackingPageRecord);
		if (save) {
			AbstractBaseIdVO abstractBaseIdVO = new AbstractBaseIdVO();
			abstractBaseIdVO.setId(trackingPageRecord.getId().getId());
			trackingPageRecord.setVersion(trackingPageRecord.getVersion());
			return SingleResponse.of(abstractBaseIdVO);
			//return SingleResponse.of(TrackingPageRecordAppStructMapping.instance.toTrackingPageRecordVO(trackingPageRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据页面埋点记录创建指令创建页面埋点记录模型
	 * @param trackingPageRecordCreateCommand
	 * @return
	 */
	private TrackingPageRecord createByTrackingPageRecordCreateCommand(TrackingPageRecordCreateCommand trackingPageRecordCreateCommand){
		TrackingPageRecord trackingPageRecord = TrackingPageRecord.create();
		TrackingPageRecordCreateCommandToTrackingPageRecordMapping.instance.fillTrackingPageRecordByTrackingPageRecordCreateCommand(trackingPageRecord, trackingPageRecordCreateCommand);
		return trackingPageRecord;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  TrackingPageRecordCreateCommandToTrackingPageRecordMapping{
		TrackingPageRecordCreateCommandToTrackingPageRecordMapping instance = Mappers.getMapper( TrackingPageRecordCreateCommandToTrackingPageRecordMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param trackingPageRecord
		 * @param trackingPageRecordCreateCommand
		 */
		void fillTrackingPageRecordByTrackingPageRecordCreateCommand(@MappingTarget TrackingPageRecord trackingPageRecord, TrackingPageRecordCreateCommand trackingPageRecordCreateCommand);
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
