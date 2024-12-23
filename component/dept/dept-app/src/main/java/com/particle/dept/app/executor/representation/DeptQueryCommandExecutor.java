package com.particle.dept.app.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.app.structmapping.DeptAppStructMapping;
import com.particle.dept.client.dto.command.representation.DeptPageQueryCommand;
import com.particle.dept.client.dto.command.representation.DeptQueryListCommand;
import com.particle.dept.client.dto.data.DeptVO;
import com.particle.dept.infrastructure.dos.DeptDO;
import com.particle.dept.infrastructure.service.IDeptService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 部门 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Component
@Validated
public class DeptQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDeptService iDeptService;

	/**
	 * 执行 部门 列表查询指令
	 * @param deptQueryListCommand
	 * @return
	 */
	public MultiResponse<DeptVO> execute(@Valid DeptQueryListCommand deptQueryListCommand) {
		List<DeptDO> deptDO = iDeptService.list(deptQueryListCommand);
		List<DeptVO> deptVOs = DeptAppStructMapping.instance.deptDOsToDeptVOs(deptDO);
		return MultiResponse.of(deptVOs);
	}
	/**
	 * 执行 部门 分页查询指令
	 * @param deptPageQueryCommand
	 * @return
	 */
	public PageResponse<DeptVO> execute(@Valid DeptPageQueryCommand deptPageQueryCommand) {
		Page<DeptDO> page = iDeptService.listPage(deptPageQueryCommand);
		return DeptAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 部门 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DeptVO> executeDetail(IdCommand detailCommand) {
		DeptDO byId = iDeptService.getById(detailCommand.getId());
		DeptVO deptVO = DeptAppStructMapping.instance.deptDOToDeptVO(byId);
		return SingleResponse.of(deptVO);
	}
	/**
	 * 执行 部门 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DeptVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DeptDO byId = iDeptService.getById(detailForUpdateCommand.getId());
		DeptVO deptVO = DeptAppStructMapping.instance.deptDOToDeptVO(byId);
		return SingleResponse.of(deptVO);
	}

	@Autowired
	public void setIDeptService(IDeptService iDeptService) {
		this.iDeptService = iDeptService;
	}
}
