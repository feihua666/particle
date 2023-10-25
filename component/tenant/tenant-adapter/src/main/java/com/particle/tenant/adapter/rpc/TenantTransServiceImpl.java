package com.particle.tenant.adapter.rpc;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import com.particle.tenant.client.dto.data.TenantTransVO;
import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.tenant.infrastructure.mapper.TenantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 租户管理租户翻译实现
 * </p>
 *
 * @author yw
 * @since 2023-10-20 17:03:54
 */
@Component
public class TenantTransServiceImpl implements ITransService<TenantTransVO,Long> {

    /**
     * 这里使用 mapper 否则有循环依赖问题
     */
    @Autowired
    private TenantMapper tenantMapper;
    @Override
    public boolean support(String type) {
        return StrUtil.containsAny(type, TransConstants.TRANS_TENANT_BY_ID);
    }

    @Override
    public TransResult<TenantTransVO, Long> trans(String type, Long key) {
        if (StrUtil.containsAny(type, TransConstants.TRANS_TENANT_BY_ID)) {
            TenantDO byId = tenantMapper.selectById(key);
            if (byId == null) {
                return null;
            }
            return new TransResult(tenantMapTenantForTrans(byId),key);
        }
        return null;
    }

    @Override
    public boolean supportBatch(String type) {
        return support(type);
    }

    @Override
    public List<TransResult<TenantTransVO, Long>> transBatch(String type, Set<Long> keys) {
        List<TenantDO> tenantDOS = tenantMapper.selectBatchIds(keys);
        if (CollectionUtil.isEmpty(tenantDOS)) {
            return null;
        }
        if (StrUtil.containsAny(type,TransConstants.TRANS_TENANT_BY_ID)) {
            return tenantDOS.stream().map(item->new TransResult<TenantTransVO, Long>(tenantMapTenantForTrans(item),item.getId())).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * 转换实体
     * @param tenant
     * @return
     */
    private TenantTransVO tenantMapTenantForTrans(TenantDO tenant){
        TenantTransVO tenantForTrans = new TenantTransVO();
        tenantForTrans.setId(tenant.getId());

        tenantForTrans.setCode(tenant.getCode());
        tenantForTrans.setName(tenant.getName());
        return tenantForTrans;
    }


}
