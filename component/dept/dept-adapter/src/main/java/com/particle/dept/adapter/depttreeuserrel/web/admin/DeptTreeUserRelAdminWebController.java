package com.particle.dept.adapter.depttreeuserrel.web.admin;

import com.particle.dept.client.depttreeuserrel.api.IDeptTreeUserRelApplicationService;
import com.particle.dept.client.depttreeuserrel.api.representation.IDeptTreeUserRelRepresentationApplicationService;
import com.particle.dept.client.depttreeuserrel.dto.command.DeptTreeUserRelCreateCommand;
import com.particle.dept.client.depttreeuserrel.dto.data.DeptTreeUserRelVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.client.depttreeuserrel.dto.command.DeptTreeUserRelUpdateCommand;
import com.particle.dept.client.depttreeuserrel.dto.command.representation.DeptTreeUserRelPageQueryCommand;
import com.particle.dept.client.depttreeuserrel.dto.command.representation.DeptTreeUserRelQueryListCommand;
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
 * 部门树用户关系后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-12 17:28:43
 */
@Api(tags = "部门树用户关系pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/dept_tree_user_rel")
public class DeptTreeUserRelAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDeptTreeUserRelApplicationService iDeptTreeUserRelApplicationService;
	@Autowired
	private IDeptTreeUserRelRepresentationApplicationService iDeptTreeUserRelRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:deptTreeUserRel:create')")
	@ApiOperation("添加部门树用户关系")
	@PostMapping("/create")
	public SingleResponse<DeptTreeUserRelVO> create(@RequestBody DeptTreeUserRelCreateCommand deptTreeUserRelCreateCommand){
		return iDeptTreeUserRelApplicationService.create(deptTreeUserRelCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptTreeUserRel:delete')")
	@ApiOperation("删除部门树用户关系")
	@DeleteMapping("/delete")
	public SingleResponse<DeptTreeUserRelVO> delete(@RequestBody IdCommand deleteCommand){
		return iDeptTreeUserRelApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptTreeUserRel:update')")
	@ApiOperation("更新部门树用户关系")
	@PutMapping("/update")
	public SingleResponse<DeptTreeUserRelVO> update(@RequestBody DeptTreeUserRelUpdateCommand deptTreeUserRelUpdateCommand){
		return iDeptTreeUserRelApplicationService.update(deptTreeUserRelUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptTreeUserRel:update')")
	@ApiOperation("部门树用户关系更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<DeptTreeUserRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iDeptTreeUserRelRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptTreeUserRel:detail')")
	@ApiOperation("部门树用户关系详情展示")
	@GetMapping("/detail")
	public SingleResponse<DeptTreeUserRelVO> queryDetail(IdCommand detailCommand){
		return iDeptTreeUserRelRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptTreeUserRel:queryList')")
	@ApiOperation("列表查询部门树用户关系")
	@GetMapping("/list")
	public MultiResponse<DeptTreeUserRelVO> queryList(DeptTreeUserRelQueryListCommand deptTreeUserRelQueryListCommand){
		return iDeptTreeUserRelRepresentationApplicationService.queryList(deptTreeUserRelQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptTreeUserRel:pageQuery')")
	@ApiOperation("分页查询部门树用户关系")
	@GetMapping("/page")
	public PageResponse<DeptTreeUserRelVO> pageQueryList(DeptTreeUserRelPageQueryCommand deptTreeUserRelPageQueryCommand){
		return iDeptTreeUserRelRepresentationApplicationService.pageQuery(deptTreeUserRelPageQueryCommand);
	}

}