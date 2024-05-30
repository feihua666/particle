package com.particle.crm.adapter.customer.web.admin;

import com.particle.crm.client.customer.api.ICrmCustomerContactApplicationService;
import com.particle.crm.client.customer.api.representation.ICrmCustomerContactRepresentationApplicationService;
import com.particle.crm.client.customer.dto.command.CrmCustomerContactCreateCommand;
import com.particle.crm.client.customer.dto.data.CrmCustomerContactVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.client.customer.dto.command.CrmCustomerContactUpdateCommand;
import com.particle.crm.client.customer.dto.command.representation.CrmCustomerContactPageQueryCommand;
import com.particle.crm.client.customer.dto.command.representation.CrmCustomerContactQueryListCommand;
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
 * 客户联系方式后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:27:56
 */
@Tag(name = "客户联系方式pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/crm_customer_contact")
public class CrmCustomerContactAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ICrmCustomerContactApplicationService iCrmCustomerContactApplicationService;
	@Autowired
	private ICrmCustomerContactRepresentationApplicationService iCrmCustomerContactRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:crmCustomerContact:create')")
	@Operation(summary = "添加客户联系方式")
	@PostMapping("/create")
	@OpLog(name = "添加客户联系方式",module = OpLogConstants.Module.crm,type = OpLogConstants.Type.create)
	public SingleResponse<CrmCustomerContactVO> create(@RequestBody CrmCustomerContactCreateCommand crmCustomerContactCreateCommand){
		return iCrmCustomerContactApplicationService.create(crmCustomerContactCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerContact:delete')")
	@Operation(summary = "删除客户联系方式")
	@DeleteMapping("/delete")
	@OpLog(name = "删除客户联系方式",module = OpLogConstants.Module.crm,type = OpLogConstants.Type.delete)
	public SingleResponse<CrmCustomerContactVO> delete(@RequestBody IdCommand deleteCommand){
		return iCrmCustomerContactApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerContact:update')")
	@Operation(summary = "更新客户联系方式")
	@PutMapping("/update")
	@OpLog(name = "更新客户联系方式",module = OpLogConstants.Module.crm,type = OpLogConstants.Type.update)
	public SingleResponse<CrmCustomerContactVO> update(@RequestBody CrmCustomerContactUpdateCommand crmCustomerContactUpdateCommand){
		return iCrmCustomerContactApplicationService.update(crmCustomerContactUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerContact:update')")
	@Operation(summary = "客户联系方式更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<CrmCustomerContactVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iCrmCustomerContactRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerContact:detail')")
	@Operation(summary = "客户联系方式详情展示")
	@GetMapping("/detail")
	public SingleResponse<CrmCustomerContactVO> queryDetail(IdCommand detailCommand){
		return iCrmCustomerContactRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerContact:queryList')")
	@Operation(summary = "列表查询客户联系方式")
	@GetMapping("/list")
	public MultiResponse<CrmCustomerContactVO> queryList(CrmCustomerContactQueryListCommand crmCustomerContactQueryListCommand){
		return iCrmCustomerContactRepresentationApplicationService.queryList(crmCustomerContactQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerContact:pageQuery')")
	@Operation(summary = "分页查询客户联系方式")
	@GetMapping("/page")
	public PageResponse<CrmCustomerContactVO> pageQueryList(CrmCustomerContactPageQueryCommand crmCustomerContactPageQueryCommand){
		return iCrmCustomerContactRepresentationApplicationService.pageQuery(crmCustomerContactPageQueryCommand);
	}

}