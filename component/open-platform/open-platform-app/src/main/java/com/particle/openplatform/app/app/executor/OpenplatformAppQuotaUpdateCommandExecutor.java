package com.particle.openplatform.app.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.app.structmapping.OpenplatformAppQuotaAppStructMapping;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppQuotaUpdateCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppQuotaVO;
import com.particle.openplatform.domain.app.OpenplatformAppQuota;
import com.particle.openplatform.domain.app.OpenplatformAppQuotaId;
import com.particle.openplatform.domain.app.gateway.OpenplatformAppQuotaGateway;
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
 * 开放平台应用额度 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformAppQuotaUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformAppQuotaGateway openplatformAppQuotaGateway;

	/**
	 * 执行 开放平台应用额度 更新指令
	 * @param openplatformAppQuotaUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformAppQuotaVO> execute(@Valid OpenplatformAppQuotaUpdateCommand openplatformAppQuotaUpdateCommand) {
		OpenplatformAppQuota openplatformAppQuota = createByOpenplatformAppQuotaUpdateCommand(openplatformAppQuotaUpdateCommand);
		openplatformAppQuota.setUpdateControl(openplatformAppQuotaUpdateCommand);
		boolean save = openplatformAppQuotaGateway.save(openplatformAppQuota);
		if (save) {
			return SingleResponse.of(OpenplatformAppQuotaAppStructMapping.instance.toOpenplatformAppQuotaVO(openplatformAppQuota));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台应用额度更新指令创建开放平台应用额度模型
	 * @param openplatformAppQuotaUpdateCommand
	 * @return
	 */
	private OpenplatformAppQuota createByOpenplatformAppQuotaUpdateCommand(OpenplatformAppQuotaUpdateCommand openplatformAppQuotaUpdateCommand){
		OpenplatformAppQuota openplatformAppQuota = OpenplatformAppQuota.create();
		OpenplatformAppQuotaUpdateCommandToOpenplatformAppQuotaMapping.instance.fillOpenplatformAppQuotaByOpenplatformAppQuotaUpdateCommand(openplatformAppQuota, openplatformAppQuotaUpdateCommand);
		return openplatformAppQuota;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface OpenplatformAppQuotaUpdateCommandToOpenplatformAppQuotaMapping{
		OpenplatformAppQuotaUpdateCommandToOpenplatformAppQuotaMapping instance = Mappers.getMapper(OpenplatformAppQuotaUpdateCommandToOpenplatformAppQuotaMapping.class );

		default OpenplatformAppQuotaId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformAppQuotaId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformAppQuota
		 * @param openplatformAppQuotaUpdateCommand
		 */
		void fillOpenplatformAppQuotaByOpenplatformAppQuotaUpdateCommand(@MappingTarget OpenplatformAppQuota openplatformAppQuota, OpenplatformAppQuotaUpdateCommand openplatformAppQuotaUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformAppQuotaGateway
	 */
	@Autowired
	public void setOpenplatformAppQuotaGateway(OpenplatformAppQuotaGateway openplatformAppQuotaGateway) {
		this.openplatformAppQuotaGateway = openplatformAppQuotaGateway;
	}
}
