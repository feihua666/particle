package com.particle.dept.client.depttreeuserrel.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.dept.client.depttreeuserrel.dto.command.DeptTreeUserRelCreateCommand;
import com.particle.dept.client.depttreeuserrel.dto.command.DeptTreeUserRelUpdateCommand;
import com.particle.dept.client.depttreeuserrel.dto.data.DeptTreeUserRelVO;

/**
 * <p>
 * 部门树用户关系 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-04-12 17:28:43
 */
public interface IDeptTreeUserRelApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param deptTreeUserRelCreateCommand
	 * @return
	 */
	SingleResponse<DeptTreeUserRelVO> create(DeptTreeUserRelCreateCommand deptTreeUserRelCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DeptTreeUserRelVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param deptTreeUserRelUpdateCommand
	 * @return
	 */
	SingleResponse<DeptTreeUserRelVO> update(DeptTreeUserRelUpdateCommand deptTreeUserRelUpdateCommand);

}
