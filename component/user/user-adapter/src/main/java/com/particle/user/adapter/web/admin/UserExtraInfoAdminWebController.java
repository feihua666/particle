package com.particle.user.adapter.web.admin;

import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.user.client.api.IUserExtraInfoApplicationService;
import com.particle.user.client.api.representation.IUserExtraInfoRepresentationApplicationService;
import com.particle.user.client.dto.command.UserExtraInfoCreateCommand;
import com.particle.user.client.dto.data.UserExtraInfoVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.user.client.dto.command.UserExtraInfoUpdateCommand;
import com.particle.user.client.dto.command.representation.UserExtraInfoPageQueryCommand;
import com.particle.user.client.dto.command.representation.UserExtraInfoQueryListCommand;
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
 * 用户扩展信息后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-08-30 23:39:50
 */
@Tag(name = "用户扩展信息pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/user_extra_info")
public class UserExtraInfoAdminWebController extends AbstractBaseWebAdapter {

    @Autowired
    private IUserExtraInfoApplicationService iUserExtraInfoApplicationService;
    @Autowired
    private IUserExtraInfoRepresentationApplicationService iUserExtraInfoRepresentationApplicationService;

    @PreAuthorize("hasAuthority('admin:web:userExtraInfo:create')")
    @Operation(summary = "添加用户扩展信息")
    @PostMapping("/create")
    @OpLog(name = "添加用户扩展信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.create)
    public SingleResponse<UserExtraInfoVO> create(@RequestBody UserExtraInfoCreateCommand userExtraInfoCreateCommand){
        return iUserExtraInfoApplicationService.create(userExtraInfoCreateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:userExtraInfo:delete')")
    @Operation(summary = "删除用户扩展信息")
    @DeleteMapping("/delete")
    @OpLog(name = "删除用户扩展信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.delete)
    public SingleResponse<UserExtraInfoVO> delete(@RequestBody IdCommand deleteCommand){
        deleteCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.delete.name());
        return iUserExtraInfoApplicationService.delete(deleteCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:userExtraInfo:update')")
    @Operation(summary = "更新用户扩展信息")
    @PutMapping("/update")
    @OpLog(name = "更新用户扩展信息",module = OpLogConstants.Module.unknown,type = OpLogConstants.Type.update)
    public SingleResponse<UserExtraInfoVO> update(@RequestBody UserExtraInfoUpdateCommand userExtraInfoUpdateCommand){
        userExtraInfoUpdateCommand.dcdo(DataConstraintConstants.data_object_null, DataConstraintContext.Action.update.name());
        return iUserExtraInfoApplicationService.update(userExtraInfoUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:userExtraInfo:update')")
    @Operation(summary = "用户扩展信息更新详情")
    @GetMapping("/detail-for-update")
    public SingleResponse<UserExtraInfoVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
        return iUserExtraInfoRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:userExtraInfo:detail')")
    @Operation(summary = "用户扩展信息详情展示")
    @GetMapping("/detail")
    public SingleResponse<UserExtraInfoVO> queryDetail(IdCommand detailCommand){
        return iUserExtraInfoRepresentationApplicationService.queryDetail(detailCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:userExtraInfo:queryList')")
    @Operation(summary = "列表查询用户扩展信息")
    @GetMapping("/list")
    public MultiResponse<UserExtraInfoVO> queryList(UserExtraInfoQueryListCommand userExtraInfoQueryListCommand){
        userExtraInfoQueryListCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iUserExtraInfoRepresentationApplicationService.queryList(userExtraInfoQueryListCommand);
    }

    @PreAuthorize("hasAuthority('admin:web:userExtraInfo:pageQuery')")
    @Operation(summary = "分页查询用户扩展信息")
    @GetMapping("/page")
    public PageResponse<UserExtraInfoVO> pageQueryList(UserExtraInfoPageQueryCommand userExtraInfoPageQueryCommand){
        userExtraInfoPageQueryCommand.dcdo(DataConstraintConstants.data_object_null,DataConstraintContext.Action.query.name());
        return iUserExtraInfoRepresentationApplicationService.pageQuery(userExtraInfoPageQueryCommand);
    }
}