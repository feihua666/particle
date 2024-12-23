package com.particle.dept.app.deptuserrel.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.app.deptuserrel.structmapping.DeptUserRelAppStructMapping;
import com.particle.dept.client.deptuserrel.dto.command.representation.DeptUserRelPageQueryCommand;
import com.particle.dept.client.deptuserrel.dto.command.representation.DeptUserRelQueryListCommand;
import com.particle.dept.client.deptuserrel.dto.data.DeptUserRelVO;
import com.particle.dept.infrastructure.deptuserrel.dos.DeptUserRelDO;
import com.particle.dept.infrastructure.deptuserrel.service.IDeptUserRelService;
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
 * 部门用户关系 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-05-17 10:28:42
 */
@Component
@Validated
public class DeptUserRelQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDeptUserRelService iDeptUserRelService;

	/**
	 * 执行 部门用户关系 列表查询指令
	 * @param deptUserRelQueryListCommand
	 * @return
	 */
	public MultiResponse<DeptUserRelVO> execute(@Valid DeptUserRelQueryListCommand deptUserRelQueryListCommand) {
		List<DeptUserRelDO> deptUserRelDO = iDeptUserRelService.list(deptUserRelQueryListCommand);
		List<DeptUserRelVO> deptUserRelVOs = DeptUserRelAppStructMapping.instance.deptUserRelDOsToDeptUserRelVOs(deptUserRelDO);
		return MultiResponse.of(deptUserRelVOs);
	}
	/**
	 * 执行 部门用户关系 分页查询指令
	 * @param deptUserRelPageQueryCommand
	 * @return
	 */
	public PageResponse<DeptUserRelVO> execute(@Valid DeptUserRelPageQueryCommand deptUserRelPageQueryCommand) {
		Page<DeptUserRelDO> page = iDeptUserRelService.listPage(deptUserRelPageQueryCommand);
		return DeptUserRelAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 部门用户关系 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DeptUserRelVO> executeDetail(IdCommand detailCommand) {
		DeptUserRelDO byId = iDeptUserRelService.getById(detailCommand.getId());
		DeptUserRelVO deptUserRelVO = DeptUserRelAppStructMapping.instance.deptUserRelDOToDeptUserRelVO(byId);
		return SingleResponse.of(deptUserRelVO);
	}
	/**
	 * 执行 部门用户关系 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DeptUserRelVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DeptUserRelDO byId = iDeptUserRelService.getById(detailForUpdateCommand.getId());
		DeptUserRelVO deptUserRelVO = DeptUserRelAppStructMapping.instance.deptUserRelDOToDeptUserRelVO(byId);
		return SingleResponse.of(deptUserRelVO);
	}

	@Autowired
	public void setIDeptUserRelService(IDeptUserRelService iDeptUserRelService) {
		this.iDeptUserRelService = iDeptUserRelService;
	}
}
