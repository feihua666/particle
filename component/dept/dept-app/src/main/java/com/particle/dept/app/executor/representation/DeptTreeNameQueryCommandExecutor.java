package com.particle.dept.app.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.app.structmapping.DeptTreeNameAppStructMapping;
import com.particle.dept.client.dto.command.representation.DeptTreeNamePageQueryCommand;
import com.particle.dept.client.dto.command.representation.DeptTreeNameQueryListCommand;
import com.particle.dept.client.dto.data.DeptTreeNameVO;
import com.particle.dept.infrastructure.dos.DeptTreeNameDO;
import com.particle.dept.infrastructure.service.IDeptTreeNameService;
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
 * 部门树名称 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-04-12 11:42:10
 */
@Component
@Validated
public class DeptTreeNameQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDeptTreeNameService iDeptTreeNameService;

	/**
	 * 执行 部门树名称 列表查询指令
	 * @param deptTreeNameQueryListCommand
	 * @return
	 */
	public MultiResponse<DeptTreeNameVO> execute(@Valid DeptTreeNameQueryListCommand deptTreeNameQueryListCommand) {
		List<DeptTreeNameDO> deptTreeNameDO = iDeptTreeNameService.list(deptTreeNameQueryListCommand);
		List<DeptTreeNameVO> deptTreeNameVOs = DeptTreeNameAppStructMapping.instance.deptTreeNameDOsToDeptTreeNameVOs(deptTreeNameDO);
		return MultiResponse.of(deptTreeNameVOs);
	}
	/**
	 * 执行 部门树名称 分页查询指令
	 * @param deptTreeNamePageQueryCommand
	 * @return
	 */
	public PageResponse<DeptTreeNameVO> execute(@Valid DeptTreeNamePageQueryCommand deptTreeNamePageQueryCommand) {
		Page<DeptTreeNameDO> page = iDeptTreeNameService.listPage(deptTreeNamePageQueryCommand);
		return DeptTreeNameAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 部门树名称 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DeptTreeNameVO> executeDetail(IdCommand detailCommand) {
		DeptTreeNameDO byId = iDeptTreeNameService.getById(detailCommand.getId());
		DeptTreeNameVO deptTreeNameVO = DeptTreeNameAppStructMapping.instance.deptTreeNameDOToDeptTreeNameVO(byId);
		return SingleResponse.of(deptTreeNameVO);
	}
	/**
	 * 执行 部门树名称 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DeptTreeNameVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DeptTreeNameDO byId = iDeptTreeNameService.getById(detailForUpdateCommand.getId());
		DeptTreeNameVO deptTreeNameVO = DeptTreeNameAppStructMapping.instance.deptTreeNameDOToDeptTreeNameVO(byId);
		return SingleResponse.of(deptTreeNameVO);
	}

	@Autowired
	public void setIDeptTreeNameService(IDeptTreeNameService iDeptTreeNameService) {
		this.iDeptTreeNameService = iDeptTreeNameService;
	}
}
