package com.particle.openplatform.app.doc.executor;

import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDirRelAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDirRelUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDirRelVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDirRel;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDirRelId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDirRelGateway;
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
 * 开放接口文档接口与目录关系 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformDocApiDirRelUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDirRelGateway openplatformDocApiDirRelGateway;

	/**
	 * 执行 开放接口文档接口与目录关系 更新指令
	 * @param openplatformDocApiDirRelUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDirRelVO> execute(@Valid OpenplatformDocApiDirRelUpdateCommand openplatformDocApiDirRelUpdateCommand) {
		OpenplatformDocApiDirRel openplatformDocApiDirRel = createByOpenplatformDocApiDirRelUpdateCommand(openplatformDocApiDirRelUpdateCommand);
		openplatformDocApiDirRel.setUpdateControl(openplatformDocApiDirRelUpdateCommand);
		boolean save = openplatformDocApiDirRelGateway.save(openplatformDocApiDirRel);
		if (save) {
			return SingleResponse.of(OpenplatformDocApiDirRelAppStructMapping.instance.toOpenplatformDocApiDirRelVO(openplatformDocApiDirRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口文档接口与目录关系更新指令创建开放接口文档接口与目录关系模型
	 * @param openplatformDocApiDirRelUpdateCommand
	 * @return
	 */
	private OpenplatformDocApiDirRel createByOpenplatformDocApiDirRelUpdateCommand(OpenplatformDocApiDirRelUpdateCommand openplatformDocApiDirRelUpdateCommand){
		OpenplatformDocApiDirRel openplatformDocApiDirRel = OpenplatformDocApiDirRel.create();
		OpenplatformDocApiDirRelUpdateCommandToOpenplatformDocApiDirRelMapping.instance.fillOpenplatformDocApiDirRelByOpenplatformDocApiDirRelUpdateCommand(openplatformDocApiDirRel, openplatformDocApiDirRelUpdateCommand);
		return openplatformDocApiDirRel;
	}

	@Mapper
	interface OpenplatformDocApiDirRelUpdateCommandToOpenplatformDocApiDirRelMapping{
		OpenplatformDocApiDirRelUpdateCommandToOpenplatformDocApiDirRelMapping instance = Mappers.getMapper(OpenplatformDocApiDirRelUpdateCommandToOpenplatformDocApiDirRelMapping.class );

		default OpenplatformDocApiDirRelId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformDocApiDirRelId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocApiDirRel
		 * @param openplatformDocApiDirRelUpdateCommand
		 */
		void fillOpenplatformDocApiDirRelByOpenplatformDocApiDirRelUpdateCommand(@MappingTarget OpenplatformDocApiDirRel openplatformDocApiDirRel, OpenplatformDocApiDirRelUpdateCommand openplatformDocApiDirRelUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiDirRelGateway
	 */
	@Autowired
	public void setOpenplatformDocApiDirRelGateway(OpenplatformDocApiDirRelGateway openplatformDocApiDirRelGateway) {
		this.openplatformDocApiDirRelGateway = openplatformDocApiDirRelGateway;
	}
}
