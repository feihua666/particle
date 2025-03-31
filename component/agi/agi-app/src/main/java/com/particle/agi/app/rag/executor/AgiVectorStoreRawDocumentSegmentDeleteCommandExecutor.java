package com.particle.agi.app.rag.executor;

import com.particle.agi.domain.gateway.AgiEmbeddingGateway;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.agi.app.rag.structmapping.AgiVectorStoreRawDocumentSegmentAppStructMapping;
import com.particle.agi.client.rag.dto.data.AgiVectorStoreRawDocumentSegmentVO;
import com.particle.agi.domain.rag.AgiVectorStoreRawDocumentSegment;
import com.particle.agi.domain.rag.AgiVectorStoreRawDocumentSegmentId;
import com.particle.agi.domain.rag.gateway.AgiVectorStoreRawDocumentSegmentGateway;
import com.particle.agi.infrastructure.rag.service.IAgiVectorStoreRawDocumentSegmentService;
import com.particle.agi.infrastructure.rag.dos.AgiVectorStoreRawDocumentSegmentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 知识存储原始文档片段 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:55:48
 */
@Component
@Validated
public class AgiVectorStoreRawDocumentSegmentDeleteCommandExecutor  extends AbstractBaseExecutor {

	private AgiVectorStoreRawDocumentSegmentGateway agiVectorStoreRawDocumentSegmentGateway;
	private IAgiVectorStoreRawDocumentSegmentService iAgiVectorStoreRawDocumentSegmentService;
	private AgiEmbeddingGateway agiEmbeddingGateway;

	/**
	 * 执行 知识存储原始文档片段 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<AgiVectorStoreRawDocumentSegmentVO> execute(@Valid IdCommand deleteCommand) {
		AgiVectorStoreRawDocumentSegmentId agiVectorStoreRawDocumentSegmentId = AgiVectorStoreRawDocumentSegmentId.of(deleteCommand.getId());
		AgiVectorStoreRawDocumentSegment byId = agiVectorStoreRawDocumentSegmentGateway.getById(agiVectorStoreRawDocumentSegmentId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = agiVectorStoreRawDocumentSegmentGateway.delete(agiVectorStoreRawDocumentSegmentId,deleteCommand);
		if (delete) {
			// 删除成功后，将向量数据也删除
			agiEmbeddingGateway.deleteEmbeddingDataById(byId.getId().getId().toString());
			return SingleResponse.of(AgiVectorStoreRawDocumentSegmentAppStructMapping.instance.toAgiVectorStoreRawDocumentSegmentVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
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
}
