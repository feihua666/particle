package com.particle.openplatform.app.doc.executor;

import com.particle.openplatform.app.doc.structmapping.OpenplatformDocDirNameAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocDirNameUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocDirNameVO;
import com.particle.openplatform.domain.doc.OpenplatformDocDirName;
import com.particle.openplatform.domain.doc.OpenplatformDocDirNameId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocDirNameGateway;
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
 * 开放接口目录名称 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformDocDirNameUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocDirNameGateway openplatformDocDirNameGateway;

	/**
	 * 执行 开放接口目录名称 更新指令
	 * @param openplatformDocDirNameUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocDirNameVO> execute(@Valid OpenplatformDocDirNameUpdateCommand openplatformDocDirNameUpdateCommand) {
		OpenplatformDocDirName openplatformDocDirName = createByOpenplatformDocDirNameUpdateCommand(openplatformDocDirNameUpdateCommand);
		openplatformDocDirName.setUpdateControl(openplatformDocDirNameUpdateCommand);
		boolean save = openplatformDocDirNameGateway.save(openplatformDocDirName);
		if (save) {
			return SingleResponse.of(OpenplatformDocDirNameAppStructMapping.instance.toOpenplatformDocDirNameVO(openplatformDocDirName));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口目录名称更新指令创建开放接口目录名称模型
	 * @param openplatformDocDirNameUpdateCommand
	 * @return
	 */
	private OpenplatformDocDirName createByOpenplatformDocDirNameUpdateCommand(OpenplatformDocDirNameUpdateCommand openplatformDocDirNameUpdateCommand){
		OpenplatformDocDirName openplatformDocDirName = OpenplatformDocDirName.create();
		OpenplatformDocDirNameUpdateCommandToOpenplatformDocDirNameMapping.instance.fillOpenplatformDocDirNameByOpenplatformDocDirNameUpdateCommand(openplatformDocDirName, openplatformDocDirNameUpdateCommand);
		return openplatformDocDirName;
	}

	@Mapper
	interface OpenplatformDocDirNameUpdateCommandToOpenplatformDocDirNameMapping{
		OpenplatformDocDirNameUpdateCommandToOpenplatformDocDirNameMapping instance = Mappers.getMapper(OpenplatformDocDirNameUpdateCommandToOpenplatformDocDirNameMapping.class );

		default OpenplatformDocDirNameId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformDocDirNameId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocDirName
		 * @param openplatformDocDirNameUpdateCommand
		 */
		void fillOpenplatformDocDirNameByOpenplatformDocDirNameUpdateCommand(@MappingTarget OpenplatformDocDirName openplatformDocDirName, OpenplatformDocDirNameUpdateCommand openplatformDocDirNameUpdateCommand);
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
