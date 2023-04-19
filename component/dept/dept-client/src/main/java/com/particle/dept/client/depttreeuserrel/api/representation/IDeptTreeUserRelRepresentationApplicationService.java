package com.particle.dept.client.depttreeuserrel.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.dept.client.depttreeuserrel.dto.command.representation.DeptTreeUserRelPageQueryCommand;
import com.particle.dept.client.depttreeuserrel.dto.command.representation.DeptTreeUserRelQueryListCommand;
import com.particle.dept.client.depttreeuserrel.dto.data.DeptTreeUserRelVO;

/**
 * <p>
 * 部门树用户关系 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDeptTreeUserRelRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DeptTreeUserRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DeptTreeUserRelVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param deptTreeUserRelQueryListCommand
	 * @return
	 */
	MultiResponse<DeptTreeUserRelVO> queryList(DeptTreeUserRelQueryListCommand deptTreeUserRelQueryListCommand);

	/**
	 * 分页查询
	 * @param deptTreeUserRelPageQueryCommand
	 * @return
	 */
	PageResponse<DeptTreeUserRelVO> pageQuery(DeptTreeUserRelPageQueryCommand deptTreeUserRelPageQueryCommand);

}
