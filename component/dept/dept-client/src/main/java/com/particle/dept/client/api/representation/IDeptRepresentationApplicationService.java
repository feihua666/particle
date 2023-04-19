package com.particle.dept.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.dept.client.dto.command.representation.DeptPageQueryCommand;
import com.particle.dept.client.dto.command.representation.DeptQueryListCommand;
import com.particle.dept.client.dto.data.DeptVO;

/**
 * <p>
 * 部门 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDeptRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DeptVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DeptVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param deptQueryListCommand
	 * @return
	 */
	MultiResponse<DeptVO> queryList(DeptQueryListCommand deptQueryListCommand);

	/**
	 * 分页查询
	 * @param deptPageQueryCommand
	 * @return
	 */
	PageResponse<DeptVO> pageQuery(DeptPageQueryCommand deptPageQueryCommand);

}
