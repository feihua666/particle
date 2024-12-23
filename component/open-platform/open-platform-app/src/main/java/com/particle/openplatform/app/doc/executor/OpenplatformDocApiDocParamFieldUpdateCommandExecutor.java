package com.particle.openplatform.app.doc.executor;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocParamFieldAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocParamFieldUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocParamFieldVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocParamField;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocParamFieldId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocParamFieldGateway;
import com.particle.openplatform.domain.gateway.OpenplatformDictGateway;
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
 * 开放接口文档参数字段 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformDocApiDocParamFieldUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocParamFieldGateway openplatformDocApiDocParamFieldGateway;
	private IOpenplatformDocApiDocService iOpenplatformDocApiDocService;

	private OpenplatformDictGateway openplatformDictGateway;
	/**
	 * 执行 开放接口文档参数字段 更新指令
	 * @param openplatformDocApiDocParamFieldUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocParamFieldVO> execute(@Valid OpenplatformDocApiDocParamFieldUpdateCommand openplatformDocApiDocParamFieldUpdateCommand) {
		OpenplatformDocApiDocParamField openplatformDocApiDocParamField = createByOpenplatformDocApiDocParamFieldUpdateCommand(openplatformDocApiDocParamFieldUpdateCommand);

		OpenplatformDocApiDocDO openplatformDocApiDocDO = iOpenplatformDocApiDocService.getById(openplatformDocApiDocParamField.getOpenplatformDocApiDocId());
		openplatformDocApiDocParamField.changeOpenplatformDocApiId(openplatformDocApiDocDO.getOpenplatformDocApiId());

		Long dictGroupDictId = openplatformDocApiDocParamFieldUpdateCommand.getDictGroupDictId();
		String dictGroupDictCode = openplatformDocApiDocParamFieldUpdateCommand.getDictGroupDictCode();
		if (dictGroupDictId == null && StrUtil.isNotEmpty(dictGroupDictCode)) {
			Long idByCode = openplatformDictGateway.getIdByCode(dictGroupDictCode);
			if (idByCode != null) {
				openplatformDocApiDocParamField.changeDictGroupDictId(idByCode);
			}
		}

		openplatformDocApiDocParamField.setUpdateControl(openplatformDocApiDocParamFieldUpdateCommand);
		boolean save = openplatformDocApiDocParamFieldGateway.save(openplatformDocApiDocParamField);
		if (save) {
			return SingleResponse.of(OpenplatformDocApiDocParamFieldAppStructMapping.instance.toOpenplatformDocApiDocParamFieldVO(openplatformDocApiDocParamField));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口文档参数字段更新指令创建开放接口文档参数字段模型
	 * @param openplatformDocApiDocParamFieldUpdateCommand
	 * @return
	 */
	private OpenplatformDocApiDocParamField createByOpenplatformDocApiDocParamFieldUpdateCommand(OpenplatformDocApiDocParamFieldUpdateCommand openplatformDocApiDocParamFieldUpdateCommand){
		OpenplatformDocApiDocParamField openplatformDocApiDocParamField = OpenplatformDocApiDocParamField.create();
		OpenplatformDocApiDocParamFieldUpdateCommandToOpenplatformDocApiDocParamFieldMapping.instance.fillOpenplatformDocApiDocParamFieldByOpenplatformDocApiDocParamFieldUpdateCommand(openplatformDocApiDocParamField, openplatformDocApiDocParamFieldUpdateCommand);
		return openplatformDocApiDocParamField;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface OpenplatformDocApiDocParamFieldUpdateCommandToOpenplatformDocApiDocParamFieldMapping{
		OpenplatformDocApiDocParamFieldUpdateCommandToOpenplatformDocApiDocParamFieldMapping instance = Mappers.getMapper(OpenplatformDocApiDocParamFieldUpdateCommandToOpenplatformDocApiDocParamFieldMapping.class );

		default OpenplatformDocApiDocParamFieldId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformDocApiDocParamFieldId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocApiDocParamField
		 * @param openplatformDocApiDocParamFieldUpdateCommand
		 */
		void fillOpenplatformDocApiDocParamFieldByOpenplatformDocApiDocParamFieldUpdateCommand(@MappingTarget OpenplatformDocApiDocParamField openplatformDocApiDocParamField, OpenplatformDocApiDocParamFieldUpdateCommand openplatformDocApiDocParamFieldUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiDocParamFieldGateway
	 */
	@Autowired
	public void setOpenplatformDocApiDocParamFieldGateway(OpenplatformDocApiDocParamFieldGateway openplatformDocApiDocParamFieldGateway) {
		this.openplatformDocApiDocParamFieldGateway = openplatformDocApiDocParamFieldGateway;
	}
	@Autowired
	public void setiOpenplatformDocApiDocService(IOpenplatformDocApiDocService iOpenplatformDocApiDocService) {
		this.iOpenplatformDocApiDocService = iOpenplatformDocApiDocService;
	}
	@Autowired
	public void setOpenplatformDictGateway(OpenplatformDictGateway openplatformDictGateway) {
		this.openplatformDictGateway = openplatformDictGateway;
	}
}
