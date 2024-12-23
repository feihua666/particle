package com.particle.dataquery.client.provider.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.client.provider.dto.command.DataQueryProviderCreateCommand;
import com.particle.dataquery.client.provider.dto.command.DataQueryProviderUpdateCommand;
import com.particle.dataquery.client.provider.dto.data.DataQueryProviderVO;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 数据查询供应商 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
public interface IDataQueryProviderApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataQueryProviderCreateCommand
	 * @return
	 */
	SingleResponse<DataQueryProviderVO> create(DataQueryProviderCreateCommand dataQueryProviderCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataQueryProviderVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataQueryProviderUpdateCommand
	 * @return
	 */
	SingleResponse<DataQueryProviderVO> update(DataQueryProviderUpdateCommand dataQueryProviderUpdateCommand);

}
