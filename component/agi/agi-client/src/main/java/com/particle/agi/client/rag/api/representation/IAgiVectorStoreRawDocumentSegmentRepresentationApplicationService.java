package com.particle.agi.client.rag.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.agi.client.rag.dto.command.representation.AgiVectorStoreRawDocumentSegmentPageQueryCommand;
import com.particle.agi.client.rag.dto.command.representation.AgiVectorStoreRawDocumentSegmentQueryListCommand;
import com.particle.agi.client.rag.dto.data.AgiVectorStoreRawDocumentSegmentVO;

/**
 * <p>
 * 知识存储原始文档片段 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IAgiVectorStoreRawDocumentSegmentRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<AgiVectorStoreRawDocumentSegmentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<AgiVectorStoreRawDocumentSegmentVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param agiVectorStoreRawDocumentSegmentQueryListCommand
	 * @return
	 */
	MultiResponse<AgiVectorStoreRawDocumentSegmentVO> queryList(AgiVectorStoreRawDocumentSegmentQueryListCommand agiVectorStoreRawDocumentSegmentQueryListCommand);

	/**
	 * 分页查询
	 * @param agiVectorStoreRawDocumentSegmentPageQueryCommand
	 * @return
	 */
	PageResponse<AgiVectorStoreRawDocumentSegmentVO> pageQuery(AgiVectorStoreRawDocumentSegmentPageQueryCommand agiVectorStoreRawDocumentSegmentPageQueryCommand);

}
