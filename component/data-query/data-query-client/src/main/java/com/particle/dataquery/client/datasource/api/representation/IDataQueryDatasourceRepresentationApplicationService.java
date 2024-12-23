package com.particle.dataquery.client.datasource.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourcePageQueryCommand;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceQueryListCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 数据查询数据源 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataQueryDatasourceRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataQueryDatasourceVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataQueryDatasourceVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataQueryDatasourceQueryListCommand
	 * @return
	 */
	MultiResponse<DataQueryDatasourceVO> queryList(DataQueryDatasourceQueryListCommand dataQueryDatasourceQueryListCommand);

	/**
	 * 分页查询
	 * @param dataQueryDatasourcePageQueryCommand
	 * @return
	 */
	PageResponse<DataQueryDatasourceVO> pageQuery(DataQueryDatasourcePageQueryCommand dataQueryDatasourcePageQueryCommand);

	/**
	 * 对数据源经量级预热，由于有脚本逻辑，和数据源初始化逻辑，需要预热以加快访问速度，仅编译脚本
	 * @return
	 */
	public Response warmUpForLight();
}
