package com.particle.agi.client.rag.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.agi.client.rag.dto.command.AgiVectorStoreRawDocumentSegmentCreateCommand;
import com.particle.agi.client.rag.dto.command.AgiVectorStoreRawDocumentSegmentUpdateCommand;
import com.particle.agi.client.rag.dto.data.AgiVectorStoreRawDocumentSegmentVO;
/**
 * <p>
 * 知识存储原始文档片段 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:55:48
 */
public interface IAgiVectorStoreRawDocumentSegmentApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param agiVectorStoreRawDocumentSegmentCreateCommand
	 * @return
	 */
	SingleResponse<AgiVectorStoreRawDocumentSegmentVO> create(AgiVectorStoreRawDocumentSegmentCreateCommand agiVectorStoreRawDocumentSegmentCreateCommand);

	/**
	 * 嵌入文档片段
	 * @param idCommand
	 * @return
	 */
	Response embedding(IdCommand idCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<AgiVectorStoreRawDocumentSegmentVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param agiVectorStoreRawDocumentSegmentUpdateCommand
	 * @return
	 */
	SingleResponse<AgiVectorStoreRawDocumentSegmentVO> update(AgiVectorStoreRawDocumentSegmentUpdateCommand agiVectorStoreRawDocumentSegmentUpdateCommand);
}
