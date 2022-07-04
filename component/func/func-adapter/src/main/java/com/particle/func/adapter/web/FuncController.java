package com.particle.func.adapter.web;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.func.client.api.FuncApplicationService;
import com.particle.func.client.dto.command.CreateFuncCommand;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单适配器
 * @program: particle
 * @description:
 * @author: 许宝华
 * @create: 2022-07-02 11:52
 */


@Api(tags = "菜单相关接口")
@RestController
@RequestMapping("/func")
public class FuncController extends AbstractBaseWebAdapter {

    @Autowired
    private FuncApplicationService funcApplicationService;


//    @PreAuthorize("hasAuthority('admin:area:create')")
    @PostMapping("/create")
    public SingleResponse<FuncVO> create(@RequestBody CreateFuncCommand createFuncCommand){
        return funcApplicationService.create(createFuncCommand);
    }
}
