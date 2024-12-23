package com.particle.dept.client.deptuserrel.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.client.deptuserrel.dto.command.DeptUserRelCreateCommand;
import com.particle.dept.client.deptuserrel.dto.command.DeptUserRelUpdateCommand;
import com.particle.dept.client.deptuserrel.dto.data.DeptUserRelVO;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 部门用户关系 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:28:42
 */
public interface IDeptUserRelApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param deptUserRelCreateCommand
	 * @return
	 */
	SingleResponse<DeptUserRelVO> create(DeptUserRelCreateCommand deptUserRelCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DeptUserRelVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param deptUserRelUpdateCommand
	 * @return
	 */
	SingleResponse<DeptUserRelVO> update(DeptUserRelUpdateCommand deptUserRelUpdateCommand);

}
