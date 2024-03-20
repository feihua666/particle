package com.particle.openplatform.app.doc.executor;

import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocResponseCodeAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocResponseCodeCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocResponseCodeVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocResponseCode;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocResponseCodeGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocDO;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocService;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放接口文档响应码 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-18 14:44:43
 */
@Component
@Validated
public class OpenplatformDocApiDocResponseCodeCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocResponseCodeGateway openplatformDocApiDocResponseCodeGateway;

	private IOpenplatformDocApiDocService iOpenplatformDocApiDocService;
	/**
	 * 执行开放接口文档响应码添加指令
	 * @param openplatformDocApiDocResponseCodeCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocResponseCodeVO> execute(@Valid OpenplatformDocApiDocResponseCodeCreateCommand openplatformDocApiDocResponseCodeCreateCommand) {
		OpenplatformDocApiDocResponseCode openplatformDocApiDocResponseCode = createByOpenplatformDocApiDocResponseCodeCreateCommand(openplatformDocApiDocResponseCodeCreateCommand);

		OpenplatformDocApiDocDO openplatformDocApiDocDO = iOpenplatformDocApiDocService.getById(openplatformDocApiDocResponseCode.getOpenplatformDocApiDocId());
		openplatformDocApiDocResponseCode.changeOpenplatformDocApiId(openplatformDocApiDocDO.getOpenplatformDocApiId());

		openplatformDocApiDocResponseCode.setAddControl(openplatformDocApiDocResponseCodeCreateCommand);
		boolean save = openplatformDocApiDocResponseCodeGateway.save(openplatformDocApiDocResponseCode);
		if (save) {
			return SingleResponse.of(OpenplatformDocApiDocResponseCodeAppStructMapping.instance.toOpenplatformDocApiDocResponseCodeVO(openplatformDocApiDocResponseCode));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口文档响应码创建指令创建开放接口文档响应码模型
	 * @param openplatformDocApiDocResponseCodeCreateCommand
	 * @return
	 */
	private OpenplatformDocApiDocResponseCode createByOpenplatformDocApiDocResponseCodeCreateCommand(OpenplatformDocApiDocResponseCodeCreateCommand openplatformDocApiDocResponseCodeCreateCommand){
		OpenplatformDocApiDocResponseCode openplatformDocApiDocResponseCode = OpenplatformDocApiDocResponseCode.create();
		OpenplatformDocApiDocResponseCodeCreateCommandToOpenplatformDocApiDocResponseCodeMapping.instance.fillOpenplatformDocApiDocResponseCodeByOpenplatformDocApiDocResponseCodeCreateCommand(openplatformDocApiDocResponseCode, openplatformDocApiDocResponseCodeCreateCommand);
		return openplatformDocApiDocResponseCode;
	}

	@Mapper
	interface  OpenplatformDocApiDocResponseCodeCreateCommandToOpenplatformDocApiDocResponseCodeMapping{
		OpenplatformDocApiDocResponseCodeCreateCommandToOpenplatformDocApiDocResponseCodeMapping instance = Mappers.getMapper( OpenplatformDocApiDocResponseCodeCreateCommandToOpenplatformDocApiDocResponseCodeMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocApiDocResponseCode
		 * @param openplatformDocApiDocResponseCodeCreateCommand
		 */
		void fillOpenplatformDocApiDocResponseCodeByOpenplatformDocApiDocResponseCodeCreateCommand(@MappingTarget OpenplatformDocApiDocResponseCode openplatformDocApiDocResponseCode, OpenplatformDocApiDocResponseCodeCreateCommand openplatformDocApiDocResponseCodeCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiDocResponseCodeGateway
	 */
	@Autowired
	public void setOpenplatformDocApiDocResponseCodeGateway(OpenplatformDocApiDocResponseCodeGateway openplatformDocApiDocResponseCodeGateway) {
		this.openplatformDocApiDocResponseCodeGateway = openplatformDocApiDocResponseCodeGateway;
	}
	@Autowired
	public void setiOpenplatformDocApiDocService(IOpenplatformDocApiDocService iOpenplatformDocApiDocService) {
		this.iOpenplatformDocApiDocService = iOpenplatformDocApiDocService;
	}
}
