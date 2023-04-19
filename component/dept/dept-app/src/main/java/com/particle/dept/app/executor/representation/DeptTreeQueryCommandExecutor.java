package com.particle.dept.app.executor.representation;

import com.particle.dept.app.structmapping.DeptTreeAppStructMapping;
import com.particle.dept.client.dto.command.representation.DeptTreeQueryListCommand;
import com.particle.dept.client.dto.data.DeptTreeVO;
import com.particle.dept.infrastructure.dos.DeptTreeDO;
import com.particle.dept.infrastructure.service.IDeptTreeService;
import com.particle.dept.client.dto.command.representation.DeptTreePageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import javax.validation.Valid;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 部门树 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-04-12 11:41:43
 */
@Component
@Validated
public class DeptTreeQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDeptTreeService iDeptTreeService;

	/**
	 * 执行 部门树 列表查询指令
	 * @param deptTreeQueryListCommand
	 * @return
	 */
	public MultiResponse<DeptTreeVO> execute(@Valid DeptTreeQueryListCommand deptTreeQueryListCommand) {
		List<DeptTreeDO> deptTreeDO = iDeptTreeService.list(deptTreeQueryListCommand);
		List<DeptTreeVO> deptTreeVOs = DeptTreeAppStructMapping.instance.deptTreeDOsToDeptTreeVOs(deptTreeDO);
		return MultiResponse.of(deptTreeVOs);
	}
	/**
	 * 执行 部门树 分页查询指令
	 * @param deptTreePageQueryCommand
	 * @return
	 */
	public PageResponse<DeptTreeVO> execute(@Valid DeptTreePageQueryCommand deptTreePageQueryCommand) {
		Page<DeptTreeDO> page = iDeptTreeService.listPage(deptTreePageQueryCommand);
		return DeptTreeAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 部门树 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DeptTreeVO> executeDetail(IdCommand detailCommand) {
		DeptTreeDO byId = iDeptTreeService.getById(detailCommand.getId());
		DeptTreeVO deptTreeVO = DeptTreeAppStructMapping.instance.deptTreeDOToDeptTreeVO(byId);
		return SingleResponse.of(deptTreeVO);
	}
	/**
	 * 执行 部门树 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DeptTreeVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DeptTreeDO byId = iDeptTreeService.getById(detailForUpdateCommand.getId());
		DeptTreeVO deptTreeVO = DeptTreeAppStructMapping.instance.deptTreeDOToDeptTreeVO(byId);
		return SingleResponse.of(deptTreeVO);
	}

	@Autowired
	public void setIDeptTreeService(IDeptTreeService iDeptTreeService) {
		this.iDeptTreeService = iDeptTreeService;
	}
}
