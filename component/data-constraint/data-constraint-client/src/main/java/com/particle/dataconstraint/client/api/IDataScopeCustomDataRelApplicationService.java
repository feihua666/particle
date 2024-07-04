package com.particle.dataconstraint.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataconstraint.client.dto.command.DataScopeAssignCustomDataCommand;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.dataconstraint.client.dto.command.DataScopeCustomDataRelCreateCommand;
import com.particle.dataconstraint.client.dto.command.DataScopeCustomDataRelUpdateCommand;
import com.particle.dataconstraint.client.dto.data.DataScopeCustomDataRelVO;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 数据范围自定义数据关系 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:55
 */
public interface IDataScopeCustomDataRelApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataScopeCustomDataRelCreateCommand
	 * @return
	 */
	SingleResponse<DataScopeCustomDataRelVO> create(DataScopeCustomDataRelCreateCommand dataScopeCustomDataRelCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataScopeCustomDataRelVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataScopeCustomDataRelUpdateCommand
	 * @return
	 */
	SingleResponse<DataScopeCustomDataRelVO> update(DataScopeCustomDataRelUpdateCommand dataScopeCustomDataRelUpdateCommand);

	/**
	 * 数据范围分配自定义数据
	 * @param cf
	 * @return
	 */
	public Response dataScopeAssignCustomData(DataScopeAssignCustomDataCommand cf);

	/**
	 * 根据数据范围id删除
	 * @param dataScopeIdCommand
	 * @return
	 */
	public Response deleteByDataScopeId(@RequestBody IdCommand dataScopeIdCommand);
}
