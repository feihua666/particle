package com.particle.dept.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.dept.client.api.IDeptTreeApplicationService;
import com.particle.dept.client.api.representation.IDeptTreeRepresentationApplicationService;
import com.particle.dept.client.dto.command.DeptTreeCreateCommand;
import com.particle.dept.client.dto.command.DeptTreeUpdateCommand;
import com.particle.dept.client.dto.command.representation.DeptTreePageQueryCommand;
import com.particle.dept.client.dto.command.representation.DeptTreeQueryListCommand;
import com.particle.dept.client.dto.data.DeptTreeVO;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 部门树后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
@Tag(name = "部门树pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/dept_tree")
public class DeptTreeAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDeptTreeApplicationService iDeptTreeApplicationService;
	@Autowired
	private IDeptTreeRepresentationApplicationService iDeptTreeRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:deptTree:create')")
	@Operation(summary = "添加部门树")
	@PostMapping("/create")
	@OpLog(name = "添加部门树",module = OpLogConstants.Module.dept,type = OpLogConstants.Type.create)
	public SingleResponse<DeptTreeVO> create(@RequestBody DeptTreeCreateCommand deptTreeCreateCommand){
		return iDeptTreeApplicationService.create(deptTreeCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptTree:delete')")
	@Operation(summary = "删除部门树")
	@DeleteMapping("/delete")
	@OpLog(name = "删除部门树",module = OpLogConstants.Module.dept,type = OpLogConstants.Type.delete)
	public SingleResponse<DeptTreeVO> delete(@RequestBody IdCommand deleteCommand){
		return iDeptTreeApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptTree:update')")
	@Operation(summary = "更新部门树")
	@PutMapping("/update")
	@OpLog(name = "更新部门树",module = OpLogConstants.Module.dept,type = OpLogConstants.Type.update)
	public SingleResponse<DeptTreeVO> update(@RequestBody DeptTreeUpdateCommand deptTreeUpdateCommand){
		return iDeptTreeApplicationService.update(deptTreeUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptTree:update')")
	@Operation(summary = "部门树更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<DeptTreeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iDeptTreeRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptTree:detail')")
	@Operation(summary = "部门树详情展示")
	@GetMapping("/detail")
	public SingleResponse<DeptTreeVO> queryDetail(IdCommand detailCommand){
		return iDeptTreeRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptTree:queryList')")
	@Operation(summary = "列表查询部门树")
	@GetMapping("/list")
	public MultiResponse<DeptTreeVO> queryList(DeptTreeQueryListCommand deptTreeQueryListCommand){
		return iDeptTreeRepresentationApplicationService.queryList(deptTreeQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:deptTree:pageQuery')")
	@Operation(summary = "分页查询部门树")
	@GetMapping("/page")
	public PageResponse<DeptTreeVO> pageQueryList(DeptTreePageQueryCommand deptTreePageQueryCommand){
		return iDeptTreeRepresentationApplicationService.pageQuery(deptTreePageQueryCommand);
	}

}
