package com.particle.crm.adapter.tag.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.crm.client.tag.api.ICrmCustomerTagApplicationService;
import com.particle.crm.client.tag.api.representation.ICrmCustomerTagRepresentationApplicationService;
import com.particle.crm.client.tag.dto.command.CrmCustomerTagCreateCommand;
import com.particle.crm.client.tag.dto.command.CrmCustomerTagUpdateCommand;
import com.particle.crm.client.tag.dto.command.representation.CrmCustomerTagPageQueryCommand;
import com.particle.crm.client.tag.dto.command.representation.CrmCustomerTagQueryListCommand;
import com.particle.crm.client.tag.dto.data.CrmCustomerTagVO;
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
 * 客户标签后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:09
 */
@Tag(name = "客户标签pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/crm_customer_tag")
public class CrmCustomerTagAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ICrmCustomerTagApplicationService iCrmCustomerTagApplicationService;
	@Autowired
	private ICrmCustomerTagRepresentationApplicationService iCrmCustomerTagRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:crmCustomerTag:create')")
	@Operation(summary = "添加客户标签")
	@PostMapping("/create")
	@OpLog(name = "添加客户标签",module = OpLogConstants.Module.crm,type = OpLogConstants.Type.create)
	public SingleResponse<CrmCustomerTagVO> create(@RequestBody CrmCustomerTagCreateCommand crmCustomerTagCreateCommand){
		return iCrmCustomerTagApplicationService.create(crmCustomerTagCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerTag:delete')")
	@Operation(summary = "删除客户标签")
	@DeleteMapping("/delete")
	@OpLog(name = "删除客户标签",module = OpLogConstants.Module.crm,type = OpLogConstants.Type.delete)
	public SingleResponse<CrmCustomerTagVO> delete(@RequestBody IdCommand deleteCommand){
		return iCrmCustomerTagApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerTag:update')")
	@Operation(summary = "更新客户标签")
	@PutMapping("/update")
	@OpLog(name = "更新客户标签",module = OpLogConstants.Module.crm,type = OpLogConstants.Type.update)
	public SingleResponse<CrmCustomerTagVO> update(@RequestBody CrmCustomerTagUpdateCommand crmCustomerTagUpdateCommand){
		return iCrmCustomerTagApplicationService.update(crmCustomerTagUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerTag:update')")
	@Operation(summary = "客户标签更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<CrmCustomerTagVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iCrmCustomerTagRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerTag:detail')")
	@Operation(summary = "客户标签详情展示")
	@GetMapping("/detail")
	public SingleResponse<CrmCustomerTagVO> queryDetail(IdCommand detailCommand){
		return iCrmCustomerTagRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerTag:queryList')")
	@Operation(summary = "列表查询客户标签")
	@GetMapping("/list")
	public MultiResponse<CrmCustomerTagVO> queryList(CrmCustomerTagQueryListCommand crmCustomerTagQueryListCommand){
		return iCrmCustomerTagRepresentationApplicationService.queryList(crmCustomerTagQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerTag:pageQuery')")
	@Operation(summary = "分页查询客户标签")
	@GetMapping("/page")
	public PageResponse<CrmCustomerTagVO> pageQueryList(CrmCustomerTagPageQueryCommand crmCustomerTagPageQueryCommand){
		return iCrmCustomerTagRepresentationApplicationService.pageQuery(crmCustomerTagPageQueryCommand);
	}

}
