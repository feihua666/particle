package com.particle.agi.app.rag.executor;

import com.particle.agi.infrastructure.rag.dos.AgiVectorStoreRawDocumentSegmentDO;
import com.particle.agi.infrastructure.rag.service.IAgiVectorStoreRawDocumentSegmentService;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.agi.app.rag.structmapping.AgiVectorStoreRawDocumentAppStructMapping;
import com.particle.agi.client.rag.dto.data.AgiVectorStoreRawDocumentVO;
import com.particle.agi.domain.rag.AgiVectorStoreRawDocument;
import com.particle.agi.domain.rag.AgiVectorStoreRawDocumentId;
import com.particle.agi.domain.rag.gateway.AgiVectorStoreRawDocumentGateway;
import com.particle.agi.infrastructure.rag.service.IAgiVectorStoreRawDocumentService;
import com.particle.agi.infrastructure.rag.dos.AgiVectorStoreRawDocumentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

import java.util.List;

/**
 * <p>
 * 知识存储原始文档 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:54:59
 */
@Component
@Validated
public class AgiVectorStoreRawDocumentDeleteCommandExecutor  extends AbstractBaseExecutor {

	private AgiVectorStoreRawDocumentGateway agiVectorStoreRawDocumentGateway;
	private IAgiVectorStoreRawDocumentService iAgiVectorStoreRawDocumentService;
	private AgiVectorStoreRawDocumentSegmentDeleteCommandExecutor agiVectorStoreRawDocumentSegmentDeleteCommandExecutor;
	private IAgiVectorStoreRawDocumentSegmentService iAgiVectorStoreRawDocumentSegmentService;

	/**
	 * 执行 知识存储原始文档 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<AgiVectorStoreRawDocumentVO> execute(@Valid IdCommand deleteCommand) {
		AgiVectorStoreRawDocumentId agiVectorStoreRawDocumentId = AgiVectorStoreRawDocumentId.of(deleteCommand.getId());
		AgiVectorStoreRawDocument byId = agiVectorStoreRawDocumentGateway.getById(agiVectorStoreRawDocumentId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = agiVectorStoreRawDocumentGateway.delete(agiVectorStoreRawDocumentId,deleteCommand);
		if (delete) {
			// 删除后将片段一并删除
			List<AgiVectorStoreRawDocumentSegmentDO> byAgiVectorStoreRawDocumentId = iAgiVectorStoreRawDocumentSegmentService.getByAgiVectorStoreRawDocumentId(deleteCommand.getId());
			for (AgiVectorStoreRawDocumentSegmentDO agiVectorStoreRawDocumentSegmentDO : byAgiVectorStoreRawDocumentId) {
				IdCommand idCommand = new IdCommand();
				idCommand.setId(agiVectorStoreRawDocumentSegmentDO.getId());
				agiVectorStoreRawDocumentSegmentDeleteCommandExecutor.execute(idCommand);
			}
			return SingleResponse.of(AgiVectorStoreRawDocumentAppStructMapping.instance.toAgiVectorStoreRawDocumentVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
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
	public void setAgiVectorStoreRawDocumentSegmentDeleteCommandExecutor(AgiVectorStoreRawDocumentSegmentDeleteCommandExecutor agiVectorStoreRawDocumentSegmentDeleteCommandExecutor) {
		this.agiVectorStoreRawDocumentSegmentDeleteCommandExecutor = agiVectorStoreRawDocumentSegmentDeleteCommandExecutor;
	}
	@Autowired
	public void setIAgiVectorStoreRawDocumentSegmentService(IAgiVectorStoreRawDocumentSegmentService iAgiVectorStoreRawDocumentSegmentService) {
		this.iAgiVectorStoreRawDocumentSegmentService = iAgiVectorStoreRawDocumentSegmentService;
	}
}
