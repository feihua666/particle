package com.particle.crm.adapter.customer.web.admin;

import com.particle.crm.client.customer.api.ICrmCustomerApplicationService;
import com.particle.crm.client.customer.api.representation.ICrmCustomerRepresentationApplicationService;
import com.particle.crm.client.customer.dto.command.CrmCustomerCreateCommand;
import com.particle.crm.client.customer.dto.data.CrmCustomerVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.client.customer.dto.command.CrmCustomerUpdateCommand;
import com.particle.crm.client.customer.dto.command.representation.CrmCustomerPageQueryCommand;
import com.particle.crm.client.customer.dto.command.representation.CrmCustomerQueryListCommand;
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
 * 客户后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:21:36
 */
@Tag(name = "客户pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/crm_customer")
public class CrmCustomerAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ICrmCustomerApplicationService iCrmCustomerApplicationService;
	@Autowired
	private ICrmCustomerRepresentationApplicationService iCrmCustomerRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:crmCustomer:create')")
	@Operation(summary = "添加客户")
	@PostMapping("/create")
	@OpLog(name = "添加客户",module = OpLogConstants.Module.crm,type = OpLogConstants.Type.create)
	public SingleResponse<CrmCustomerVO> create(@RequestBody CrmCustomerCreateCommand crmCustomerCreateCommand){
		return iCrmCustomerApplicationService.create(crmCustomerCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomer:delete')")
	@Operation(summary = "删除客户")
	@DeleteMapping("/delete")
	@OpLog(name = "删除客户",module = OpLogConstants.Module.crm,type = OpLogConstants.Type.delete)
	public SingleResponse<CrmCustomerVO> delete(@RequestBody IdCommand deleteCommand){
		return iCrmCustomerApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomer:update')")
	@Operation(summary = "更新客户")
	@PutMapping("/update")
	@OpLog(name = "更新客户",module = OpLogConstants.Module.crm,type = OpLogConstants.Type.update)
	public SingleResponse<CrmCustomerVO> update(@RequestBody CrmCustomerUpdateCommand crmCustomerUpdateCommand){
		return iCrmCustomerApplicationService.update(crmCustomerUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomer:update')")
	@Operation(summary = "客户更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<CrmCustomerVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iCrmCustomerRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomer:detail')")
	@Operation(summary = "客户详情展示")
	@GetMapping("/detail")
	public SingleResponse<CrmCustomerVO> queryDetail(IdCommand detailCommand){
		return iCrmCustomerRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomer:queryList')")
	@Operation(summary = "列表查询客户")
	@GetMapping("/list")
	public MultiResponse<CrmCustomerVO> queryList(CrmCustomerQueryListCommand crmCustomerQueryListCommand){
		return iCrmCustomerRepresentationApplicationService.queryList(crmCustomerQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomer:pageQuery')")
	@Operation(summary = "分页查询客户")
	@GetMapping("/page")
	public PageResponse<CrmCustomerVO> pageQueryList(CrmCustomerPageQueryCommand crmCustomerPageQueryCommand){
		return iCrmCustomerRepresentationApplicationService.pageQuery(crmCustomerPageQueryCommand);
	}

}