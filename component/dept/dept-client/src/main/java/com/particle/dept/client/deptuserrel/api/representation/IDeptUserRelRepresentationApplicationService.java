package com.particle.dept.client.deptuserrel.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.client.deptuserrel.dto.command.representation.DeptUserRelPageQueryCommand;
import com.particle.dept.client.deptuserrel.dto.command.representation.DeptUserRelQueryListCommand;
import com.particle.dept.client.deptuserrel.dto.data.DeptUserRelVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 部门用户关系 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDeptUserRelRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DeptUserRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DeptUserRelVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param deptUserRelQueryListCommand
	 * @return
	 */
	MultiResponse<DeptUserRelVO> queryList(DeptUserRelQueryListCommand deptUserRelQueryListCommand);

	/**
	 * 分页查询
	 * @param deptUserRelPageQueryCommand
	 * @return
	 */
	PageResponse<DeptUserRelVO> pageQuery(DeptUserRelPageQueryCommand deptUserRelPageQueryCommand);

}
