package com.particle.agi.client.rag.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.agi.client.rag.dto.command.representation.AgiVectorStoreRawDocumentPageQueryCommand;
import com.particle.agi.client.rag.dto.command.representation.AgiVectorStoreRawDocumentQueryListCommand;
import com.particle.agi.client.rag.dto.data.AgiVectorStoreRawDocumentVO;

/**
 * <p>
 * 知识存储原始文档 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IAgiVectorStoreRawDocumentRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<AgiVectorStoreRawDocumentVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param agiVectorStoreRawDocumentQueryListCommand
	 * @return
	 */
	MultiResponse<AgiVectorStoreRawDocumentVO> queryList(AgiVectorStoreRawDocumentQueryListCommand agiVectorStoreRawDocumentQueryListCommand);

	/**
	 * 分页查询
	 * @param agiVectorStoreRawDocumentPageQueryCommand
	 * @return
	 */
	PageResponse<AgiVectorStoreRawDocumentVO> pageQuery(AgiVectorStoreRawDocumentPageQueryCommand agiVectorStoreRawDocumentPageQueryCommand);

}
