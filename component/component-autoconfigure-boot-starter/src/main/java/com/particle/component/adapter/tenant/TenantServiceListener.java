package com.particle.component.adapter.tenant;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.particle.global.mybatis.plus.crud.IAddServiceListener;
import com.particle.global.mybatis.plus.crud.IDeleteServiceListener;
import com.particle.global.mybatis.plus.crud.IUpdateServiceListener;
import com.particle.global.security.tenant.ITenantResolveService;
import com.particle.tenant.infrastructure.dos.TenantDO;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 * 用户添加监听，用来添加部门
 * </p>
 *
 * @author yangwei
 * @since 2023-05-17 09:50:35
 */
public class TenantServiceListener implements IAddServiceListener<TenantDO>, IUpdateServiceListener<TenantDO> , IDeleteServiceListener<TenantDO> {

	@Setter
	private ITenantResolveService iTenantResolveService;

	@Override
	public void postAdd(TenantDO po) {
		iTenantResolveService.removeCache();
	}

	@Override
	public void postDeleteById(Long id, TenantDO tenantDO) {
		iTenantResolveService.removeCache();
	}

	@Override
	public void postDeleteByColumn(Object columnId, SFunction<TenantDO, ?> column, List<TenantDO> tenantDOS) {
		iTenantResolveService.removeCache();
	}

	@Override
	public void postUpdate(TenantDO po) {
		iTenantResolveService.removeCache();
	}
}
