package com.particle.crm.adapter.company.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.crm.client.company.api.ICrmDeptApplicationService;
import com.particle.crm.client.company.api.representation.ICrmDeptRepresentationApplicationService;
import com.particle.crm.client.company.dto.command.CrmDeptCreateCommand;
import com.particle.crm.client.company.dto.command.CrmDeptUpdateCommand;
import com.particle.crm.client.company.dto.command.representation.CrmDeptPageQueryCommand;
import com.particle.crm.client.company.dto.command.representation.CrmDeptQueryListCommand;
import com.particle.crm.client.company.dto.data.CrmDeptVO;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
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
 * 客户公司部门后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-04-24 10:16:52
 */
@Tag(name = "客户公司部门pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/crm_dept")
public class CrmDeptAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ICrmDeptApplicationService iCrmDeptApplicationService;
	@Autowired
	private ICrmDeptRepresentationApplicationService iCrmDeptRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:crmDept:create')")
	@Operation(summary = "添加客户公司部门")
	@PostMapping("/create")
	@OpLog(name = "添加客户公司部门",module = OpLogConstants.Module.crm,type = OpLogConstants.Type.create)
	public SingleResponse<CrmDeptVO> create(@RequestBody CrmDeptCreateCommand crmDeptCreateCommand){
		return iCrmDeptApplicationService.create(crmDeptCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmDept:delete')")
	@Operation(summary = "删除客户公司部门")
	@DeleteMapping("/delete")
	@OpLog(name = "删除客户公司部门",module = OpLogConstants.Module.crm,type = OpLogConstants.Type.delete)
	public SingleResponse<CrmDeptVO> delete(@RequestBody IdCommand deleteCommand){
		deleteCommand.dcdo(DataConstraintConstants.data_object_crm_dept, DataConstraintContext.Action.delete.name());
		return iCrmDeptApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmDept:update')")
	@Operation(summary = "更新客户公司部门")
	@PutMapping("/update")
	@OpLog(name = "更新客户公司部门",module = OpLogConstants.Module.crm,type = OpLogConstants.Type.update)
	public SingleResponse<CrmDeptVO> update(@RequestBody CrmDeptUpdateCommand crmDeptUpdateCommand){
		crmDeptUpdateCommand.dcdo(DataConstraintConstants.data_object_crm_dept,DataConstraintContext.Action.update.name());
		return iCrmDeptApplicationService.update(crmDeptUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmDept:update')")
	@Operation(summary = "客户公司部门更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<CrmDeptVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iCrmDeptRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmDept:detail')")
	@Operation(summary = "客户公司部门详情展示")
	@GetMapping("/detail")
	public SingleResponse<CrmDeptVO> queryDetail(IdCommand detailCommand){
		return iCrmDeptRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmDept:queryList')")
	@Operation(summary = "列表查询客户公司部门")
	@GetMapping("/list")
	public MultiResponse<CrmDeptVO> queryList(CrmDeptQueryListCommand crmDeptQueryListCommand){
		crmDeptQueryListCommand.dcdo(DataConstraintConstants.data_object_crm_dept,DataConstraintContext.Action.query.name());
		return iCrmDeptRepresentationApplicationService.queryList(crmDeptQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmDept:pageQuery')")
	@Operation(summary = "分页查询客户公司部门")
	@GetMapping("/page")
	public PageResponse<CrmDeptVO> pageQueryList(CrmDeptPageQueryCommand crmDeptPageQueryCommand){
		crmDeptPageQueryCommand.dcdo(DataConstraintConstants.data_object_crm_dept,DataConstraintContext.Action.query.name());
		return iCrmDeptRepresentationApplicationService.pageQuery(crmDeptPageQueryCommand);
	}

}
