package com.particle.dataquery.client.datasource.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceApiCreateCommand;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceApiUpdateCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceApiVO;
import com.particle.global.dto.response.SingleResponse;

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
	 * 复制
	 * @param copyCommand
	 * @return
	 */
	SingleResponse<DataQueryDatasourceApiVO> copy(IdCommand copyCommand);
	SingleResponse<DataQueryDatasourceApiVO> copydev(IdCommand copyCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataQueryDatasourceApiVO> delete(IdCommand deleteCommand);

	/**
	 * 删除缓存
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<String> deleteCache(IdCommand deleteCommand);

	/**
	 * 刷新缓存
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<String> refreshCache(IdCommand deleteCommand);

	/**
	 * dev合并到master，devMergeToMaster
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataQueryDatasourceApiVO> devMergeToMaster(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataQueryDatasourceApiUpdateCommand
	 * @return
	 */
	SingleResponse<DataQueryDatasourceApiVO> update(DataQueryDatasourceApiUpdateCommand dataQueryDatasourceApiUpdateCommand);

}
