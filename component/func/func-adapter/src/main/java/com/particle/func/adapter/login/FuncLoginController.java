package com.particle.func.adapter.login;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.func.client.api.representation.IFuncRepresentationApplicationService;
import com.particle.func.client.dto.command.representation.FuncQueryListCommand;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.func.infrastructure.service.IFuncService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.security.login.LoginUserTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 菜单功能表 前端控制器 登录用户相关
 * </p>
 *
 * @author yw
 * @since 2020-12-07
 */
@RestController
@RequestMapping("/func/login")
@Api(tags = "菜单功能，登录用户相关")
public class FuncLoginController {


    @Autowired
    private IFuncRepresentationApplicationService iFuncRepresentationApplicationService;

    @ApiOperation("当前登录用户的功能")
    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/getList")
    @ResponseStatus(HttpStatus.OK)
    public MultiResponse<FuncVO> getList(@ApiIgnore LoginUser loginUser) {
        if (loginUser.getIsSuperAdmin()) {
            FuncQueryListCommand funcQueryListCommand = new FuncQueryListCommand();
            funcQueryListCommand.setIsDisabled(false);
            MultiResponse<FuncVO> list = iFuncRepresentationApplicationService.queryList(funcQueryListCommand);
            return list;
        }

        List<Long> loginUserPermissionIds = LoginUserTool.getLoginUserPermissionIds();
        if (CollectionUtil.isEmpty(loginUserPermissionIds)) {
            return MultiResponse.buildSuccess();
        }
        MultiResponse<FuncVO> list = iFuncRepresentationApplicationService.queryListByIds(loginUserPermissionIds,false);
        return list;
    }

}
