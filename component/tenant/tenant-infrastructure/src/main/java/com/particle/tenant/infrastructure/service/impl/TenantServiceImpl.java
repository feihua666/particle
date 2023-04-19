package com.particle.tenant.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.tenant.infrastructure.mapper.TenantMapper;
import com.particle.tenant.infrastructure.service.ITenantService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


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

	@Override
	protected void preAdd(TenantDO po) {
	    // 租户编码 已存在不能添加
	    assertByColumn(po.getCode(),TenantDO::getCode,false);
		if (StrUtil.isNotEmpty(po.getTenantDomain())) {
			assertByColumn(po.getTenantDomain(),TenantDO::getTenantDomain,false);
		}
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
}
