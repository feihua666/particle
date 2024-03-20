package com.particle.openplatform.app.doc.executor;

import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocResponseCodeAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocResponseCodeUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocResponseCodeVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocResponseCode;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocResponseCodeId;
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
 * 开放接口文档响应码 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformDocApiDocResponseCodeUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocResponseCodeGateway openplatformDocApiDocResponseCodeGateway;

	private IOpenplatformDocApiDocService iOpenplatformDocApiDocService;
	/**
	 * 执行 开放接口文档响应码 更新指令
	 * @param openplatformDocApiDocResponseCodeUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocResponseCodeVO> execute(@Valid OpenplatformDocApiDocResponseCodeUpdateCommand openplatformDocApiDocResponseCodeUpdateCommand) {
		OpenplatformDocApiDocResponseCode openplatformDocApiDocResponseCode = createByOpenplatformDocApiDocResponseCodeUpdateCommand(openplatformDocApiDocResponseCodeUpdateCommand);

		OpenplatformDocApiDocDO openplatformDocApiDocDO = iOpenplatformDocApiDocService.getById(openplatformDocApiDocResponseCode.getOpenplatformDocApiDocId());
		openplatformDocApiDocResponseCode.changeOpenplatformDocApiId(openplatformDocApiDocDO.getOpenplatformDocApiId());


		openplatformDocApiDocResponseCode.setUpdateControl(openplatformDocApiDocResponseCodeUpdateCommand);
		boolean save = openplatformDocApiDocResponseCodeGateway.save(openplatformDocApiDocResponseCode);
		if (save) {
			return SingleResponse.of(OpenplatformDocApiDocResponseCodeAppStructMapping.instance.toOpenplatformDocApiDocResponseCodeVO(openplatformDocApiDocResponseCode));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口文档响应码更新指令创建开放接口文档响应码模型
	 * @param openplatformDocApiDocResponseCodeUpdateCommand
	 * @return
	 */
	private OpenplatformDocApiDocResponseCode createByOpenplatformDocApiDocResponseCodeUpdateCommand(OpenplatformDocApiDocResponseCodeUpdateCommand openplatformDocApiDocResponseCodeUpdateCommand){
		OpenplatformDocApiDocResponseCode openplatformDocApiDocResponseCode = OpenplatformDocApiDocResponseCode.create();
		OpenplatformDocApiDocResponseCodeUpdateCommandToOpenplatformDocApiDocResponseCodeMapping.instance.fillOpenplatformDocApiDocResponseCodeByOpenplatformDocApiDocResponseCodeUpdateCommand(openplatformDocApiDocResponseCode, openplatformDocApiDocResponseCodeUpdateCommand);
		return openplatformDocApiDocResponseCode;
	}

	@Mapper
	interface OpenplatformDocApiDocResponseCodeUpdateCommandToOpenplatformDocApiDocResponseCodeMapping{
		OpenplatformDocApiDocResponseCodeUpdateCommandToOpenplatformDocApiDocResponseCodeMapping instance = Mappers.getMapper(OpenplatformDocApiDocResponseCodeUpdateCommandToOpenplatformDocApiDocResponseCodeMapping.class );

		default OpenplatformDocApiDocResponseCodeId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformDocApiDocResponseCodeId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocApiDocResponseCode
		 * @param openplatformDocApiDocResponseCodeUpdateCommand
		 */
		void fillOpenplatformDocApiDocResponseCodeByOpenplatformDocApiDocResponseCodeUpdateCommand(@MappingTarget OpenplatformDocApiDocResponseCode openplatformDocApiDocResponseCode, OpenplatformDocApiDocResponseCodeUpdateCommand openplatformDocApiDocResponseCodeUpdateCommand);
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
