package com.particle.openplatform.client.providerrecord.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.providerrecord.dto.command.representation.OpenplatformProviderRecordPageQueryCommand;
import com.particle.openplatform.client.providerrecord.dto.command.representation.OpenplatformProviderRecordQueryListCommand;
import com.particle.openplatform.client.providerrecord.dto.data.OpenplatformProviderRecordVO;

/**
 * <p>
 * 开放平台开放接口供应商调用记录 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformProviderRecordRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderRecordVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformProviderRecordQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformProviderRecordVO> queryList(OpenplatformProviderRecordQueryListCommand openplatformProviderRecordQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformProviderRecordPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformProviderRecordVO> pageQuery(OpenplatformProviderRecordPageQueryCommand openplatformProviderRecordPageQueryCommand);

}
