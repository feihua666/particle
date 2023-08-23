package com.particle.openplatform.client.providerrecord.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.openapirecord.dto.data.OpenplatformOpenapiRecordParamVO;
import com.particle.openplatform.client.providerrecord.dto.command.representation.OpenplatformProviderRecordParamPageQueryCommand;
import com.particle.openplatform.client.providerrecord.dto.command.representation.OpenplatformProviderRecordParamQueryListCommand;
import com.particle.openplatform.client.providerrecord.dto.data.OpenplatformProviderRecordParamVO;

/**
 * <p>
 * 开放平台开放接口供应商调用记录参数 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformProviderRecordParamRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderRecordParamVO> queryDetail(IdCommand detailCommand);

	/**
	 * 根据供应商调用记录id查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderRecordParamVO> detailByOpenplatformProviderRecordId(IdCommand detailCommand);
	/**
	 * 列表查询
	 * @param openplatformProviderRecordParamQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformProviderRecordParamVO> queryList(OpenplatformProviderRecordParamQueryListCommand openplatformProviderRecordParamQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformProviderRecordParamPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformProviderRecordParamVO> pageQuery(OpenplatformProviderRecordParamPageQueryCommand openplatformProviderRecordParamPageQueryCommand);

}
