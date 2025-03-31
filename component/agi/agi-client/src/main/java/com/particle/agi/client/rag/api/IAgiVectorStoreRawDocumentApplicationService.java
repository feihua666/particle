package com.particle.agi.client.rag.api;

import com.particle.agi.client.rag.dto.command.AgiVectorStoreRawDocumentCreateCommand;
import com.particle.agi.client.rag.dto.data.AgiVectorStoreRawDocumentVO;
import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
/**
 * <p>
 * 知识存储原始文档 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:54:59
 */
public interface IAgiVectorStoreRawDocumentApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param agiVectorStoreRawDocumentCreateCommand
	 * @return
	 */
	SingleResponse<AgiVectorStoreRawDocumentVO> create(AgiVectorStoreRawDocumentCreateCommand agiVectorStoreRawDocumentCreateCommand);
	/**
	 * 嵌入文档片段,忽略已经嵌入的片段
	 * @param idCommand
	 * @return
	 */
	Response embedding(IdCommand idCommand);
	/**
	 * 嵌入文档片段，重新嵌入所有片段，已经嵌入的片段会重新嵌入，即删除原来已嵌入的向量，重新嵌入
	 * @param idCommand
	 * @return
	 */
	Response reEmbedding(IdCommand idCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<AgiVectorStoreRawDocumentVO> delete(IdCommand deleteCommand);
}
