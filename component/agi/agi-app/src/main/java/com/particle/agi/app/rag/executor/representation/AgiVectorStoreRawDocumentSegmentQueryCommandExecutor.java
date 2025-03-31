package com.particle.agi.app.rag.executor.representation;

import com.particle.agi.app.rag.structmapping.AgiVectorStoreRawDocumentSegmentAppStructMapping;
import com.particle.agi.client.rag.dto.command.representation.AgiVectorStoreRawDocumentSegmentQueryListCommand;
import com.particle.agi.client.rag.dto.data.AgiVectorStoreRawDocumentSegmentVO;
import com.particle.agi.infrastructure.rag.dos.AgiVectorStoreRawDocumentSegmentDO;
import com.particle.agi.infrastructure.rag.service.IAgiVectorStoreRawDocumentSegmentService;
import com.particle.agi.client.rag.dto.command.representation.AgiVectorStoreRawDocumentSegmentPageQueryCommand;
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
 * 知识存储原始文档片段 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-01-08 16:55:48
 */
@Component
@Validated
public class AgiVectorStoreRawDocumentSegmentQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IAgiVectorStoreRawDocumentSegmentService iAgiVectorStoreRawDocumentSegmentService;

	/**
	 * 执行 知识存储原始文档片段 列表查询指令
	 * @param agiVectorStoreRawDocumentSegmentQueryListCommand
	 * @return
	 */
	public MultiResponse<AgiVectorStoreRawDocumentSegmentVO> execute(@Valid AgiVectorStoreRawDocumentSegmentQueryListCommand agiVectorStoreRawDocumentSegmentQueryListCommand) {
		List<AgiVectorStoreRawDocumentSegmentDO> agiVectorStoreRawDocumentSegmentDO = iAgiVectorStoreRawDocumentSegmentService.list(agiVectorStoreRawDocumentSegmentQueryListCommand);
		List<AgiVectorStoreRawDocumentSegmentVO> agiVectorStoreRawDocumentSegmentVOs = AgiVectorStoreRawDocumentSegmentAppStructMapping.instance.agiVectorStoreRawDocumentSegmentDOsToAgiVectorStoreRawDocumentSegmentVOs(agiVectorStoreRawDocumentSegmentDO);
		return MultiResponse.of(agiVectorStoreRawDocumentSegmentVOs);
	}
	/**
	 * 执行 知识存储原始文档片段 分页查询指令
	 * @param agiVectorStoreRawDocumentSegmentPageQueryCommand
	 * @return
	 */
	public PageResponse<AgiVectorStoreRawDocumentSegmentVO> execute(@Valid AgiVectorStoreRawDocumentSegmentPageQueryCommand agiVectorStoreRawDocumentSegmentPageQueryCommand) {
		Page<AgiVectorStoreRawDocumentSegmentDO> page = iAgiVectorStoreRawDocumentSegmentService.listPage(agiVectorStoreRawDocumentSegmentPageQueryCommand);
		return AgiVectorStoreRawDocumentSegmentAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 知识存储原始文档片段 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<AgiVectorStoreRawDocumentSegmentVO> executeDetail(IdCommand detailCommand) {
		AgiVectorStoreRawDocumentSegmentDO byId = iAgiVectorStoreRawDocumentSegmentService.getById(detailCommand.getId());
		AgiVectorStoreRawDocumentSegmentVO agiVectorStoreRawDocumentSegmentVO = AgiVectorStoreRawDocumentSegmentAppStructMapping.instance.agiVectorStoreRawDocumentSegmentDOToAgiVectorStoreRawDocumentSegmentVO(byId);
		return SingleResponse.of(agiVectorStoreRawDocumentSegmentVO);
	}
	/**
	 * 执行 知识存储原始文档片段 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<AgiVectorStoreRawDocumentSegmentVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		AgiVectorStoreRawDocumentSegmentDO byId = iAgiVectorStoreRawDocumentSegmentService.getById(detailForUpdateCommand.getId());
		AgiVectorStoreRawDocumentSegmentVO agiVectorStoreRawDocumentSegmentVO = AgiVectorStoreRawDocumentSegmentAppStructMapping.instance.agiVectorStoreRawDocumentSegmentDOToAgiVectorStoreRawDocumentSegmentVO(byId);
		return SingleResponse.of(agiVectorStoreRawDocumentSegmentVO);
	}


	@Autowired
	public void setIAgiVectorStoreRawDocumentSegmentService(IAgiVectorStoreRawDocumentSegmentService iAgiVectorStoreRawDocumentSegmentService) {
		this.iAgiVectorStoreRawDocumentSegmentService = iAgiVectorStoreRawDocumentSegmentService;
	}
}
