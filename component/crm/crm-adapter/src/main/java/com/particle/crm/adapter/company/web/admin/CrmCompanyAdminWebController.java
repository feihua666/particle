package com.particle.crm.adapter.company.web.admin;

import com.particle.crm.client.company.api.ICrmCompanyApplicationService;
import com.particle.crm.client.company.api.representation.ICrmCompanyRepresentationApplicationService;
import com.particle.crm.client.company.dto.command.CrmCompanyCreateCommand;
import com.particle.crm.client.company.dto.data.CrmCompanyVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.client.company.dto.command.CrmCompanyUpdateCommand;
import com.particle.crm.client.company.dto.command.representation.CrmCompanyPageQueryCommand;
import com.particle.crm.client.company.dto.command.representation.CrmCompanyQueryListCommand;
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
 * 客户公司后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-04-11 13:44:00
 */
@Tag(name = "客户公司pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/crm_company")
public class CrmCompanyAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ICrmCompanyApplicationService iCrmCompanyApplicationService;
	@Autowired
	private ICrmCompanyRepresentationApplicationService iCrmCompanyRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:crmCompany:create')")
	@Operation(summary = "添加客户公司")
	@PostMapping("/create")
	@OpLog(name = "添加客户公司",module = OpLogConstants.Module.crm,type = OpLogConstants.Type.create)
	public SingleResponse<CrmCompanyVO> create(@RequestBody CrmCompanyCreateCommand crmCompanyCreateCommand){
		return iCrmCompanyApplicationService.create(crmCompanyCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCompany:delete')")
	@Operation(summary = "删除客户公司")
	@DeleteMapping("/delete")
	@OpLog(name = "删除客户公司",module = OpLogConstants.Module.crm,type = OpLogConstants.Type.delete)
	public SingleResponse<CrmCompanyVO> delete(@RequestBody IdCommand deleteCommand){
		return iCrmCompanyApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCompany:update')")
	@Operation(summary = "更新客户公司")
	@PutMapping("/update")
	@OpLog(name = "更新客户公司",module = OpLogConstants.Module.crm,type = OpLogConstants.Type.update)
	public SingleResponse<CrmCompanyVO> update(@RequestBody CrmCompanyUpdateCommand crmCompanyUpdateCommand){
		return iCrmCompanyApplicationService.update(crmCompanyUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCompany:update')")
	@Operation(summary = "客户公司更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<CrmCompanyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iCrmCompanyRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCompany:detail')")
	@Operation(summary = "客户公司详情展示")
	@GetMapping("/detail")
	public SingleResponse<CrmCompanyVO> queryDetail(IdCommand detailCommand){
		return iCrmCompanyRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCompany:queryList')")
	@Operation(summary = "列表查询客户公司")
	@GetMapping("/list")
	public MultiResponse<CrmCompanyVO> queryList(CrmCompanyQueryListCommand crmCompanyQueryListCommand){
		return iCrmCompanyRepresentationApplicationService.queryList(crmCompanyQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:crmCompany:pageQuery')")
	@Operation(summary = "分页查询客户公司")
	@GetMapping("/page")
	public PageResponse<CrmCompanyVO> pageQueryList(CrmCompanyPageQueryCommand crmCompanyPageQueryCommand){
		return iCrmCompanyRepresentationApplicationService.pageQuery(crmCompanyPageQueryCommand);
	}

}