package com.particle.openplatform.client.openapirecord.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.openapirecord.dto.command.representation.OpenplatformOpenapiRecordParamPageQueryCommand;
import com.particle.openplatform.client.openapirecord.dto.command.representation.OpenplatformOpenapiRecordParamQueryListCommand;
import com.particle.openplatform.client.openapirecord.dto.data.OpenplatformOpenapiRecordParamVO;

/**
 * <p>
 * 开放平台开放接口调用记录参数 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformOpenapiRecordParamRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordParamVO> queryDetail(IdCommand detailCommand);

	/**
	 * 查询详情，仅展示使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordParamVO> detailByOpenplatformOpenapiRecordId(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformOpenapiRecordParamQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformOpenapiRecordParamVO> queryList(OpenplatformOpenapiRecordParamQueryListCommand openplatformOpenapiRecordParamQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformOpenapiRecordParamPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformOpenapiRecordParamVO> pageQuery(OpenplatformOpenapiRecordParamPageQueryCommand openplatformOpenapiRecordParamPageQueryCommand);

}
