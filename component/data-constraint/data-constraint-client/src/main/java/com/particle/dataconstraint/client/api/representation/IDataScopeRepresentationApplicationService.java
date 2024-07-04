package com.particle.dataconstraint.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.dataconstraint.client.dto.command.representation.DataScopePageQueryCommand;
import com.particle.dataconstraint.client.dto.command.representation.DataScopeQueryListCommand;
import com.particle.dataconstraint.client.dto.data.DataScopeVO;

/**
 * <p>
 * 数据范围 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataScopeRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataScopeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataScopeVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataScopeQueryListCommand
	 * @return
	 */
	MultiResponse<DataScopeVO> queryList(DataScopeQueryListCommand dataScopeQueryListCommand);

	/**
	 * 分页查询
	 * @param dataScopePageQueryCommand
	 * @return
	 */
	PageResponse<DataScopeVO> pageQuery(DataScopePageQueryCommand dataScopePageQueryCommand);

}
