package com.particle.openplatform.app.doc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDirRelAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDirRelCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDirRelVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDirRel;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDirRelGateway;
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
 * 开放接口文档接口与目录关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:20
 */
@Component
@Validated
public class OpenplatformDocApiDirRelCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDirRelGateway openplatformDocApiDirRelGateway;

	/**
	 * 执行开放接口文档接口与目录关系添加指令
	 * @param openplatformDocApiDirRelCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDirRelVO> execute(@Valid OpenplatformDocApiDirRelCreateCommand openplatformDocApiDirRelCreateCommand) {
		OpenplatformDocApiDirRel openplatformDocApiDirRel = createByOpenplatformDocApiDirRelCreateCommand(openplatformDocApiDirRelCreateCommand);
		openplatformDocApiDirRel.setAddControl(openplatformDocApiDirRelCreateCommand);
		boolean save = openplatformDocApiDirRelGateway.save(openplatformDocApiDirRel);
		if (save) {
			return SingleResponse.of(OpenplatformDocApiDirRelAppStructMapping.instance.toOpenplatformDocApiDirRelVO(openplatformDocApiDirRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口文档接口与目录关系创建指令创建开放接口文档接口与目录关系模型
	 * @param openplatformDocApiDirRelCreateCommand
	 * @return
	 */
	private OpenplatformDocApiDirRel createByOpenplatformDocApiDirRelCreateCommand(OpenplatformDocApiDirRelCreateCommand openplatformDocApiDirRelCreateCommand){
		OpenplatformDocApiDirRel openplatformDocApiDirRel = OpenplatformDocApiDirRel.create();
		OpenplatformDocApiDirRelCreateCommandToOpenplatformDocApiDirRelMapping.instance.fillOpenplatformDocApiDirRelByOpenplatformDocApiDirRelCreateCommand(openplatformDocApiDirRel, openplatformDocApiDirRelCreateCommand);
		return openplatformDocApiDirRel;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  OpenplatformDocApiDirRelCreateCommandToOpenplatformDocApiDirRelMapping{
		OpenplatformDocApiDirRelCreateCommandToOpenplatformDocApiDirRelMapping instance = Mappers.getMapper( OpenplatformDocApiDirRelCreateCommandToOpenplatformDocApiDirRelMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocApiDirRel
		 * @param openplatformDocApiDirRelCreateCommand
		 */
		void fillOpenplatformDocApiDirRelByOpenplatformDocApiDirRelCreateCommand(@MappingTarget OpenplatformDocApiDirRel openplatformDocApiDirRel, OpenplatformDocApiDirRelCreateCommand openplatformDocApiDirRelCreateCommand);
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
