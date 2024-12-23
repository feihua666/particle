package com.particle.openplatform.app.doc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocDirAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocDirCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocDirVO;
import com.particle.openplatform.domain.doc.OpenplatformDocDir;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocDirGateway;
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
 * 开放接口目录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:55:42
 */
@Component
@Validated
public class OpenplatformDocDirCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocDirGateway openplatformDocDirGateway;

	/**
	 * 执行开放接口目录添加指令
	 * @param openplatformDocDirCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocDirVO> execute(@Valid OpenplatformDocDirCreateCommand openplatformDocDirCreateCommand) {
		OpenplatformDocDir openplatformDocDir = createByOpenplatformDocDirCreateCommand(openplatformDocDirCreateCommand);
		openplatformDocDir.setAddControl(openplatformDocDirCreateCommand);
		boolean save = openplatformDocDirGateway.save(openplatformDocDir);
		if (save) {
			return SingleResponse.of(OpenplatformDocDirAppStructMapping.instance.toOpenplatformDocDirVO(openplatformDocDir));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口目录创建指令创建开放接口目录模型
	 * @param openplatformDocDirCreateCommand
	 * @return
	 */
	private OpenplatformDocDir createByOpenplatformDocDirCreateCommand(OpenplatformDocDirCreateCommand openplatformDocDirCreateCommand){
		OpenplatformDocDir openplatformDocDir = OpenplatformDocDir.create();
		OpenplatformDocDirCreateCommandToOpenplatformDocDirMapping.instance.fillOpenplatformDocDirByOpenplatformDocDirCreateCommand(openplatformDocDir, openplatformDocDirCreateCommand);
		return openplatformDocDir;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  OpenplatformDocDirCreateCommandToOpenplatformDocDirMapping{
		OpenplatformDocDirCreateCommandToOpenplatformDocDirMapping instance = Mappers.getMapper( OpenplatformDocDirCreateCommandToOpenplatformDocDirMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocDir
		 * @param openplatformDocDirCreateCommand
		 */
		void fillOpenplatformDocDirByOpenplatformDocDirCreateCommand(@MappingTarget OpenplatformDocDir openplatformDocDir, OpenplatformDocDirCreateCommand openplatformDocDirCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocDirGateway
	 */
	@Autowired
	public void setOpenplatformDocDirGateway(OpenplatformDocDirGateway openplatformDocDirGateway) {
		this.openplatformDocDirGateway = openplatformDocDirGateway;
	}
}
