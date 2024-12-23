package com.particle.dataquery.client.provider.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.client.provider.dto.command.representation.DataQueryProviderPageQueryCommand;
import com.particle.dataquery.client.provider.dto.command.representation.DataQueryProviderQueryListCommand;
import com.particle.dataquery.client.provider.dto.data.DataQueryProviderVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 数据查询供应商 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataQueryProviderRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataQueryProviderVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataQueryProviderVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataQueryProviderQueryListCommand
	 * @return
	 */
	MultiResponse<DataQueryProviderVO> queryList(DataQueryProviderQueryListCommand dataQueryProviderQueryListCommand);

	/**
	 * 分页查询
	 * @param dataQueryProviderPageQueryCommand
	 * @return
	 */
	PageResponse<DataQueryProviderVO> pageQuery(DataQueryProviderPageQueryCommand dataQueryProviderPageQueryCommand);

}
