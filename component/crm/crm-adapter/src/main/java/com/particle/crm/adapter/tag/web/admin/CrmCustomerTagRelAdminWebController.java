package com.particle.crm.adapter.tag.web.admin;

import com.particle.crm.client.tag.api.ICrmCustomerTagRelApplicationService;
import com.particle.crm.client.tag.api.representation.ICrmCustomerTagRelRepresentationApplicationService;
import com.particle.crm.client.tag.dto.command.CrmCustomerTagRelCreateCommand;
import com.particle.crm.client.tag.dto.data.CrmCustomerTagRelVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.client.tag.dto.command.CrmCustomerTagRelUpdateCommand;
import com.particle.crm.client.tag.dto.command.representation.CrmCustomerTagRelPageQueryCommand;
import com.particle.crm.client.tag.dto.command.representation.CrmCustomerTagRelQueryListCommand;
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
 * 客户标签关系后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:22
 */
@Tag(name = "客户标签关系pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/crm_customer_tag_rel")
public class CrmCustomerTagRelAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ICrmCustomerTagRelApplicationService iCrmCustomerTagRelApplicationService;
	@Autowired
	private ICrmCustomerTagRelRepresentationApplicationService iCrmCustomerTagRelRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:crmCustomerTagRel:create')")
	@Operation(summary = "添加客户标签关系")
	@PostMapping("/create")
	@OpLog(name = "添加客户标签关系",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
	public SingleResponse<CrmCustomerTagRelVO> create(@RequestBody CrmCustomerTagRelCreateCommand crmCustomerTagRelCreateCommand){
		return iCrmCustomerTagRelApplicationService.create(crmCustomerTagRelCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerTagRel:delete')")
	@Operation(summary = "删除客户标签关系")
	@DeleteMapping("/delete")
	@OpLog(name = "删除客户标签关系",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
	public SingleResponse<CrmCustomerTagRelVO> delete(@RequestBody IdCommand deleteCommand){
		return iCrmCustomerTagRelApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerTagRel:update')")
	@Operation(summary = "更新客户标签关系")
	@PutMapping("/update")
	@OpLog(name = "更新客户标签关系",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
	public SingleResponse<CrmCustomerTagRelVO> update(@RequestBody CrmCustomerTagRelUpdateCommand crmCustomerTagRelUpdateCommand){
		return iCrmCustomerTagRelApplicationService.update(crmCustomerTagRelUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerTagRel:update')")
	@Operation(summary = "客户标签关系更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<CrmCustomerTagRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iCrmCustomerTagRelRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerTagRel:detail')")
	@Operation(summary = "客户标签关系详情展示")
	@GetMapping("/detail")
	public SingleResponse<CrmCustomerTagRelVO> queryDetail(IdCommand detailCommand){
		return iCrmCustomerTagRelRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerTagRel:queryList')")
	@Operation(summary = "列表查询客户标签关系")
	@GetMapping("/list")
	public MultiResponse<CrmCustomerTagRelVO> queryList(CrmCustomerTagRelQueryListCommand crmCustomerTagRelQueryListCommand){
		return iCrmCustomerTagRelRepresentationApplicationService.queryList(crmCustomerTagRelQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCustomerTagRel:pageQuery')")
	@Operation(summary = "分页查询客户标签关系")
	@GetMapping("/page")
	public PageResponse<CrmCustomerTagRelVO> pageQueryList(CrmCustomerTagRelPageQueryCommand crmCustomerTagRelPageQueryCommand){
		return iCrmCustomerTagRelRepresentationApplicationService.pageQuery(crmCustomerTagRelPageQueryCommand);
	}

}