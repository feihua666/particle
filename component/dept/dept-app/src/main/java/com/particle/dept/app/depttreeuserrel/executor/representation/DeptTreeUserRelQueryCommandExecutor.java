package com.particle.dept.app.depttreeuserrel.executor.representation;

import com.particle.dept.app.depttreeuserrel.structmapping.DeptTreeUserRelAppStructMapping;
import com.particle.dept.client.depttreeuserrel.dto.command.representation.DeptTreeUserRelQueryListCommand;
import com.particle.dept.client.depttreeuserrel.dto.data.DeptTreeUserRelVO;
import com.particle.dept.infrastructure.depttreeuserrel.dos.DeptTreeUserRelDO;
import com.particle.dept.infrastructure.depttreeuserrel.service.IDeptTreeUserRelService;
import com.particle.dept.client.depttreeuserrel.dto.command.representation.DeptTreeUserRelPageQueryCommand;
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
 * 部门树用户关系 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-05-17 10:26:06
 */
@Component
@Validated
public class DeptTreeUserRelQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDeptTreeUserRelService iDeptTreeUserRelService;

	/**
	 * 执行 部门树用户关系 列表查询指令
	 * @param deptTreeUserRelQueryListCommand
	 * @return
	 */
	public MultiResponse<DeptTreeUserRelVO> execute(@Valid DeptTreeUserRelQueryListCommand deptTreeUserRelQueryListCommand) {
		List<DeptTreeUserRelDO> deptTreeUserRelDO = iDeptTreeUserRelService.list(deptTreeUserRelQueryListCommand);
		List<DeptTreeUserRelVO> deptTreeUserRelVOs = DeptTreeUserRelAppStructMapping.instance.deptTreeUserRelDOsToDeptTreeUserRelVOs(deptTreeUserRelDO);
		return MultiResponse.of(deptTreeUserRelVOs);
	}
	/**
	 * 执行 部门树用户关系 分页查询指令
	 * @param deptTreeUserRelPageQueryCommand
	 * @return
	 */
	public PageResponse<DeptTreeUserRelVO> execute(@Valid DeptTreeUserRelPageQueryCommand deptTreeUserRelPageQueryCommand) {
		Page<DeptTreeUserRelDO> page = iDeptTreeUserRelService.listPage(deptTreeUserRelPageQueryCommand);
		return DeptTreeUserRelAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 部门树用户关系 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DeptTreeUserRelVO> executeDetail(IdCommand detailCommand) {
		DeptTreeUserRelDO byId = iDeptTreeUserRelService.getById(detailCommand.getId());
		DeptTreeUserRelVO deptTreeUserRelVO = DeptTreeUserRelAppStructMapping.instance.deptTreeUserRelDOToDeptTreeUserRelVO(byId);
		return SingleResponse.of(deptTreeUserRelVO);
	}
	/**
	 * 执行 部门树用户关系 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DeptTreeUserRelVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DeptTreeUserRelDO byId = iDeptTreeUserRelService.getById(detailForUpdateCommand.getId());
		DeptTreeUserRelVO deptTreeUserRelVO = DeptTreeUserRelAppStructMapping.instance.deptTreeUserRelDOToDeptTreeUserRelVO(byId);
		return SingleResponse.of(deptTreeUserRelVO);
	}

	@Autowired
	public void setIDeptTreeUserRelService(IDeptTreeUserRelService iDeptTreeUserRelService) {
		this.iDeptTreeUserRelService = iDeptTreeUserRelService;
	}
}
