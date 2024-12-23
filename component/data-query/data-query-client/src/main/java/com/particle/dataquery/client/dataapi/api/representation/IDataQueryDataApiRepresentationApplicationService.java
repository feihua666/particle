package com.particle.dataquery.client.dataapi.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.client.dataapi.dto.command.representation.DataQueryDataApiPageQueryCommand;
import com.particle.dataquery.client.dataapi.dto.command.representation.DataQueryDataApiQueryCommand;
import com.particle.dataquery.client.dataapi.dto.command.representation.DataQueryDataApiQueryListCommand;
import com.particle.dataquery.client.dataapi.dto.data.DataQueryDataApiVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 数据查询数据接口 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataQueryDataApiRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataQueryDataApiVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataQueryDataApiVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataQueryDataApiQueryListCommand
	 * @return
	 */
	MultiResponse<DataQueryDataApiVO> queryList(DataQueryDataApiQueryListCommand dataQueryDataApiQueryListCommand);

	/**
	 * 分页查询
	 * @param dataQueryDataApiPageQueryCommand
	 * @return
	 */
	PageResponse<DataQueryDataApiVO> pageQuery(DataQueryDataApiPageQueryCommand dataQueryDataApiPageQueryCommand);


	/**
	 * 数据接口服务查询
	 * @param dataQueryDataApiQueryCommand
	 * @return
	 */
	Object dataApiQuery(DataQueryDataApiQueryCommand dataQueryDataApiQueryCommand);

	/**
	 * 仅用来测试
	 * @param dataQueryDataApiQueryCommand
	 * @return
	 */
	Object dataApiQueryTest(DataQueryDataApiQueryCommand dataQueryDataApiQueryCommand);

	/**
	 * 对数据查询接口预热，由于有脚本逻辑，和数据源初始化逻辑，需要预热以加快访问速度
	 * @return
	 */
	public Response warmUp();

	/**
	 * 对数据查询接口经量级预热，由于有脚本逻辑，和数据源初始化逻辑，需要预热以加快访问速度，仅编译脚本
	 * @return
	 */
	public Response warmUpForLight();
}
