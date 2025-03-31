package com.particle.agi.app.rag.executor;

import com.particle.agi.app.rag.structmapping.AgiVectorStoreRawDocumentSegmentAppStructMapping;
import com.particle.agi.client.rag.dto.command.AgiVectorStoreRawDocumentSegmentUpdateCommand;
import com.particle.agi.client.rag.dto.data.AgiVectorStoreRawDocumentSegmentVO;
import com.particle.agi.domain.gateway.AgiEmbeddingGateway;
import com.particle.agi.domain.rag.AgiVectorStoreRawDocumentSegment;
import com.particle.agi.domain.rag.AgiVectorStoreRawDocumentSegmentId;
import com.particle.agi.domain.rag.gateway.AgiVectorStoreRawDocumentSegmentGateway;
import com.particle.common.client.dto.command.IdCommand;
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

/**
 * <p>
 * 知识存储原始文档片段 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class AgiVectorStoreRawDocumentSegmentUpdateCommandExecutor  extends AbstractBaseExecutor {

	private AgiVectorStoreRawDocumentSegmentGateway agiVectorStoreRawDocumentSegmentGateway;
	private AgiEmbeddingGateway agiEmbeddingGateway;
	private AgiVectorStoreRawDocumentUpdateCommandExecutor agiVectorStoreRawDocumentUpdateCommandExecutor;

	/**
	 * 执行 知识存储原始文档片段 更新指令
	 * @param agiVectorStoreRawDocumentSegmentUpdateCommand
	 * @return
	 */
	public SingleResponse<AgiVectorStoreRawDocumentSegmentVO> execute(@Valid AgiVectorStoreRawDocumentSegmentUpdateCommand agiVectorStoreRawDocumentSegmentUpdateCommand) {
		AgiVectorStoreRawDocumentSegment agiVectorStoreRawDocumentSegment = createByAgiVectorStoreRawDocumentSegmentUpdateCommand(agiVectorStoreRawDocumentSegmentUpdateCommand);
		agiVectorStoreRawDocumentSegment.changeToNotEmbedded();
		agiVectorStoreRawDocumentSegment.setUpdateControl(agiVectorStoreRawDocumentSegmentUpdateCommand);
		boolean save = agiVectorStoreRawDocumentSegmentGateway.save(agiVectorStoreRawDocumentSegment);
		if (save) {
			// 修改成功后，删除已嵌入的向量数据
			agiEmbeddingGateway.deleteEmbeddingDataById(agiVectorStoreRawDocumentSegment.getId().getId().toString());
			// 将文档嵌入状态更新
			agiVectorStoreRawDocumentUpdateCommandExecutor.updateEmbedStatus(IdCommand.create(agiVectorStoreRawDocumentSegment.getAgiVectorStoreRawDocumentId()));
			return SingleResponse.of(AgiVectorStoreRawDocumentSegmentAppStructMapping.instance.toAgiVectorStoreRawDocumentSegmentVO(agiVectorStoreRawDocumentSegment));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据知识存储原始文档片段更新指令创建知识存储原始文档片段模型
	 * @param agiVectorStoreRawDocumentSegmentUpdateCommand
	 * @return
	 */
	private AgiVectorStoreRawDocumentSegment createByAgiVectorStoreRawDocumentSegmentUpdateCommand(AgiVectorStoreRawDocumentSegmentUpdateCommand agiVectorStoreRawDocumentSegmentUpdateCommand){
		AgiVectorStoreRawDocumentSegment agiVectorStoreRawDocumentSegment = AgiVectorStoreRawDocumentSegment.create();
		AgiVectorStoreRawDocumentSegmentUpdateCommandToAgiVectorStoreRawDocumentSegmentMapping.instance.fillAgiVectorStoreRawDocumentSegmentByAgiVectorStoreRawDocumentSegmentUpdateCommand(agiVectorStoreRawDocumentSegment, agiVectorStoreRawDocumentSegmentUpdateCommand);
		return agiVectorStoreRawDocumentSegment;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface AgiVectorStoreRawDocumentSegmentUpdateCommandToAgiVectorStoreRawDocumentSegmentMapping{
		AgiVectorStoreRawDocumentSegmentUpdateCommandToAgiVectorStoreRawDocumentSegmentMapping instance = Mappers.getMapper(AgiVectorStoreRawDocumentSegmentUpdateCommandToAgiVectorStoreRawDocumentSegmentMapping.class );

		default AgiVectorStoreRawDocumentSegmentId map(Long id){
			if (id == null) {
				return null;
			}
			return AgiVectorStoreRawDocumentSegmentId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param agiVectorStoreRawDocumentSegment
		 * @param agiVectorStoreRawDocumentSegmentUpdateCommand
		 */
		void fillAgiVectorStoreRawDocumentSegmentByAgiVectorStoreRawDocumentSegmentUpdateCommand(@MappingTarget AgiVectorStoreRawDocumentSegment agiVectorStoreRawDocumentSegment, AgiVectorStoreRawDocumentSegmentUpdateCommand agiVectorStoreRawDocumentSegmentUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param agiVectorStoreRawDocumentSegmentGateway
	 */
	@Autowired
	public void setAgiVectorStoreRawDocumentSegmentGateway(AgiVectorStoreRawDocumentSegmentGateway agiVectorStoreRawDocumentSegmentGateway) {
		this.agiVectorStoreRawDocumentSegmentGateway = agiVectorStoreRawDocumentSegmentGateway;
	}
	@Autowired
	public void setAgiEmbeddingGateway(AgiEmbeddingGateway agiEmbeddingGateway) {
		this.agiEmbeddingGateway = agiEmbeddingGateway;
	}
	@Autowired
	public void setAgiVectorStoreRawDocumentUpdateCommandExecutor(AgiVectorStoreRawDocumentUpdateCommandExecutor agiVectorStoreRawDocumentUpdateCommandExecutor) {
		this.agiVectorStoreRawDocumentUpdateCommandExecutor = agiVectorStoreRawDocumentUpdateCommandExecutor;
	}
}
