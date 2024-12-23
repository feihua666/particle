package com.particle.dataconstraint.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataconstraint.client.dto.command.representation.DataScopeCustomDataRelPageQueryCommand;
import com.particle.dataconstraint.client.dto.command.representation.DataScopeCustomDataRelQueryListCommand;
import com.particle.dataconstraint.client.dto.data.DataScopeCustomDataRelVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 数据范围自定义数据关系 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataScopeCustomDataRelRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataScopeCustomDataRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataScopeCustomDataRelVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataScopeCustomDataRelQueryListCommand
	 * @return
	 */
	MultiResponse<DataScopeCustomDataRelVO> queryList(DataScopeCustomDataRelQueryListCommand dataScopeCustomDataRelQueryListCommand);

	/**
	 * 分页查询
	 * @param dataScopeCustomDataRelPageQueryCommand
	 * @return
	 */
	PageResponse<DataScopeCustomDataRelVO> pageQuery(DataScopeCustomDataRelPageQueryCommand dataScopeCustomDataRelPageQueryCommand);

	/**
	 * 根据数据范围id查询已分配的自定义数据id
	 * @param dataScopeIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryCustomDataIdsByDataScopeId(IdCommand dataScopeIdCommand);
}
