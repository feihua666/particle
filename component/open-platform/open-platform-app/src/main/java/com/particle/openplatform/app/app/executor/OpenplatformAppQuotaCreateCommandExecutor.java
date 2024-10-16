package com.particle.openplatform.app.app.executor;

import com.particle.openplatform.app.app.structmapping.OpenplatformAppQuotaAppStructMapping;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppQuotaCreateCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppQuotaVO;
import com.particle.openplatform.domain.app.OpenplatformAppQuota;
import com.particle.openplatform.domain.app.gateway.OpenplatformAppQuotaGateway;
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
 * 开放平台应用额度 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
@Component
@Validated
public class OpenplatformAppQuotaCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformAppQuotaGateway openplatformAppQuotaGateway;

	/**
	 * 执行开放平台应用额度添加指令
	 * @param openplatformAppQuotaCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformAppQuotaVO> execute(@Valid OpenplatformAppQuotaCreateCommand openplatformAppQuotaCreateCommand) {
		OpenplatformAppQuota openplatformAppQuota = createByOpenplatformAppQuotaCreateCommand(openplatformAppQuotaCreateCommand);
		openplatformAppQuota.setAddControl(openplatformAppQuotaCreateCommand);
		boolean save = openplatformAppQuotaGateway.save(openplatformAppQuota);
		if (save) {
			return SingleResponse.of(OpenplatformAppQuotaAppStructMapping.instance.toOpenplatformAppQuotaVO(openplatformAppQuota));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台应用额度创建指令创建开放平台应用额度模型
	 * @param openplatformAppQuotaCreateCommand
	 * @return
	 */
	private OpenplatformAppQuota createByOpenplatformAppQuotaCreateCommand(OpenplatformAppQuotaCreateCommand openplatformAppQuotaCreateCommand){
		OpenplatformAppQuota openplatformAppQuota = OpenplatformAppQuota.create();
		OpenplatformAppQuotaCreateCommandToOpenplatformAppQuotaMapping.instance.fillOpenplatformAppQuotaByOpenplatformAppQuotaCreateCommand(openplatformAppQuota, openplatformAppQuotaCreateCommand);
		return openplatformAppQuota;
	}

	@Mapper
	interface  OpenplatformAppQuotaCreateCommandToOpenplatformAppQuotaMapping{
		OpenplatformAppQuotaCreateCommandToOpenplatformAppQuotaMapping instance = Mappers.getMapper( OpenplatformAppQuotaCreateCommandToOpenplatformAppQuotaMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformAppQuota
		 * @param openplatformAppQuotaCreateCommand
		 */
		void fillOpenplatformAppQuotaByOpenplatformAppQuotaCreateCommand(@MappingTarget OpenplatformAppQuota openplatformAppQuota, OpenplatformAppQuotaCreateCommand openplatformAppQuotaCreateCommand);
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
