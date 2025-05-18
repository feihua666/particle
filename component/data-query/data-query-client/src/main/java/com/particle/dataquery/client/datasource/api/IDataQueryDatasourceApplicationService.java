package com.particle.dataquery.client.datasource.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceCreateCommand;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceReloadCommand;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceUpdateCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceVO;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 数据查询数据源 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
public interface IDataQueryDatasourceApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataQueryDatasourceCreateCommand
	 * @return
	 */
	SingleResponse<DataQueryDatasourceVO> create(DataQueryDatasourceCreateCommand dataQueryDatasourceCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataQueryDatasourceVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataQueryDatasourceUpdateCommand
	 * @return
	 */
	SingleResponse<DataQueryDatasourceVO> update(DataQueryDatasourceUpdateCommand dataQueryDatasourceUpdateCommand);

	/**
	 * 重新加载数据源
	 * @param dataQueryDatasourceReloadCommand
	 * @return
	 */
	public SingleResponse<String> reload(DataQueryDatasourceReloadCommand dataQueryDatasourceReloadCommand);

	/**
	 * 复制
	 *
	 * @param copyCommand
	 * @return
	 */
	public SingleResponse<DataQueryDatasourceVO> copy(IdCommand copyCommand);

}
