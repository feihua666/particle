package com.particle.component.adapter.func.login;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.func.adapter.login.FuncLoginService;
import com.particle.func.infrastructure.dos.FuncDO;
import com.particle.func.infrastructure.service.IFuncService;
import com.particle.role.infrastructure.dos.RoleDO;
import com.particle.role.infrastructure.rolefuncrel.dos.RoleFuncRelDO;
import com.particle.role.infrastructure.rolefuncrel.service.IRoleFuncRelService;
import com.particle.role.infrastructure.service.IRoleService;
import com.particle.user.adapter.login.UserAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 获取用户功能
 * Created by yangwei
 * Created at 2020/12/11 18:16
 */
public class UserFuncServiceImpl implements FuncLoginService {
    @Autowired
    private IRoleService iRoleService;

    @Autowired
    private IFuncService iFuncService;

    @Autowired
    private IRoleFuncRelService iRoleFuncRelService;

    @Override
    public List<FuncDO> retrieveFuncsByUserId(Long userId) {
        List<RoleDO> byUserId = iRoleService.getByUserId(userId,false);
        if (CollectionUtil.isEmpty(byUserId)) {
            return Collections.emptyList();
        }
        List<Long> roleIds = byUserId.stream().map(RoleDO::getId).collect(Collectors.toList());
        List<RoleFuncRelDO> byRoleIds = iRoleFuncRelService.listByColumns(roleIds,RoleFuncRelDO::getRoleId);
        if (CollectionUtil.isEmpty(byRoleIds)) {
            return Collections.emptyList();
        }
        List<Long> funcIds = byRoleIds.stream().map(RoleFuncRelDO::getRoleId).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(funcIds)) {
            return Collections.emptyList();
        }
        return iFuncService.list(Wrappers.<FuncDO>lambdaQuery().in(FuncDO::getId, funcIds).eq(FuncDO::getIsDisabled, false));
    }
}
