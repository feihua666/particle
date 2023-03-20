package com.particle.dataquery.client.datasource.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceApiTestCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceApiPageQueryCommand;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceApiQueryListCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceApiVO;

/**
 * <p>
 * 数据查询数据源接口 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataQueryDatasourceApiRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataQueryDatasourceApiVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataQueryDatasourceApiVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataQueryDatasourceApiQueryListCommand
	 * @return
	 */
	MultiResponse<DataQueryDatasourceApiVO> queryList(DataQueryDatasourceApiQueryListCommand dataQueryDatasourceApiQueryListCommand);

	/**
	 * 分页查询
	 * @param dataQueryDatasourceApiPageQueryCommand
	 * @return
	 */
	PageResponse<DataQueryDatasourceApiVO> pageQuery(DataQueryDatasourceApiPageQueryCommand dataQueryDatasourceApiPageQueryCommand);

	/**
	 * 接口测试
	 * @param dataQueryDatasourceApiTestCommand
	 * @return
	 */
	Object test(DataQueryDatasourceApiTestCommand dataQueryDatasourceApiTestCommand);
}
