package com.particle.tenant.infrastructure.service.impl;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.plugins.IgnoreStrategy;
import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.tenant.infrastructure.mapper.TenantMapper;
import com.particle.tenant.infrastructure.service.ITenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * <p>
 * 租户 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@Component
public class TenantServiceImpl extends IBaseServiceImpl<TenantMapper, TenantDO> implements ITenantService {
	private IBaseQueryCommandMapStruct<TenantDO> queryCommandMapStruct;

	@Override
	protected TenantDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<TenantDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
    /**
     * 缓存，参数为0表示不限制缓存时间
     * 缓存时间31分钟
     */
    TimedCache<String, List<TenantDO>> timedCache = CacheUtil.newTimedCache(31 * 60 * 1000);

	@Override
	protected void preAdd(TenantDO po) {
	    // 租户编码 已存在不能添加
	    assertByColumn(po.getCode(),TenantDO::getCode,false);
		if (StrUtil.isNotEmpty(po.getTenantDomain())) {
			assertByColumn(po.getTenantDomain(),TenantDO::getTenantDomain,false);
		}
	}

    @Override
    protected void postAdd(TenantDO po) {
        refreshCacheForGetAllTenant();
    }

    @Override
	protected void preUpdate(TenantDO po) {

	    TenantDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果租户编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 租户编码已存在不能修改
	            assertByColumn(po.getCode(),TenantDO::getCode,false);
	        }
	    }
		if (StrUtil.isNotEmpty(po.getTenantDomain())) {
			byId = byId == null ? getById(po.getId()) : byId;
			// 如果租户 domain 有改动
			if (!po.getTenantDomain().equals(byId.getTenantDomain())) {
				// 租户 domain 已存在不能修改
				assertByColumn(po.getTenantDomain(),TenantDO::getTenantDomain,false);
			}
		}

	}

    @Override
    protected void postDeleteById(Long id, TenantDO DO) {
        refreshCacheForGetAllTenant();
    }

    @Override
    protected void postDeleteByColumn(Object columnId, SFunction<TenantDO, ?> column, List<TenantDO> DOS) {
        refreshCacheForGetAllTenant();
    }

    @Override
    protected void postUpdate(TenantDO po) {
        refreshCacheForGetAllTenant();
    }

    @Override
    public List<TenantDO> getAllIgnoreTenantLimit() {
        return doGetAllIgnoreTenantLimit(false);
    }

    private  List<TenantDO> doGetAllIgnoreTenantLimit(boolean isRefreshCache) {
        List<TenantDO> getAllIgnoreTenantLimit = null;
        if (!isRefreshCache) {
            getAllIgnoreTenantLimit = timedCache.get("getAllIgnoreTenantLimit");
            if (getAllIgnoreTenantLimit != null) {
                return getAllIgnoreTenantLimit;
            }
        }
        try {
            // 设置忽略租户插件
            InterceptorIgnoreHelper.handle(IgnoreStrategy.builder().tenantLine(true).dataPermission(true).build());
            getAllIgnoreTenantLimit =  list();
            timedCache.put("getAllIgnoreTenantLimit",getAllIgnoreTenantLimit);
            return getAllIgnoreTenantLimit;
        } finally {
            InterceptorIgnoreHelper.clearIgnoreStrategy();
        }
    }

    @Override
    public List<TenantDO> getAllSimpleIgnoreTenantLimit() {
        return doGetAllSimpleIgnoreTenantLimit(false);
    }
    private List<TenantDO> doGetAllSimpleIgnoreTenantLimit(boolean isRefreshCache) {
        List<TenantDO> getAllSimpleIgnoreTenantLimit = null;
        if (!isRefreshCache) {
            getAllSimpleIgnoreTenantLimit = timedCache.get("getAllSimpleIgnoreTenantLimit");
            if (getAllSimpleIgnoreTenantLimit != null) {
                return getAllSimpleIgnoreTenantLimit;
            }
        }
        try {
            // 设置忽略租户插件
            InterceptorIgnoreHelper.handle(IgnoreStrategy.builder().tenantLine(true).dataPermission(true).build());
            getAllSimpleIgnoreTenantLimit = list(Wrappers.<TenantDO>lambdaQuery().select(TenantDO::getId,TenantDO::getCode,TenantDO::getName,TenantDO::getTenantDomain));
            timedCache.put("getAllSimpleIgnoreTenantLimit",getAllSimpleIgnoreTenantLimit);
            return getAllSimpleIgnoreTenantLimit;
        } finally {
            InterceptorIgnoreHelper.clearIgnoreStrategy();
        }
    }
    @Override
    public void refreshCacheForGetAllTenant() {
        doGetAllIgnoreTenantLimit(true);
        doGetAllSimpleIgnoreTenantLimit(true);
    }
}
