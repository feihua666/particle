package com.particle.tracking.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.tracking.app.structmapping.TrackingPageAppStructMapping;
import com.particle.tracking.client.dto.command.TrackingPageCreateCommand;
import com.particle.tracking.client.dto.data.TrackingPageVO;
import com.particle.tracking.domain.TrackingPage;
import com.particle.tracking.domain.gateway.TrackingPageGateway;
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
 * 埋点页面 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
@Component
@Validated
public class TrackingPageCreateCommandExecutor  extends AbstractBaseExecutor {

	private TrackingPageGateway trackingPageGateway;

	/**
	 * 执行埋点页面添加指令
	 * @param trackingPageCreateCommand
	 * @return
	 */
	public SingleResponse<TrackingPageVO> execute(@Valid TrackingPageCreateCommand trackingPageCreateCommand) {
		TrackingPage trackingPage = createByTrackingPageCreateCommand(trackingPageCreateCommand);
		trackingPage.setAddControl(trackingPageCreateCommand);
		boolean save = trackingPageGateway.save(trackingPage);
		if (save) {
			return SingleResponse.of(TrackingPageAppStructMapping.instance.toTrackingPageVO(trackingPage));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据埋点页面创建指令创建埋点页面模型
	 * @param trackingPageCreateCommand
	 * @return
	 */
	private TrackingPage createByTrackingPageCreateCommand(TrackingPageCreateCommand trackingPageCreateCommand){
		TrackingPage trackingPage = TrackingPage.create();
		TrackingPageCreateCommandToTrackingPageMapping.instance.fillTrackingPageByTrackingPageCreateCommand(trackingPage, trackingPageCreateCommand);
		return trackingPage;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  TrackingPageCreateCommandToTrackingPageMapping{
		TrackingPageCreateCommandToTrackingPageMapping instance = Mappers.getMapper( TrackingPageCreateCommandToTrackingPageMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param trackingPage
		 * @param trackingPageCreateCommand
		 */
		void fillTrackingPageByTrackingPageCreateCommand(@MappingTarget TrackingPage trackingPage, TrackingPageCreateCommand trackingPageCreateCommand);
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
