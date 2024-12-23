package com.particle.dept.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.client.dto.command.DeptCreateCommand;
import com.particle.dept.client.dto.command.DeptUpdateCommand;
import com.particle.dept.client.dto.data.DeptVO;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 部门 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
public interface IDeptApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param deptCreateCommand
	 * @return
	 */
	SingleResponse<DeptVO> create(DeptCreateCommand deptCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DeptVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param deptUpdateCommand
	 * @return
	 */
	SingleResponse<DeptVO> update(DeptUpdateCommand deptUpdateCommand);

}
