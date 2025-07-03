package com.particle.cms.app.executor;

import com.particle.cms.app.structmapping.CmsChannelAppStructMapping;
import com.particle.cms.client.dto.command.CmsChannelCreateCommand;
import com.particle.cms.client.dto.data.CmsChannelVO;
import com.particle.cms.domain.CmsChannel;
import com.particle.cms.domain.gateway.CmsChannelGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 栏目 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
@Component
@Validated
public class CmsChannelCreateCommandExecutor  extends AbstractBaseExecutor {

	private CmsChannelGateway cmsChannelGateway;

	/**
	 * 执行栏目添加指令
	 * @param cmsChannelCreateCommand
	 * @return
	 */
	public SingleResponse<CmsChannelVO> execute(@Valid CmsChannelCreateCommand cmsChannelCreateCommand) {
		CmsChannel cmsChannel = createByCmsChannelCreateCommand(cmsChannelCreateCommand);
		cmsChannel.initForAdd();
		cmsChannel.setAddControl(cmsChannelCreateCommand);
		boolean save = cmsChannelGateway.save(cmsChannel);
		if (save) {
			return SingleResponse.of(CmsChannelAppStructMapping.instance.toCmsChannelVO(cmsChannel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据栏目创建指令创建栏目模型
	 * @param cmsChannelCreateCommand
	 * @return
	 */
	private CmsChannel createByCmsChannelCreateCommand(CmsChannelCreateCommand cmsChannelCreateCommand){
		CmsChannel cmsChannel = CmsChannel.create();
		CmsChannelCreateCommandToCmsChannelMapping.instance.fillCmsChannelByCmsChannelCreateCommand(cmsChannel, cmsChannelCreateCommand);
		return cmsChannel;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CmsChannelCreateCommandToCmsChannelMapping{
		CmsChannelCreateCommandToCmsChannelMapping instance = Mappers.getMapper( CmsChannelCreateCommandToCmsChannelMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param cmsChannel
		 * @param cmsChannelCreateCommand
		 */
		void fillCmsChannelByCmsChannelCreateCommand(@MappingTarget CmsChannel cmsChannel, CmsChannelCreateCommand cmsChannelCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param cmsChannelGateway
	 */
	@Autowired
	public void setCmsChannelGateway(CmsChannelGateway cmsChannelGateway) {
		this.cmsChannelGateway = cmsChannelGateway;
	}
}
