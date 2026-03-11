package com.particle.cms.app.executor;

import com.particle.cms.app.structmapping.CmsChannelAppStructMapping;
import com.particle.cms.client.dto.command.CmsChannelUpdateCommand;
import com.particle.cms.client.dto.data.CmsChannelVO;
import com.particle.cms.domain.CmsChannel;
import com.particle.cms.domain.CmsChannelId;
import com.particle.cms.domain.gateway.CmsChannelGateway;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.CommonPublicCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
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
 * 栏目 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CmsChannelUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CmsChannelGateway cmsChannelGateway;

	/**
	 * 执行 栏目 更新指令
	 * @param cmsChannelUpdateCommand
	 * @return
	 */
	public SingleResponse<CmsChannelVO> execute(@Valid CmsChannelUpdateCommand cmsChannelUpdateCommand) {
		CmsChannel cmsChannel = createByCmsChannelUpdateCommand(cmsChannelUpdateCommand);
		cmsChannel.setUpdateControl(cmsChannelUpdateCommand);
		boolean save = cmsChannelGateway.save(cmsChannel);
		if (save) {
			return SingleResponse.of(CmsChannelAppStructMapping.instance.toCmsChannelVO(cmsChannel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}
	/**
	 * 执行 栏目 发布指令
	 * @param cmsChannelPublicCommand
	 * @return
	 */
	public SingleResponse<CmsChannelVO> publish(@Valid CommonPublicCommand cmsChannelPublicCommand) {
		CmsChannel cmsChannel = CmsChannel.create(CmsChannelId.of(cmsChannelPublicCommand.getId()));
		if (cmsChannelPublicCommand.getIsPublic()) {
			cmsChannel.publish();
		}else {
			cmsChannel.unPublish();
		}

		boolean save = cmsChannelGateway.save(cmsChannel);
		if (save) {
			return SingleResponse.of(CmsChannelAppStructMapping.instance.toCmsChannelVO(cmsChannel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}
	/**
	 * 根据栏目更新指令创建栏目模型
	 * @param cmsChannelUpdateCommand
	 * @return
	 */
	private CmsChannel createByCmsChannelUpdateCommand(CmsChannelUpdateCommand cmsChannelUpdateCommand){
		CmsChannel cmsChannel = CmsChannel.create();
		CmsChannelUpdateCommandToCmsChannelMapping.instance.fillCmsChannelByCmsChannelUpdateCommand(cmsChannel, cmsChannelUpdateCommand);
		return cmsChannel;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CmsChannelUpdateCommandToCmsChannelMapping{
		CmsChannelUpdateCommandToCmsChannelMapping instance = Mappers.getMapper(CmsChannelUpdateCommandToCmsChannelMapping.class );

		default CmsChannelId map(Long id){
			if (id == null) {
				return null;
			}
			return CmsChannelId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param cmsChannel
		 * @param cmsChannelUpdateCommand
		 */
		void fillCmsChannelByCmsChannelUpdateCommand(@MappingTarget CmsChannel cmsChannel, CmsChannelUpdateCommand cmsChannelUpdateCommand);
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
