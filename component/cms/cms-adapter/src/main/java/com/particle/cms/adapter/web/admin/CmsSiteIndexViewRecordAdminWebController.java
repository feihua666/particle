package com.particle.cms.adapter.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.cms.client.api.ICmsSiteIndexViewRecordApplicationService;
import com.particle.cms.client.api.representation.ICmsSiteIndexViewRecordRepresentationApplicationService;
import com.particle.cms.client.dto.command.CmsSiteIndexViewRecordCreateCommand;
import com.particle.cms.client.dto.data.CmsSiteIndexViewRecordVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.cms.client.dto.command.CmsSiteIndexViewRecordUpdateCommand;
import com.particle.cms.client.dto.command.representation.CmsSiteIndexViewRecordPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsSiteIndexViewRecordQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
/**
 * <p>
 * 站点首页访问记录后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:10
 */
@Tag(name = "站点首页访问记录pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/cms_site_index_view_record")
public class CmsSiteIndexViewRecordAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private ICmsSiteIndexViewRecordApplicationService iCmsSiteIndexViewRecordApplicationService;
    @Autowired
    private ICmsSiteIndexViewRecordRepresentationApplicationService iCmsSiteIndexViewRecordRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:cmsSiteIndexViewRecord:create')")
    @Operation(summary = "添加站点首页访问记录")
    @PostMapping("/create")
    @OpLog(name = "添加站点首页访问记录",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.create)
    public SingleResponse<CmsSiteIndexViewRecordVO> create(@RequestBody CmsSiteIndexViewRecordCreateCommand cmsSiteIndexViewRecordCreateCommand){
        return iCmsSiteIndexViewRecordApplicationService.create(cmsSiteIndexViewRecordCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsSiteIndexViewRecord:delete')")
    @Operation(summary = "删除站点首页访问记录")
    @DeleteMapping("/delete")
    @OpLog(name = "删除站点首页访问记录",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.delete)
    public SingleResponse<CmsSiteIndexViewRecordVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iCmsSiteIndexViewRecordApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsSiteIndexViewRecord:update')")
    @Operation(summary = "更新站点首页访问记录")
    @PutMapping("/update")
    @OpLog(name = "更新站点首页访问记录",module = OpLogConstants.Module.cms,type = OpLogConstants.Type.update)
    public SingleResponse<CmsSiteIndexViewRecordVO> update(@RequestBody CmsSiteIndexViewRecordUpdateCommand cmsSiteIndexViewRecordUpdateCommand){
        cmsSiteIndexViewRecordUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iCmsSiteIndexViewRecordApplicationService.update(cmsSiteIndexViewRecordUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsSiteIndexViewRecord:update')")
    @Operation(summary = "站点首页访问记录更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<CmsSiteIndexViewRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iCmsSiteIndexViewRecordRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsSiteIndexViewRecord:detail')")
    @Operation(summary = "站点首页访问记录详情展示")
    @GetMapping("/detail")
    public SingleResponse<CmsSiteIndexViewRecordVO> queryDetail(IdCommand detailCommand){
        return iCmsSiteIndexViewRecordRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsSiteIndexViewRecord:queryList')")
    @Operation(summary = "列表查询站点首页访问记录")
    @GetMapping("/list")
    public MultiResponse<CmsSiteIndexViewRecordVO> queryList(CmsSiteIndexViewRecordQueryListCommand cmsSiteIndexViewRecordQueryListCommand){
        cmsSiteIndexViewRecordQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCmsSiteIndexViewRecordRepresentationApplicationService.queryList(cmsSiteIndexViewRecordQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:cmsSiteIndexViewRecord:pageQuery')")
    @Operation(summary = "分页查询站点首页访问记录")
    @GetMapping("/page")
    public PageResponse<CmsSiteIndexViewRecordVO> pageQueryList(CmsSiteIndexViewRecordPageQueryCommand cmsSiteIndexViewRecordPageQueryCommand){
        cmsSiteIndexViewRecordPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iCmsSiteIndexViewRecordRepresentationApplicationService.pageQuery(cmsSiteIndexViewRecordPageQueryCommand);
    }
}
