package com.particle.dept.adapter.web.admin;

import com.particle.dept.client.api.IDeptApplicationService;
import com.particle.dept.client.api.representation.IDeptRepresentationApplicationService;
import com.particle.dept.client.dto.command.DeptCreateCommand;
import com.particle.dept.client.dto.data.DeptVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.client.dto.command.DeptUpdateCommand;
import com.particle.dept.client.dto.command.representation.DeptPageQueryCommand;
import com.particle.dept.client.dto.command.representation.DeptQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
/**
 * <p>
 * 部门后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Api(tags = "部门pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/dept")
public class DeptAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDeptApplicationService iDeptApplicationService;
	@Autowired
	private IDeptRepresentationApplicationService iDeptRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:dept:create')")
	@ApiOperation("添加部门")
	@PostMapping("/create")
	public SingleResponse<DeptVO> create(@RequestBody DeptCreateCommand deptCreateCommand){
		return iDeptApplicationService.create(deptCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dept:delete')")
	@ApiOperation("删除部门")
	@DeleteMapping("/delete")
	public SingleResponse<DeptVO> delete(@RequestBody IdCommand deleteCommand){
		return iDeptApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dept:update')")
	@ApiOperation("更新部门")
	@PutMapping("/update")
	public SingleResponse<DeptVO> update(@RequestBody DeptUpdateCommand deptUpdateCommand){
		return iDeptApplicationService.update(deptUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dept:update')")
	@ApiOperation("部门更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<DeptVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iDeptRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dept:detail')")
	@ApiOperation("部门详情展示")
	@GetMapping("/detail")
	public SingleResponse<DeptVO> queryDetail(IdCommand detailCommand){
		return iDeptRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dept:queryList')")
	@ApiOperation("列表查询部门")
	@GetMapping("/list")
	public MultiResponse<DeptVO> queryList(DeptQueryListCommand deptQueryListCommand){
		return iDeptRepresentationApplicationService.queryList(deptQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dept:pageQuery')")
	@ApiOperation("分页查询部门")
	@GetMapping("/page")
	public PageResponse<DeptVO> pageQueryList(DeptPageQueryCommand deptPageQueryCommand){
		return iDeptRepresentationApplicationService.pageQuery(deptPageQueryCommand);
	}

}