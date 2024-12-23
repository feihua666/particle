package com.particle.openplatform.app.doc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocDirNameAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocDirNameCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocDirNameVO;
import com.particle.openplatform.domain.doc.OpenplatformDocDirName;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocDirNameGateway;
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
 * 开放接口目录名称 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:53:48
 */
@Component
@Validated
public class OpenplatformDocDirNameCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocDirNameGateway openplatformDocDirNameGateway;

	/**
	 * 执行开放接口目录名称添加指令
	 * @param openplatformDocDirNameCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocDirNameVO> execute(@Valid OpenplatformDocDirNameCreateCommand openplatformDocDirNameCreateCommand) {
		OpenplatformDocDirName openplatformDocDirName = createByOpenplatformDocDirNameCreateCommand(openplatformDocDirNameCreateCommand);
		openplatformDocDirName.setAddControl(openplatformDocDirNameCreateCommand);
		boolean save = openplatformDocDirNameGateway.save(openplatformDocDirName);
		if (save) {
			return SingleResponse.of(OpenplatformDocDirNameAppStructMapping.instance.toOpenplatformDocDirNameVO(openplatformDocDirName));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口目录名称创建指令创建开放接口目录名称模型
	 * @param openplatformDocDirNameCreateCommand
	 * @return
	 */
	private OpenplatformDocDirName createByOpenplatformDocDirNameCreateCommand(OpenplatformDocDirNameCreateCommand openplatformDocDirNameCreateCommand){
		OpenplatformDocDirName openplatformDocDirName = OpenplatformDocDirName.create();
		OpenplatformDocDirNameCreateCommandToOpenplatformDocDirNameMapping.instance.fillOpenplatformDocDirNameByOpenplatformDocDirNameCreateCommand(openplatformDocDirName, openplatformDocDirNameCreateCommand);
		return openplatformDocDirName;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  OpenplatformDocDirNameCreateCommandToOpenplatformDocDirNameMapping{
		OpenplatformDocDirNameCreateCommandToOpenplatformDocDirNameMapping instance = Mappers.getMapper( OpenplatformDocDirNameCreateCommandToOpenplatformDocDirNameMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocDirName
		 * @param openplatformDocDirNameCreateCommand
		 */
		void fillOpenplatformDocDirNameByOpenplatformDocDirNameCreateCommand(@MappingTarget OpenplatformDocDirName openplatformDocDirName, OpenplatformDocDirNameCreateCommand openplatformDocDirNameCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocDirNameGateway
	 */
	@Autowired
	public void setOpenplatformDocDirNameGateway(OpenplatformDocDirNameGateway openplatformDocDirNameGateway) {
		this.openplatformDocDirNameGateway = openplatformDocDirNameGateway;
	}
}
