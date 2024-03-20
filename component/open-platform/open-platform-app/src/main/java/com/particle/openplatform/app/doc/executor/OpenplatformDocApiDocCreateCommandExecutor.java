package com.particle.openplatform.app.doc.executor;

import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDoc;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocGateway;
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
 * 开放接口文档 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:37
 */
@Component
@Validated
public class OpenplatformDocApiDocCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocGateway openplatformDocApiDocGateway;

	/**
	 * 执行开放接口文档添加指令
	 * @param openplatformDocApiDocCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocVO> execute(@Valid OpenplatformDocApiDocCreateCommand openplatformDocApiDocCreateCommand) {
		OpenplatformDocApiDoc openplatformDocApiDoc = createByOpenplatformDocApiDocCreateCommand(openplatformDocApiDocCreateCommand);
		openplatformDocApiDoc.setAddControl(openplatformDocApiDocCreateCommand);
		boolean save = openplatformDocApiDocGateway.save(openplatformDocApiDoc);
		if (save) {
			return SingleResponse.of(OpenplatformDocApiDocAppStructMapping.instance.toOpenplatformDocApiDocVO(openplatformDocApiDoc));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口文档创建指令创建开放接口文档模型
	 * @param openplatformDocApiDocCreateCommand
	 * @return
	 */
	private OpenplatformDocApiDoc createByOpenplatformDocApiDocCreateCommand(OpenplatformDocApiDocCreateCommand openplatformDocApiDocCreateCommand){
		OpenplatformDocApiDoc openplatformDocApiDoc = OpenplatformDocApiDoc.create();
		OpenplatformDocApiDocCreateCommandToOpenplatformDocApiDocMapping.instance.fillOpenplatformDocApiDocByOpenplatformDocApiDocCreateCommand(openplatformDocApiDoc, openplatformDocApiDocCreateCommand);
		return openplatformDocApiDoc;
	}

	@Mapper
	interface  OpenplatformDocApiDocCreateCommandToOpenplatformDocApiDocMapping{
		OpenplatformDocApiDocCreateCommandToOpenplatformDocApiDocMapping instance = Mappers.getMapper( OpenplatformDocApiDocCreateCommandToOpenplatformDocApiDocMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocApiDoc
		 * @param openplatformDocApiDocCreateCommand
		 */
		void fillOpenplatformDocApiDocByOpenplatformDocApiDocCreateCommand(@MappingTarget OpenplatformDocApiDoc openplatformDocApiDoc, OpenplatformDocApiDocCreateCommand openplatformDocApiDocCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiDocGateway
	 */
	@Autowired
	public void setOpenplatformDocApiDocGateway(OpenplatformDocApiDocGateway openplatformDocApiDocGateway) {
		this.openplatformDocApiDocGateway = openplatformDocApiDocGateway;
	}
}
