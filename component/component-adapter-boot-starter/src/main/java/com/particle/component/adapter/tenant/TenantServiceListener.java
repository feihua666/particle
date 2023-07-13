package com.particle.component.adapter.tenant;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.particle.dept.infrastructure.deptuserrel.dos.DeptUserRelDO;
import com.particle.dept.infrastructure.deptuserrel.service.IDeptUserRelService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IAddServiceListener;
import com.particle.global.mybatis.plus.crud.IDeleteServiceListener;
import com.particle.global.mybatis.plus.crud.IQueryWrapperHandler;
import com.particle.global.mybatis.plus.crud.IUpdateServiceListener;
import com.particle.global.security.tenant.ITenantResolveService;
import com.particle.tenant.client.dto.command.TenantUserCreateCommand;
import com.particle.tenant.client.dto.command.representation.TenantUserPageQueryCommand;
import com.particle.tenant.client.dto.command.representation.TenantUserQueryListCommand;
import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.tenant.infrastructure.dos.TenantUserDO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

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
