package com.particle.dept.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.dept.client.dto.command.DeptTreeCreateCommand;
import com.particle.dept.client.dto.command.DeptTreeUpdateCommand;
import com.particle.dept.client.dto.data.DeptTreeVO;

/**
 * <p>
 * 部门树 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
public interface IDeptTreeApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param deptTreeCreateCommand
	 * @return
	 */
	SingleResponse<DeptTreeVO> create(DeptTreeCreateCommand deptTreeCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DeptTreeVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param deptTreeUpdateCommand
	 * @return
	 */
	SingleResponse<DeptTreeVO> update(DeptTreeUpdateCommand deptTreeUpdateCommand);

}
