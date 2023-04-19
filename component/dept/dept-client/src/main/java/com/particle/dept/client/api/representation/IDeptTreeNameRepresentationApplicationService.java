package com.particle.dept.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.dept.client.dto.command.representation.DeptTreeNamePageQueryCommand;
import com.particle.dept.client.dto.command.representation.DeptTreeNameQueryListCommand;
import com.particle.dept.client.dto.data.DeptTreeNameVO;

/**
 * <p>
 * 部门树名称 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IDeptTreeNameRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<DeptTreeNameVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<DeptTreeNameVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param deptTreeNameQueryListCommand
	 * @return
	 */
	MultiResponse<DeptTreeNameVO> queryList(DeptTreeNameQueryListCommand deptTreeNameQueryListCommand);

	/**
	 * 分页查询
	 * @param deptTreeNamePageQueryCommand
	 * @return
	 */
	PageResponse<DeptTreeNameVO> pageQuery(DeptTreeNamePageQueryCommand deptTreeNamePageQueryCommand);

}
