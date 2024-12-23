package com.particle.role.adapter.rpc;

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

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 翻译角色实现
 * @author  yangwei
 * Created at 2023-05-26 13:40:03
 */
@Primary
@Component
public class RoleTransServiceImpl implements ITransService<RoleTransVO,Long> {

    @Autowired
    private IRoleService iRoleService;

    @Override
    public boolean support(String type) {
        return StrUtil.containsAny(type, TransConstants.TRANS_ROLE_BY_ID);
    }

    @Override
    public TransResult<RoleTransVO, Long> trans(String type, Long key) {
        if (StrUtil.containsAny(type,TransConstants.TRANS_ROLE_BY_ID)) {
            RoleDO roleDO = iRoleService.getById(key);
            if (roleDO == null) {
                return null;
            }
            return new TransResult(newRoleTransVO(roleDO),key);
        }
        return null;
    }

    @Override
    public boolean supportBatch(String type) {
        return support(type);
    }

    @Override
    public List<TransResult<RoleTransVO, Long>> transBatch(String type, Set<Long> keys) {
        if (StrUtil.containsAny(type,TransConstants.TRANS_ROLE_BY_ID)) {
            List<RoleDO> roleDOS = iRoleService.listByIds(keys);
            return roleDOS.stream()
                    .map(item->new TransResult<RoleTransVO, Long>(newRoleTransVO(item),item.getId()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    protected static RoleTransVO newRoleTransVO(RoleDO roleDO){
        RoleTransVO roleTransVO = new RoleTransVO();
        roleTransVO.setId(roleDO.getId());
        roleTransVO.setName(roleDO.getName());

        return roleTransVO;
    }

}
