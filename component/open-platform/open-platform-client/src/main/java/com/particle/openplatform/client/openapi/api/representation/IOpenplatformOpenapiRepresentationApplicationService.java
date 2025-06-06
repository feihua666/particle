package com.particle.openplatform.client.openapi.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.openapi.dto.command.representation.*;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiBatchQueryRecordVO;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiDownloadBatchQueryTemplateVO;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiVO;

/**
 * <p>
 * 开放平台开放接口 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformOpenapiRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformOpenapiQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformOpenapiVO> queryList(OpenplatformOpenapiQueryListCommand openplatformOpenapiQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformOpenapiPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformOpenapiVO> pageQuery(OpenplatformOpenapiPageQueryCommand openplatformOpenapiPageQueryCommand);

	/**
	 * 开放接口单次查询
	 * @param openplatformOpenapiSingleQueryCommand
	 * @return
	 */
	public SingleResponse<String> singleQuery(OpenplatformOpenapiSingleQueryCommand openplatformOpenapiSingleQueryCommand);

	/**
	 * 开放接口批量查询
	 * @param openplatformOpenapiBatchQueryCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiBatchQueryRecordVO> batchQuery(OpenplatformOpenapiBatchQueryCommand openplatformOpenapiBatchQueryCommand);

	/**
	 * 异步执行查询和导出
	 * 缘由
	 * 原来在 {@link IOpenplatformOpenapiRepresentationApplicationService#batchQuery(OpenplatformOpenapiBatchQueryCommand)} 中使用异步，但有可能会由于事务未提交导致查询不到数据的情况
	 * @param openplatformOpenapiBatchQueryRecordId
	 * @return
	 */
	public Response asyncBatchQueryAndExport(IdCommand openplatformOpenapiBatchQueryRecordId);
	/**
	 * 下载批量查询模板
	 *
	 * @param openplatformOpenapiDownloadBatchQueryTemplateCommand
	 * @return
	 */
	public OpenplatformOpenapiDownloadBatchQueryTemplateVO downloadBatchQueryTemplate(OpenplatformOpenapiDownloadBatchQueryTemplateCommand openplatformOpenapiDownloadBatchQueryTemplateCommand);
}
