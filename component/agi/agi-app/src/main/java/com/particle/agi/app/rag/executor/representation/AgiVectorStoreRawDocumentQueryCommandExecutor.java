package com.particle.agi.app.rag.executor.representation;

import com.particle.agi.app.rag.structmapping.AgiVectorStoreRawDocumentAppStructMapping;
import com.particle.agi.client.rag.dto.command.representation.AgiVectorStoreRawDocumentQueryListCommand;
import com.particle.agi.client.rag.dto.data.AgiVectorStoreRawDocumentVO;
import com.particle.agi.infrastructure.rag.dos.AgiVectorStoreRawDocumentDO;
import com.particle.agi.infrastructure.rag.service.IAgiVectorStoreRawDocumentService;
import com.particle.agi.client.rag.dto.command.representation.AgiVectorStoreRawDocumentPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 知识存储原始文档 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-01-08 16:54:59
 */
@Component
@Validated
public class AgiVectorStoreRawDocumentQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IAgiVectorStoreRawDocumentService iAgiVectorStoreRawDocumentService;

	/**
	 * 执行 知识存储原始文档 列表查询指令
	 * @param agiVectorStoreRawDocumentQueryListCommand
	 * @return
	 */
	public MultiResponse<AgiVectorStoreRawDocumentVO> execute(@Valid AgiVectorStoreRawDocumentQueryListCommand agiVectorStoreRawDocumentQueryListCommand) {
		List<AgiVectorStoreRawDocumentDO> agiVectorStoreRawDocumentDO = iAgiVectorStoreRawDocumentService.list(agiVectorStoreRawDocumentQueryListCommand);
		List<AgiVectorStoreRawDocumentVO> agiVectorStoreRawDocumentVOs = AgiVectorStoreRawDocumentAppStructMapping.instance.agiVectorStoreRawDocumentDOsToAgiVectorStoreRawDocumentVOs(agiVectorStoreRawDocumentDO);
		return MultiResponse.of(agiVectorStoreRawDocumentVOs);
	}
	/**
	 * 执行 知识存储原始文档 分页查询指令
	 * @param agiVectorStoreRawDocumentPageQueryCommand
	 * @return
	 */
	public PageResponse<AgiVectorStoreRawDocumentVO> execute(@Valid AgiVectorStoreRawDocumentPageQueryCommand agiVectorStoreRawDocumentPageQueryCommand) {
		Page<AgiVectorStoreRawDocumentDO> page = iAgiVectorStoreRawDocumentService.listPage(agiVectorStoreRawDocumentPageQueryCommand);
		return AgiVectorStoreRawDocumentAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 知识存储原始文档 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<AgiVectorStoreRawDocumentVO> executeDetail(IdCommand detailCommand) {
		AgiVectorStoreRawDocumentDO byId = iAgiVectorStoreRawDocumentService.getById(detailCommand.getId());
		AgiVectorStoreRawDocumentVO agiVectorStoreRawDocumentVO = AgiVectorStoreRawDocumentAppStructMapping.instance.agiVectorStoreRawDocumentDOToAgiVectorStoreRawDocumentVO(byId);
		return SingleResponse.of(agiVectorStoreRawDocumentVO);
	}


	@Autowired
	public void setIAgiVectorStoreRawDocumentService(IAgiVectorStoreRawDocumentService iAgiVectorStoreRawDocumentService) {
		this.iAgiVectorStoreRawDocumentService = iAgiVectorStoreRawDocumentService;
	}
}
