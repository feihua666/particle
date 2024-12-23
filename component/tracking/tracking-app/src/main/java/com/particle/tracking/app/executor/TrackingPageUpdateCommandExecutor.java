package com.particle.tracking.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.tracking.app.structmapping.TrackingPageAppStructMapping;
import com.particle.tracking.client.dto.command.TrackingPageUpdateCommand;
import com.particle.tracking.client.dto.data.TrackingPageVO;
import com.particle.tracking.domain.TrackingPage;
import com.particle.tracking.domain.TrackingPageId;
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
 * 埋点页面 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class TrackingPageUpdateCommandExecutor  extends AbstractBaseExecutor {

	private TrackingPageGateway trackingPageGateway;

	/**
	 * 执行 埋点页面 更新指令
	 * @param trackingPageUpdateCommand
	 * @return
	 */
	public SingleResponse<TrackingPageVO> execute(@Valid TrackingPageUpdateCommand trackingPageUpdateCommand) {
		TrackingPage trackingPage = createByTrackingPageUpdateCommand(trackingPageUpdateCommand);
		trackingPage.setUpdateControl(trackingPageUpdateCommand);
		boolean save = trackingPageGateway.save(trackingPage);
		if (save) {
			return SingleResponse.of(TrackingPageAppStructMapping.instance.toTrackingPageVO(trackingPage));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据埋点页面更新指令创建埋点页面模型
	 * @param trackingPageUpdateCommand
	 * @return
	 */
	private TrackingPage createByTrackingPageUpdateCommand(TrackingPageUpdateCommand trackingPageUpdateCommand){
		TrackingPage trackingPage = TrackingPage.create();
		TrackingPageUpdateCommandToTrackingPageMapping.instance.fillTrackingPageByTrackingPageUpdateCommand(trackingPage, trackingPageUpdateCommand);
		return trackingPage;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface TrackingPageUpdateCommandToTrackingPageMapping{
		TrackingPageUpdateCommandToTrackingPageMapping instance = Mappers.getMapper(TrackingPageUpdateCommandToTrackingPageMapping.class );

		default TrackingPageId map(Long id){
			if (id == null) {
				return null;
			}
			return TrackingPageId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param trackingPage
		 * @param trackingPageUpdateCommand
		 */
		void fillTrackingPageByTrackingPageUpdateCommand(@MappingTarget TrackingPage trackingPage, TrackingPageUpdateCommand trackingPageUpdateCommand);
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
