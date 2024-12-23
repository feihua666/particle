package com.particle.dept.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.client.dto.command.DeptTreeNameCreateCommand;
import com.particle.dept.client.dto.command.DeptTreeNameUpdateCommand;
import com.particle.dept.client.dto.data.DeptTreeNameVO;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 部门树名称 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:42:10
 */
public interface IDeptTreeNameApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param deptTreeNameCreateCommand
	 * @return
	 */
	SingleResponse<DeptTreeNameVO> create(DeptTreeNameCreateCommand deptTreeNameCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DeptTreeNameVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param deptTreeNameUpdateCommand
	 * @return
	 */
	SingleResponse<DeptTreeNameVO> update(DeptTreeNameUpdateCommand deptTreeNameUpdateCommand);

}
