package com.particle.agi.app.rag.executor;

import com.particle.agi.app.rag.structmapping.AgiVectorStoreRawDocumentAppStructMapping;
import com.particle.agi.client.rag.dto.command.AgiVectorStoreRawDocumentUpdateCommand;
import com.particle.agi.client.rag.dto.data.AgiVectorStoreRawDocumentVO;
import com.particle.agi.domain.rag.AgiVectorStoreRawDocument;
import com.particle.agi.domain.rag.AgiVectorStoreRawDocumentId;
import com.particle.agi.domain.rag.gateway.AgiVectorStoreRawDocumentGateway;
import com.particle.agi.infrastructure.rag.dos.AgiVectorStoreRawDocumentSegmentDO;
import com.particle.agi.infrastructure.rag.service.IAgiVectorStoreRawDocumentSegmentService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

import java.util.List;

/**
 * <p>
 * 知识存储原始文档 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class AgiVectorStoreRawDocumentUpdateCommandExecutor  extends AbstractBaseExecutor {

	private AgiVectorStoreRawDocumentGateway agiVectorStoreRawDocumentGateway;
	private IAgiVectorStoreRawDocumentSegmentService iAgiVectorStoreRawDocumentSegmentService;


	/**
	 * 执行 知识存储原始文档 更新指令
	 * @param agiVectorStoreRawDocumentUpdateCommand
	 * @return
	 */
	public SingleResponse<AgiVectorStoreRawDocumentVO> execute(@Valid AgiVectorStoreRawDocumentUpdateCommand agiVectorStoreRawDocumentUpdateCommand) {
		AgiVectorStoreRawDocument agiVectorStoreRawDocument = createByAgiVectorStoreRawDocumentUpdateCommand(agiVectorStoreRawDocumentUpdateCommand);
		agiVectorStoreRawDocument.setUpdateControl(agiVectorStoreRawDocumentUpdateCommand);
		boolean save = agiVectorStoreRawDocumentGateway.save(agiVectorStoreRawDocument);
		if (save) {
			return SingleResponse.of(AgiVectorStoreRawDocumentAppStructMapping.instance.toAgiVectorStoreRawDocumentVO(agiVectorStoreRawDocument));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 更新嵌入状态，根据片段状态更新
	 * @param idCommand
	 * @return
	 */
	public Response updateEmbedStatus(IdCommand idCommand) {
		long noneEmbeddedSegmentCount = iAgiVectorStoreRawDocumentSegmentService.countNoneEmbeddedByAgiVectorStoreRawDocumentId(idCommand.getId());

		AgiVectorStoreRawDocument agiVectorStoreRawDocument = agiVectorStoreRawDocumentGateway.getById(AgiVectorStoreRawDocumentId.of(idCommand.getId()));
        if (noneEmbeddedSegmentCount > 0) {
			agiVectorStoreRawDocument.changeToNotEmbedded();
        }else {
			agiVectorStoreRawDocument.changeToEmbedded();
		}
		boolean save = agiVectorStoreRawDocumentGateway.save(agiVectorStoreRawDocument);
		if (save) {
			return Response.buildSuccess();
		}
		return Response.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据知识存储原始文档更新指令创建知识存储原始文档模型
	 * @param agiVectorStoreRawDocumentUpdateCommand
	 * @return
	 */
	private AgiVectorStoreRawDocument createByAgiVectorStoreRawDocumentUpdateCommand(AgiVectorStoreRawDocumentUpdateCommand agiVectorStoreRawDocumentUpdateCommand){
		AgiVectorStoreRawDocument agiVectorStoreRawDocument = AgiVectorStoreRawDocument.create();
		AgiVectorStoreRawDocumentUpdateCommandToAgiVectorStoreRawDocumentMapping.instance.fillAgiVectorStoreRawDocumentByAgiVectorStoreRawDocumentUpdateCommand(agiVectorStoreRawDocument, agiVectorStoreRawDocumentUpdateCommand);
		return agiVectorStoreRawDocument;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface AgiVectorStoreRawDocumentUpdateCommandToAgiVectorStoreRawDocumentMapping{
		AgiVectorStoreRawDocumentUpdateCommandToAgiVectorStoreRawDocumentMapping instance = Mappers.getMapper(AgiVectorStoreRawDocumentUpdateCommandToAgiVectorStoreRawDocumentMapping.class );

		default AgiVectorStoreRawDocumentId map(Long id){
			if (id == null) {
				return null;
			}
			return AgiVectorStoreRawDocumentId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param agiVectorStoreRawDocument
		 * @param agiVectorStoreRawDocumentUpdateCommand
		 */
		void fillAgiVectorStoreRawDocumentByAgiVectorStoreRawDocumentUpdateCommand(@MappingTarget AgiVectorStoreRawDocument agiVectorStoreRawDocument, AgiVectorStoreRawDocumentUpdateCommand agiVectorStoreRawDocumentUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param agiVectorStoreRawDocumentGateway
	 */
	@Autowired
	public void setAgiVectorStoreRawDocumentGateway(AgiVectorStoreRawDocumentGateway agiVectorStoreRawDocumentGateway) {
		this.agiVectorStoreRawDocumentGateway = agiVectorStoreRawDocumentGateway;
	}
	@Autowired
	public void setIAgiVectorStoreRawDocumentSegmentService(IAgiVectorStoreRawDocumentSegmentService iAgiVectorStoreRawDocumentSegmentService) {
		this.iAgiVectorStoreRawDocumentSegmentService = iAgiVectorStoreRawDocumentSegmentService;
	}
}
