package com.particle.dept.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.client.dto.command.representation.DeptTreePageQueryCommand;
import com.particle.dept.client.dto.command.representation.DeptTreeQueryListCommand;
import com.particle.dept.client.dto.data.DeptTreeVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 部门树 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDeptTreeRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DeptTreeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DeptTreeVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param deptTreeQueryListCommand
	 * @return
	 */
	MultiResponse<DeptTreeVO> queryList(DeptTreeQueryListCommand deptTreeQueryListCommand);

	/**
	 * 分页查询
	 * @param deptTreePageQueryCommand
	 * @return
	 */
	PageResponse<DeptTreeVO> pageQuery(DeptTreePageQueryCommand deptTreePageQueryCommand);

}
