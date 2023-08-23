package com.particle.openplatform.client.openapirecord.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.openapirecord.dto.command.representation.OpenplatformOpenapiRecordPageQueryCommand;
import com.particle.openplatform.client.openapirecord.dto.command.representation.OpenplatformOpenapiRecordQueryListCommand;
import com.particle.openplatform.client.openapirecord.dto.data.OpenplatformOpenapiRecordVO;

/**
 * <p>
 * 开放平台开放接口调用记录 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformOpenapiRecordRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformOpenapiRecordQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformOpenapiRecordVO> queryList(OpenplatformOpenapiRecordQueryListCommand openplatformOpenapiRecordQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformOpenapiRecordPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformOpenapiRecordVO> pageQuery(OpenplatformOpenapiRecordPageQueryCommand openplatformOpenapiRecordPageQueryCommand);

}
