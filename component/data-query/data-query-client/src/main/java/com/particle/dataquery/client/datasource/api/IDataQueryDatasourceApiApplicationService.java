package com.particle.dataquery.client.datasource.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceApiCreateCommand;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceApiUpdateCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceApiVO;

/**
 * <p>
 * 数据查询数据源接口 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
public interface IDataQueryDatasourceApiApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataQueryDatasourceApiCreateCommand
	 * @return
	 */
	SingleResponse<DataQueryDatasourceApiVO> create(DataQueryDatasourceApiCreateCommand dataQueryDatasourceApiCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataQueryDatasourceApiVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataQueryDatasourceApiUpdateCommand
	 * @return
	 */
	SingleResponse<DataQueryDatasourceApiVO> update(DataQueryDatasourceApiUpdateCommand dataQueryDatasourceApiUpdateCommand);

}
