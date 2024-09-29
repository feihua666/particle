package com.particle.openplatform.adapter.openapi.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.global.dto.basic.Command;
import com.particle.global.security.security.login.LoginUser;
import com.particle.openplatform.client.openapi.api.IOpenplatformOpenapiBatchQueryRecordApplicationService;
import com.particle.openplatform.client.openapi.api.representation.IOpenplatformOpenapiBatchQueryRecordRepresentationApplicationService;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiBatchQueryRecordCreateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiBatchQueryRecordVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiBatchQueryRecordUpdateCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiBatchQueryRecordPageQueryCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiBatchQueryRecordQueryListCommand;
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
 * 开放接口批量查询记录后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:44:36
 */
@Tag(name = "开放接口批量查询记录pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/openplatform_openapi_batch_query_record")
public class OpenplatformOpenapiBatchQueryRecordAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IOpenplatformOpenapiBatchQueryRecordApplicationService iOpenplatformOpenapiBatchQueryRecordApplicationService;
    @Autowired
    private IOpenplatformOpenapiBatchQueryRecordRepresentationApplicationService iOpenplatformOpenapiBatchQueryRecordRepresentationApplicationService;


    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiBatchQueryRecord:delete')")
    @Operation(summary = "删除开放接口批量查询记录")
    @DeleteMapping("/delete")
    @OpLog(name = "删除开放接口批量查询记录",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<OpenplatformOpenapiBatchQueryRecordVO> delete(@RequestBody IdCommand deleteCommand, LoginUser loginUser){
        fillLoginUserIdIfNessary(deleteCommand, loginUser);
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iOpenplatformOpenapiBatchQueryRecordApplicationService.delete(deleteCommand);
    }


    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiBatchQueryRecord:detail')")
    @Operation(summary = "开放接口批量查询记录详情展示")
    @GetMapping("/detail")
    public SingleResponse<OpenplatformOpenapiBatchQueryRecordVO> queryDetail(IdCommand detailCommand, LoginUser loginUser){
        fillLoginUserIdIfNessary(detailCommand, loginUser);
        return iOpenplatformOpenapiBatchQueryRecordRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiBatchQueryRecord:queryList')")
    @Operation(summary = "列表查询开放接口批量查询记录")
    @GetMapping("/list")
    public MultiResponse<OpenplatformOpenapiBatchQueryRecordVO> queryList(OpenplatformOpenapiBatchQueryRecordQueryListCommand openplatformOpenapiBatchQueryRecordQueryListCommand, LoginUser loginUser){
        fillLoginUserIdIfNessary(openplatformOpenapiBatchQueryRecordQueryListCommand, loginUser);
        openplatformOpenapiBatchQueryRecordQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformOpenapiBatchQueryRecordRepresentationApplicationService.queryList(openplatformOpenapiBatchQueryRecordQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:openplatformOpenapiBatchQueryRecord:pageQuery')")
    @Operation(summary = "分页查询开放接口批量查询记录")
    @GetMapping("/page")
    public PageResponse<OpenplatformOpenapiBatchQueryRecordVO> pageQueryList(OpenplatformOpenapiBatchQueryRecordPageQueryCommand openplatformOpenapiBatchQueryRecordPageQueryCommand, LoginUser loginUser){
        fillLoginUserIdIfNessary(openplatformOpenapiBatchQueryRecordPageQueryCommand, loginUser);
        openplatformOpenapiBatchQueryRecordPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iOpenplatformOpenapiBatchQueryRecordRepresentationApplicationService.pageQuery(openplatformOpenapiBatchQueryRecordPageQueryCommand);
    }

    /**
     * 主要是限制用户只能查看自己的数据
     * @param command
     * @param loginUser
     */
    private void fillLoginUserIdIfNessary(Command command, LoginUser loginUser) {
        if (!loginUser.getIsSuperAdmin()) {
            command.setLoginUserId(loginUser.getId());
        }
    }
}