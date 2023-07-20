package com.particle.func.adapter.login;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.func.client.api.representation.IFuncRepresentationApplicationService;
import com.particle.func.client.application.api.representation.IFuncApplicationRepresentationApplicationService;
import com.particle.func.client.application.dto.command.representation.FuncApplicationQueryListCommand;
import com.particle.func.client.application.dto.data.FuncApplicationVO;
import com.particle.func.client.dto.command.representation.FuncQueryListCommand;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.func.domain.FuncTypeEnum;
import com.particle.func.domain.gateway.FuncDictGateway;
import com.particle.func.infrastructure.dos.FuncGroupDO;
import com.particle.func.infrastructure.service.IFuncGroupService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.security.login.LoginUserTool;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.javers.common.string.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
@Tag(name = "菜单功能，登录用户相关")
public class FuncLoginController {

    private static String includeTypeDictValueAll = "all";
    private static String includeTypeDictValuesDefault = StrUtil.format("{},{},{}", FuncTypeEnum.menu.itemValue(),FuncTypeEnum.page.itemValue(),FuncTypeEnum.group);

    @Autowired
    private IFuncRepresentationApplicationService iFuncRepresentationApplicationService;
    @Autowired
    private FuncDictGateway funcDictGateway;

    @Autowired
    private IFuncApplicationRepresentationApplicationService iFuncApplicationRepresentationApplicationService;
    @Autowired
    private IFuncGroupService funcGroupService;

    @Operation(summary = "当前登录用户的功能")
    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/getList")
    @ResponseStatus(HttpStatus.OK)
    public MultiResponse<FuncVO> getList(@Parameter(hidden = true) LoginUser loginUser,
                                         @Parameter(description = "包括的类型，func_type字典值，默认 menu、page、group")  @RequestParam(required = false) String includeTypeDictValues,
                                         @Parameter(description = "过虑是否展示的数据，不传忽略过虑条件") @RequestParam(required = false) Boolean isShow,
                                         @Parameter(description = "功能分组编码")  @RequestParam(required = false) String funcGroupCode) {
        Long funcGroupId = null;
        if (Strings.isNonEmpty(funcGroupCode)) {
            FuncGroupDO byCode = funcGroupService.getByCode(funcGroupCode);
            funcGroupId = byCode.getId();
        }
        if (loginUser.getIsSuperAdmin()) {
            FuncQueryListCommand funcQueryListCommand = new FuncQueryListCommand();
            funcQueryListCommand.setIsDisabled(false);
            funcQueryListCommand.setFuncGroupId(funcGroupId);
            MultiResponse<FuncVO> list = iFuncRepresentationApplicationService.queryList(funcQueryListCommand);
            filter(list,includeTypeDictValues,isShow,funcGroupId);
            return list;
        }

        List<Long> loginUserPermissionIds = LoginUserTool.getLoginUserPermissionIds();
        if (CollectionUtil.isEmpty(loginUserPermissionIds)) {
            return MultiResponse.buildSuccess();
        }
        MultiResponse<FuncVO> list = iFuncRepresentationApplicationService.queryListByIds(loginUserPermissionIds,false);
        // 排序
        if (CollectionUtil.isNotEmpty(list.getData())) {
            List<FuncVO> collect = list.getData().stream()
                    .sorted(Comparator.comparing(FuncVO::getSeq).thenComparing(FuncVO::getId)).collect(Collectors.toList());
            list.setData(collect);
        }
        // 条件过虑
        filter(list,includeTypeDictValues, isShow,funcGroupId);
        return list;
    }
    @Operation(summary = "当前登录用户的应用")
    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/getFuncApplicationList")
    @ResponseStatus(HttpStatus.OK)
    public MultiResponse<FuncApplicationVO> getFuncApplicationList(FuncApplicationQueryListCommand funcApplicationQueryListCommand, @Parameter(hidden = true) LoginUser loginUser){
        // 该方法已过滤，默认查询的是租户下的应用，如果不启用租户组件，那就是全部的应用数据
        return iFuncApplicationRepresentationApplicationService.queryList(funcApplicationQueryListCommand);

    }
    /**
     * 根据参数过滤，默认只过滤菜单、页面、分组
     * @param list
     * @param includeTypeDictValues 如果为 all 将不过滤，值为功能类型字典值，多个以逗号分隔
     * @param funcGroupId 过虑 功能分组
     */
    private void filter(MultiResponse<FuncVO> list, String includeTypeDictValues, Boolean isShow,Long funcGroupId){


        if (StrUtil.isEmpty(includeTypeDictValues)) {
            includeTypeDictValues = includeTypeDictValuesDefault;
        }

        Map<Long,String> itemsByGroupCode = funcDictGateway.getItemsByGroupCode(FuncTypeEnum.Group.func_type.groupCode());
        String finalIncludeTypeDictValues = includeTypeDictValues;
        Long finalFuncGroupId = funcGroupId;
        List<FuncVO> collect = list.getData().stream()
				.filter(item -> isShow == null || isShow.equals(item.getIsShow()))
				.filter(item -> finalFuncGroupId == null || finalFuncGroupId.equals(item.getFuncGroupId()))
				.filter(item -> {
            String value = itemsByGroupCode.get(item.getTypeDictId());
            return finalIncludeTypeDictValues.contains(value) || StrUtil.equals(finalIncludeTypeDictValues,includeTypeDictValueAll);
        }).collect(Collectors.toList());
        list.setData(collect);
    }
}
