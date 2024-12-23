package com.particle.openplatform.app.doc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocExampleCodeAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocExampleCodeCreateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocExampleCodeVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocExampleCode;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocExampleCodeGateway;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocDO;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocService;
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
 * 开放接口文档示例代码 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:11
 */
@Component
@Validated
public class OpenplatformDocApiDocExampleCodeCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocExampleCodeGateway openplatformDocApiDocExampleCodeGateway;
	private IOpenplatformDocApiDocService iOpenplatformDocApiDocService;
	/**
	 * 执行开放接口文档示例代码添加指令
	 * @param openplatformDocApiDocExampleCodeCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocExampleCodeVO> execute(@Valid OpenplatformDocApiDocExampleCodeCreateCommand openplatformDocApiDocExampleCodeCreateCommand) {
		OpenplatformDocApiDocExampleCode openplatformDocApiDocExampleCode = createByOpenplatformDocApiDocExampleCodeCreateCommand(openplatformDocApiDocExampleCodeCreateCommand);

		OpenplatformDocApiDocDO openplatformDocApiDocDO = iOpenplatformDocApiDocService.getById(openplatformDocApiDocExampleCode.getOpenplatformDocApiDocId());
		openplatformDocApiDocExampleCode.changeOpenplatformDocApiId(openplatformDocApiDocDO.getOpenplatformDocApiId());

		openplatformDocApiDocExampleCode.setAddControl(openplatformDocApiDocExampleCodeCreateCommand);
		boolean save = openplatformDocApiDocExampleCodeGateway.save(openplatformDocApiDocExampleCode);
		if (save) {
			return SingleResponse.of(OpenplatformDocApiDocExampleCodeAppStructMapping.instance.toOpenplatformDocApiDocExampleCodeVO(openplatformDocApiDocExampleCode));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口文档示例代码创建指令创建开放接口文档示例代码模型
	 * @param openplatformDocApiDocExampleCodeCreateCommand
	 * @return
	 */
	private OpenplatformDocApiDocExampleCode createByOpenplatformDocApiDocExampleCodeCreateCommand(OpenplatformDocApiDocExampleCodeCreateCommand openplatformDocApiDocExampleCodeCreateCommand){
		OpenplatformDocApiDocExampleCode openplatformDocApiDocExampleCode = OpenplatformDocApiDocExampleCode.create();
		OpenplatformDocApiDocExampleCodeCreateCommandToOpenplatformDocApiDocExampleCodeMapping.instance.fillOpenplatformDocApiDocExampleCodeByOpenplatformDocApiDocExampleCodeCreateCommand(openplatformDocApiDocExampleCode, openplatformDocApiDocExampleCodeCreateCommand);
		return openplatformDocApiDocExampleCode;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  OpenplatformDocApiDocExampleCodeCreateCommandToOpenplatformDocApiDocExampleCodeMapping{
		OpenplatformDocApiDocExampleCodeCreateCommandToOpenplatformDocApiDocExampleCodeMapping instance = Mappers.getMapper( OpenplatformDocApiDocExampleCodeCreateCommandToOpenplatformDocApiDocExampleCodeMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocApiDocExampleCode
		 * @param openplatformDocApiDocExampleCodeCreateCommand
		 */
		void fillOpenplatformDocApiDocExampleCodeByOpenplatformDocApiDocExampleCodeCreateCommand(@MappingTarget OpenplatformDocApiDocExampleCode openplatformDocApiDocExampleCode, OpenplatformDocApiDocExampleCodeCreateCommand openplatformDocApiDocExampleCodeCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiDocExampleCodeGateway
	 */
	@Autowired
	public void setOpenplatformDocApiDocExampleCodeGateway(OpenplatformDocApiDocExampleCodeGateway openplatformDocApiDocExampleCodeGateway) {
		this.openplatformDocApiDocExampleCodeGateway = openplatformDocApiDocExampleCodeGateway;
	}
	@Autowired
	public void setiOpenplatformDocApiDocService(IOpenplatformDocApiDocService iOpenplatformDocApiDocService) {
		this.iOpenplatformDocApiDocService = iOpenplatformDocApiDocService;
	}
}
