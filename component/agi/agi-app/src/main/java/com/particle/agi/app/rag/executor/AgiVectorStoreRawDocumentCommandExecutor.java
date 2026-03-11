package com.particle.agi.app.rag.executor;

import com.particle.agi.domain.rag.gateway.AgiVectorStoreRawDocumentGateway;
import com.particle.agi.infrastructure.rag.dos.AgiVectorStoreRawDocumentSegmentDO;
import com.particle.agi.infrastructure.rag.service.IAgiVectorStoreRawDocumentSegmentService;
import com.particle.agi.infrastructure.rag.service.IAgiVectorStoreRawDocumentService;

import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

import java.util.List;

/**
 * <p>
 * 知识存储原始文档 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:54:59
 */
@Component
@Validated
public class AgiVectorStoreRawDocumentCommandExecutor  extends AbstractBaseExecutor {

	private AgiVectorStoreRawDocumentGateway agiVectorStoreRawDocumentGateway;
	private IAgiVectorStoreRawDocumentService iAgiVectorStoreRawDocumentService;
	private AgiVectorStoreRawDocumentSegmentCommandExecutor agiVectorStoreRawDocumentSegmentCommandExecutor;
	private IAgiVectorStoreRawDocumentSegmentService iAgiVectorStoreRawDocumentSegmentService;

	/**
	 * 嵌入文档下所有片段，已经嵌入的片段会被忽略
	 * @param commonIdCommand
	 * @return
	 */
	public Response embedding(@Valid CommonIdCommand commonIdCommand) {

		return embedding(commonIdCommand, true);
	}


	/**
	 * 重新嵌入所有片段，已经嵌入的片段会重新嵌入
	 * @param commonIdCommand
	 * @return
	 */
	public Response reEmbedding(@Valid CommonIdCommand commonIdCommand) {
		return embedding(commonIdCommand, false);
	}

	/**
	 * 嵌入
	 * @param commonIdCommand
	 * @param isIgnoreEmbeddedSegment 是否忽略已经嵌入的片段，true=忽略，false=不忽略
	 * @return
	 */
	private Response embedding(@Valid CommonIdCommand commonIdCommand, Boolean isIgnoreEmbeddedSegment) {
		List<AgiVectorStoreRawDocumentSegmentDO> byAgiVectorStoreRawDocumentId = iAgiVectorStoreRawDocumentSegmentService.getByAgiVectorStoreRawDocumentId(commonIdCommand.getId());
		for (AgiVectorStoreRawDocumentSegmentDO agiVectorStoreRawDocumentSegmentDO : byAgiVectorStoreRawDocumentId) {
			Boolean isEmbedded = agiVectorStoreRawDocumentSegmentDO.getIsEmbedded();
			if (isIgnoreEmbeddedSegment && isEmbedded) {
				continue;
			}
			CommonIdCommand segmentCommonIdCommand = CommonIdCommand.create(agiVectorStoreRawDocumentSegmentDO.getId());
			agiVectorStoreRawDocumentSegmentCommandExecutor.embedding(segmentCommonIdCommand);
		}
		return Response.buildSuccess();
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
	public void setIAgiVectorStoreRawDocumentService(IAgiVectorStoreRawDocumentService iAgiVectorStoreRawDocumentService) {
		this.iAgiVectorStoreRawDocumentService = iAgiVectorStoreRawDocumentService;
	}
	@Autowired
	public void setAgiVectorStoreRawDocumentSegmentCommandExecutor(AgiVectorStoreRawDocumentSegmentCommandExecutor agiVectorStoreRawDocumentSegmentCommandExecutor) {
		this.agiVectorStoreRawDocumentSegmentCommandExecutor = agiVectorStoreRawDocumentSegmentCommandExecutor;
	}
	@Autowired
	public void setIAgiVectorStoreRawDocumentSegmentService(IAgiVectorStoreRawDocumentSegmentService iAgiVectorStoreRawDocumentSegmentService) {
		this.iAgiVectorStoreRawDocumentSegmentService = iAgiVectorStoreRawDocumentSegmentService;
	}
}
