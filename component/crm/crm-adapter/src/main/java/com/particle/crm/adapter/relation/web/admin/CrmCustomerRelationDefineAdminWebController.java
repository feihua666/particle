package com.particle.crm.adapter.relation.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.crm.client.relation.api.ICrmCustomerRelationDefineApplicationService;
import com.particle.crm.client.relation.api.representation.ICrmCustomerRelationDefineRepresentationApplicationService;
import com.particle.crm.client.relation.dto.command.CrmCustomerRelationDefineCreateCommand;
import com.particle.crm.client.relation.dto.command.CrmCustomerRelationDefineUpdateCommand;
import com.particle.crm.client.relation.dto.command.representation.CrmCustomerRelationDefinePageQueryCommand;
import com.particle.crm.client.relation.dto.command.representation.CrmCustomerRelationDefineQueryListCommand;
import com.particle.crm.client.relation.dto.data.CrmCustomerRelationDefineVO;
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
 * 客户关系定义后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:31:00
 */
@Tag(name = "客户关系定义pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/crm_customer_relation_define")
public class CrmCustomerRelationDefineAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ICrmCustomerRelationDefineApplicationService iCrmCustomerRelationDefineApplicationService;
	@Autowired
	private ICrmCustomerRelationDefineRepresentationApplicationService iCrmCustomerRelationDefineRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:crmCustomerRelationDefine:create')")
	@Operation(summary = "添加客户关系定义")
	@PostMapping("/create")
	@OpLog(name = "添加客户关系定义",module = OpLogConstants.Module.crm,type = OpLogConstants.Type.create)
	public SingleResponse<CrmCustomerRelationDefineVO> create(@RequestBody CrmCustomerRelationDefineCreateCommand crmCustomerRelationDefineCreateCommand){
		return iCrmCustomerRelationDefineApplicationService.create(crmCustomerRelationDefineCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerRelationDefine:delete')")
	@Operation(summary = "删除客户关系定义")
	@DeleteMapping("/delete")
	@OpLog(name = "删除客户关系定义",module = OpLogConstants.Module.crm,type = OpLogConstants.Type.delete)
	public SingleResponse<CrmCustomerRelationDefineVO> delete(@RequestBody IdCommand deleteCommand){
		return iCrmCustomerRelationDefineApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerRelationDefine:update')")
	@Operation(summary = "更新客户关系定义")
	@PutMapping("/update")
	@OpLog(name = "更新客户关系定义",module = OpLogConstants.Module.crm,type = OpLogConstants.Type.update)
	public SingleResponse<CrmCustomerRelationDefineVO> update(@RequestBody CrmCustomerRelationDefineUpdateCommand crmCustomerRelationDefineUpdateCommand){
		return iCrmCustomerRelationDefineApplicationService.update(crmCustomerRelationDefineUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerRelationDefine:update')")
	@Operation(summary = "客户关系定义更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<CrmCustomerRelationDefineVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iCrmCustomerRelationDefineRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerRelationDefine:detail')")
	@Operation(summary = "客户关系定义详情展示")
	@GetMapping("/detail")
	public SingleResponse<CrmCustomerRelationDefineVO> queryDetail(IdCommand detailCommand){
		return iCrmCustomerRelationDefineRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerRelationDefine:queryList')")
	@Operation(summary = "列表查询客户关系定义")
	@GetMapping("/list")
	public MultiResponse<CrmCustomerRelationDefineVO> queryList(CrmCustomerRelationDefineQueryListCommand crmCustomerRelationDefineQueryListCommand){
		return iCrmCustomerRelationDefineRepresentationApplicationService.queryList(crmCustomerRelationDefineQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerRelationDefine:pageQuery')")
	@Operation(summary = "分页查询客户关系定义")
	@GetMapping("/page")
	public PageResponse<CrmCustomerRelationDefineVO> pageQueryList(CrmCustomerRelationDefinePageQueryCommand crmCustomerRelationDefinePageQueryCommand){
		return iCrmCustomerRelationDefineRepresentationApplicationService.pageQuery(crmCustomerRelationDefinePageQueryCommand);
	}

}
