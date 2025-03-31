package com.particle.agi.app.rag.executor;

import com.particle.agi.domain.gateway.AgiEmbeddingGateway;
import com.particle.agi.domain.rag.AgiVectorStoreRawDocumentSegment;
import com.particle.agi.domain.rag.AgiVectorStoreRawDocumentSegmentId;
import com.particle.agi.domain.rag.gateway.AgiVectorStoreRawDocumentSegmentGateway;
import com.particle.agi.domain.values.AgiDocument;
import com.particle.agi.infrastructure.rag.service.IAgiVectorStoreRawDocumentSegmentService;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 知识存储原始文档片段 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:55:48
 */
@Component
@Validated
public class AgiVectorStoreRawDocumentSegmentCommandExecutor  extends AbstractBaseExecutor {

	private AgiVectorStoreRawDocumentSegmentGateway agiVectorStoreRawDocumentSegmentGateway;
	private IAgiVectorStoreRawDocumentSegmentService iAgiVectorStoreRawDocumentSegmentService;
	private AgiEmbeddingGateway agiEmbeddingGateway;
	private AgiVectorStoreRawDocumentUpdateCommandExecutor agiVectorStoreRawDocumentUpdateCommandExecutor;

	/**
	 * 执行嵌入操作，将文档嵌入到向量存储中
	 * @param idCommand
	 * @return
	 */
	public Response embedding(@Valid IdCommand idCommand) {
		AgiVectorStoreRawDocumentSegment agiVectorStoreRawDocumentSegment = agiVectorStoreRawDocumentSegmentGateway.getById(AgiVectorStoreRawDocumentSegmentId.of(idCommand.getId()));
		AgiDocument agiDocument = agiVectorStoreRawDocumentSegment.toAgiDocument();
		String id = agiDocument.getId();
		// 嵌入之前，先尝试删除已存在的数据
		if (id != null) {
			agiEmbeddingGateway.deleteEmbeddingDataById(id);
		}
		agiEmbeddingGateway.embedding(agiDocument);
		// 修改为已嵌入
		agiVectorStoreRawDocumentSegment.changeToEmbedded();
		agiVectorStoreRawDocumentSegmentGateway.save(agiVectorStoreRawDocumentSegment);

		// 修改对应文档的状态
		agiVectorStoreRawDocumentUpdateCommandExecutor.updateEmbedStatus(IdCommand.create(agiVectorStoreRawDocumentSegment.getAgiVectorStoreRawDocumentId()));
		return Response.buildSuccess();
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
	public void setIAgiVectorStoreRawDocumentSegmentService(IAgiVectorStoreRawDocumentSegmentService iAgiVectorStoreRawDocumentSegmentService) {
		this.iAgiVectorStoreRawDocumentSegmentService = iAgiVectorStoreRawDocumentSegmentService;
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
