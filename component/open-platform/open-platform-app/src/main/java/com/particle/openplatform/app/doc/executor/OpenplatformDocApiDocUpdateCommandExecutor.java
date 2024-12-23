package com.particle.openplatform.app.doc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDoc;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocGateway;
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
 * 开放接口文档 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformDocApiDocUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocGateway openplatformDocApiDocGateway;

	/**
	 * 执行 开放接口文档 更新指令
	 * @param openplatformDocApiDocUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocVO> execute(@Valid OpenplatformDocApiDocUpdateCommand openplatformDocApiDocUpdateCommand) {
		OpenplatformDocApiDoc openplatformDocApiDoc = createByOpenplatformDocApiDocUpdateCommand(openplatformDocApiDocUpdateCommand);
		openplatformDocApiDoc.setUpdateControl(openplatformDocApiDocUpdateCommand);
		boolean save = openplatformDocApiDocGateway.save(openplatformDocApiDoc);
		if (save) {
			return SingleResponse.of(OpenplatformDocApiDocAppStructMapping.instance.toOpenplatformDocApiDocVO(openplatformDocApiDoc));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口文档更新指令创建开放接口文档模型
	 * @param openplatformDocApiDocUpdateCommand
	 * @return
	 */
	private OpenplatformDocApiDoc createByOpenplatformDocApiDocUpdateCommand(OpenplatformDocApiDocUpdateCommand openplatformDocApiDocUpdateCommand){
		OpenplatformDocApiDoc openplatformDocApiDoc = OpenplatformDocApiDoc.create();
		OpenplatformDocApiDocUpdateCommandToOpenplatformDocApiDocMapping.instance.fillOpenplatformDocApiDocByOpenplatformDocApiDocUpdateCommand(openplatformDocApiDoc, openplatformDocApiDocUpdateCommand);
		return openplatformDocApiDoc;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface OpenplatformDocApiDocUpdateCommandToOpenplatformDocApiDocMapping{
		OpenplatformDocApiDocUpdateCommandToOpenplatformDocApiDocMapping instance = Mappers.getMapper(OpenplatformDocApiDocUpdateCommandToOpenplatformDocApiDocMapping.class );

		default OpenplatformDocApiDocId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformDocApiDocId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocApiDoc
		 * @param openplatformDocApiDocUpdateCommand
		 */
		void fillOpenplatformDocApiDocByOpenplatformDocApiDocUpdateCommand(@MappingTarget OpenplatformDocApiDoc openplatformDocApiDoc, OpenplatformDocApiDocUpdateCommand openplatformDocApiDocUpdateCommand);
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
