package com.particle.dataconstraint.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataconstraint.client.dto.command.representation.DataObjectPageQueryCommand;
import com.particle.dataconstraint.client.dto.command.representation.DataObjectQueryListCommand;
import com.particle.dataconstraint.client.dto.data.DataObjectVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 数据对象 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDataObjectRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DataObjectVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DataObjectVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param dataObjectQueryListCommand
	 * @return
	 */
	MultiResponse<DataObjectVO> queryList(DataObjectQueryListCommand dataObjectQueryListCommand);

	/**
	 * 分页查询
	 * @param dataObjectPageQueryCommand
	 * @return
	 */
	PageResponse<DataObjectVO> pageQuery(DataObjectPageQueryCommand dataObjectPageQueryCommand);

}
