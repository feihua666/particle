package com.particle.role.adapter.rpc;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import com.particle.role.client.dto.data.RoleTransVO;
import com.particle.role.infrastructure.dos.RoleDO;
import com.particle.role.infrastructure.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 翻译角色实现
 * 主要是根据用户id翻译多个
 * @author  yangwei
 * Created at 2023-05-26 13:40:03
 */
@Primary
@Component
public class RoleMultipleTransServiceImpl implements ITransService<List<RoleTransVO>,Long> {

    @Autowired
    private IRoleService iRoleService;

    @Override
    public boolean support(String type) {
        return StrUtil.containsAny(type, TransConstants.TRANS_ROLE_BY_USER_ID);
    }

    @Override
    public TransResult<List<RoleTransVO>, Long> trans(String type, Long key) {
        if (StrUtil.equals(type,TransConstants.TRANS_ROLE_BY_USER_ID)) {
            List<RoleDO> roleDOS = iRoleService.getByUserId(key, null);
            if (CollectionUtil.isEmpty(roleDOS)) {
                return null;
            }
            return new TransResult(newRoleTransVOs(roleDOS),key);
        }
        return null;
    }

    @Override
    public boolean supportBatch(String type) {
        return support(type);
    }

    @Override
    public List<TransResult<List<RoleTransVO>, Long>> transBatch(String type, Set<Long> keys) {
        if (StrUtil.containsAny(type,TransConstants.TRANS_ROLE_BY_USER_ID)) {
            Map<Long, List<RoleDO>> byUserIds = iRoleService.getByUserIds(new ArrayList<>(keys), null);
            if (CollectionUtil.isNotEmpty(byUserIds)) {
                Set<Long> userIds = byUserIds.keySet();
                List<TransResult<List<RoleTransVO>, Long>> result = new ArrayList<>(userIds.size());
                for (Long userId : userIds) {
                    result.add(new TransResult(newRoleTransVOs(byUserIds.get(userId)),userId));
                }
                return result;
            }
        }
        return null;
    }


    private List<RoleTransVO> newRoleTransVOs(List<RoleDO> roleDOs){
        if (CollectionUtil.isEmpty(roleDOs)) {
            return Collections.emptyList();
        }
        List<RoleTransVO> roleTransVOS = roleDOs.stream().map(item -> RoleTransServiceImpl.newRoleTransVO(item)).collect(Collectors.toList());
        return roleTransVOS;
    }
}
