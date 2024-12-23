package com.particle.dataquery.client.dataapi.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.client.dataapi.dto.command.DataQueryDataApiCreateCommand;
import com.particle.dataquery.client.dataapi.dto.command.DataQueryDataApiUpdateCommand;
import com.particle.dataquery.client.dataapi.dto.data.DataQueryDataApiVO;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 数据查询数据接口 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
public interface IDataQueryDataApiApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataQueryDataApiCreateCommand
	 * @return
	 */
	SingleResponse<DataQueryDataApiVO> create(DataQueryDataApiCreateCommand dataQueryDataApiCreateCommand);

	/**
	 * 复制
	 * @param copyCommand
	 * @return
	 */
	SingleResponse<DataQueryDataApiVO> copy(IdCommand copyCommand);
	SingleResponse<DataQueryDataApiVO> copydev(IdCommand copyCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataQueryDataApiVO> delete(IdCommand deleteCommand);

	/**
	 * 删除查询时缓存
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<String> deleteCache(IdCommand deleteCommand);

	/**
	 * 更新缓存
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<String> refreshCache(IdCommand deleteCommand);

	/**
	 * dev合并到master
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataQueryDataApiVO> devMergeToMaster(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataQueryDataApiUpdateCommand
	 * @return
	 */
	SingleResponse<DataQueryDataApiVO> update(DataQueryDataApiUpdateCommand dataQueryDataApiUpdateCommand);

}
