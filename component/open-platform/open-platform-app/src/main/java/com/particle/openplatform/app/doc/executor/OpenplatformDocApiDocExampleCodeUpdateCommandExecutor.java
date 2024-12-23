package com.particle.openplatform.app.doc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocExampleCodeAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocExampleCodeUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocExampleCodeVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocExampleCode;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocExampleCodeId;
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
 * 开放接口文档示例代码 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformDocApiDocExampleCodeUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocExampleCodeGateway openplatformDocApiDocExampleCodeGateway;
	private IOpenplatformDocApiDocService iOpenplatformDocApiDocService;
	/**
	 * 执行 开放接口文档示例代码 更新指令
	 * @param openplatformDocApiDocExampleCodeUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocExampleCodeVO> execute(@Valid OpenplatformDocApiDocExampleCodeUpdateCommand openplatformDocApiDocExampleCodeUpdateCommand) {
		OpenplatformDocApiDocExampleCode openplatformDocApiDocExampleCode = createByOpenplatformDocApiDocExampleCodeUpdateCommand(openplatformDocApiDocExampleCodeUpdateCommand);

		OpenplatformDocApiDocDO openplatformDocApiDocDO = iOpenplatformDocApiDocService.getById(openplatformDocApiDocExampleCode.getOpenplatformDocApiDocId());
		openplatformDocApiDocExampleCode.changeOpenplatformDocApiId(openplatformDocApiDocDO.getOpenplatformDocApiId());

		openplatformDocApiDocExampleCode.setUpdateControl(openplatformDocApiDocExampleCodeUpdateCommand);
		boolean save = openplatformDocApiDocExampleCodeGateway.save(openplatformDocApiDocExampleCode);
		if (save) {
			return SingleResponse.of(OpenplatformDocApiDocExampleCodeAppStructMapping.instance.toOpenplatformDocApiDocExampleCodeVO(openplatformDocApiDocExampleCode));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口文档示例代码更新指令创建开放接口文档示例代码模型
	 * @param openplatformDocApiDocExampleCodeUpdateCommand
	 * @return
	 */
	private OpenplatformDocApiDocExampleCode createByOpenplatformDocApiDocExampleCodeUpdateCommand(OpenplatformDocApiDocExampleCodeUpdateCommand openplatformDocApiDocExampleCodeUpdateCommand){
		OpenplatformDocApiDocExampleCode openplatformDocApiDocExampleCode = OpenplatformDocApiDocExampleCode.create();
		OpenplatformDocApiDocExampleCodeUpdateCommandToOpenplatformDocApiDocExampleCodeMapping.instance.fillOpenplatformDocApiDocExampleCodeByOpenplatformDocApiDocExampleCodeUpdateCommand(openplatformDocApiDocExampleCode, openplatformDocApiDocExampleCodeUpdateCommand);
		return openplatformDocApiDocExampleCode;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface OpenplatformDocApiDocExampleCodeUpdateCommandToOpenplatformDocApiDocExampleCodeMapping{
		OpenplatformDocApiDocExampleCodeUpdateCommandToOpenplatformDocApiDocExampleCodeMapping instance = Mappers.getMapper(OpenplatformDocApiDocExampleCodeUpdateCommandToOpenplatformDocApiDocExampleCodeMapping.class );

		default OpenplatformDocApiDocExampleCodeId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformDocApiDocExampleCodeId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocApiDocExampleCode
		 * @param openplatformDocApiDocExampleCodeUpdateCommand
		 */
		void fillOpenplatformDocApiDocExampleCodeByOpenplatformDocApiDocExampleCodeUpdateCommand(@MappingTarget OpenplatformDocApiDocExampleCode openplatformDocApiDocExampleCode, OpenplatformDocApiDocExampleCodeUpdateCommand openplatformDocApiDocExampleCodeUpdateCommand);
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
