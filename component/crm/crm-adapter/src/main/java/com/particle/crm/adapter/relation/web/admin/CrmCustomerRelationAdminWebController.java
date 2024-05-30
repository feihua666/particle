package com.particle.crm.adapter.relation.web.admin;

import com.particle.crm.client.relation.api.ICrmCustomerRelationApplicationService;
import com.particle.crm.client.relation.api.representation.ICrmCustomerRelationRepresentationApplicationService;
import com.particle.crm.client.relation.dto.command.CrmCustomerRelationCreateCommand;
import com.particle.crm.client.relation.dto.data.CrmCustomerRelationVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.client.relation.dto.command.CrmCustomerRelationUpdateCommand;
import com.particle.crm.client.relation.dto.command.representation.CrmCustomerRelationPageQueryCommand;
import com.particle.crm.client.relation.dto.command.representation.CrmCustomerRelationQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
/**
 * <p>
 * 客户与客户关系后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:30:39
 */
@Tag(name = "客户与客户关系pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/crm_customer_relation")
public class CrmCustomerRelationAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ICrmCustomerRelationApplicationService iCrmCustomerRelationApplicationService;
	@Autowired
	private ICrmCustomerRelationRepresentationApplicationService iCrmCustomerRelationRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:crmCustomerRelation:create')")
	@Operation(summary = "添加客户与客户关系")
	@PostMapping("/create")
	@OpLog(name = "添加客户与客户关系",module = OpLogConstants.Module.crm,type = OpLogConstants.Type.create)
	public SingleResponse<CrmCustomerRelationVO> create(@RequestBody CrmCustomerRelationCreateCommand crmCustomerRelationCreateCommand){
		return iCrmCustomerRelationApplicationService.create(crmCustomerRelationCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerRelation:delete')")
	@Operation(summary = "删除客户与客户关系")
	@DeleteMapping("/delete")
	@OpLog(name = "删除客户与客户关系",module = OpLogConstants.Module.crm,type = OpLogConstants.Type.delete)
	public SingleResponse<CrmCustomerRelationVO> delete(@RequestBody IdCommand deleteCommand){
		return iCrmCustomerRelationApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerRelation:update')")
	@Operation(summary = "更新客户与客户关系")
	@PutMapping("/update")
	@OpLog(name = "更新客户与客户关系",module = OpLogConstants.Module.crm,type = OpLogConstants.Type.update)
	public SingleResponse<CrmCustomerRelationVO> update(@RequestBody CrmCustomerRelationUpdateCommand crmCustomerRelationUpdateCommand){
		return iCrmCustomerRelationApplicationService.update(crmCustomerRelationUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerRelation:update')")
	@Operation(summary = "客户与客户关系更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<CrmCustomerRelationVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iCrmCustomerRelationRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerRelation:detail')")
	@Operation(summary = "客户与客户关系详情展示")
	@GetMapping("/detail")
	public SingleResponse<CrmCustomerRelationVO> queryDetail(IdCommand detailCommand){
		return iCrmCustomerRelationRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerRelation:queryList')")
	@Operation(summary = "列表查询客户与客户关系")
	@GetMapping("/list")
	public MultiResponse<CrmCustomerRelationVO> queryList(CrmCustomerRelationQueryListCommand crmCustomerRelationQueryListCommand){
		return iCrmCustomerRelationRepresentationApplicationService.queryList(crmCustomerRelationQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerRelation:pageQuery')")
	@Operation(summary = "分页查询客户与客户关系")
	@GetMapping("/page")
	public PageResponse<CrmCustomerRelationVO> pageQueryList(CrmCustomerRelationPageQueryCommand crmCustomerRelationPageQueryCommand){
		return iCrmCustomerRelationRepresentationApplicationService.pageQuery(crmCustomerRelationPageQueryCommand);
	}

}