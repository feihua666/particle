package com.particle.componentadmin.infrastructure.gateway.impl;

import com.particle.componentadmin.domain.AdminComponentDependency;
import com.particle.componentadmin.domain.AdminComponentDependencyId;
import com.particle.componentadmin.domain.gateway.AdminComponentDependencyGateway;
import com.particle.componentadmin.infrastructure.service.IAdminComponentDependencyService;
import com.particle.componentadmin.infrastructure.dos.AdminComponentDependencyDO;
import com.particle.componentadmin.infrastructure.structmapping.AdminComponentDependencyInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 组件依赖关系 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:22:06
 */
@Component
public class AdminComponentDependencyGatewayImpl extends AbstractBaseGatewayImpl<AdminComponentDependencyId,AdminComponentDependency> implements AdminComponentDependencyGateway {

    private IAdminComponentDependencyService iAdminComponentDependencyService;

    @Override
    public AdminComponentDependency getById(AdminComponentDependencyId adminComponentDependencyId) {
        AdminComponentDependencyDO byId = iAdminComponentDependencyService.getById(adminComponentDependencyId.getId());
        AdminComponentDependency adminComponentDependency = DomainFactory.create(AdminComponentDependency.class);
        adminComponentDependency = AdminComponentDependencyInfrastructureStructMapping.instance. adminComponentDependencyDOToAdminComponentDependency(adminComponentDependency,byId);
        return adminComponentDependency;
    }

    @Override
    public boolean doSave(AdminComponentDependency adminComponentDependency) {
        AdminComponentDependencyDO adminComponentDependencyDO = AdminComponentDependencyInfrastructureStructMapping.instance.adminComponentDependencyToAdminComponentDependencyDO(adminComponentDependency);
        if (adminComponentDependencyDO.getId() == null) {
            adminComponentDependencyDO.setAddControl(adminComponentDependency.getAddControl());
            AdminComponentDependencyDO add = iAdminComponentDependencyService.add(adminComponentDependencyDO);
            adminComponentDependency.setId(AdminComponentDependencyId.of(add.getId()));
            return add != null;
        }
        adminComponentDependencyDO.setUpdateControl(adminComponentDependency.getUpdateControl());
        AdminComponentDependencyDO update = iAdminComponentDependencyService.update(adminComponentDependencyDO);
        return update != null;
    }

    @Override
    public boolean delete(AdminComponentDependencyId adminComponentDependencyId) {
        return iAdminComponentDependencyService.deleteById(adminComponentDependencyId.getId());
    }

    @Override
    public boolean delete(AdminComponentDependencyId adminComponentDependencyId, IdCommand idCommand) {
        return iAdminComponentDependencyService.deleteById(idCommand);
    }

    @Autowired
    public void setIAdminComponentDependencyService(IAdminComponentDependencyService iAdminComponentDependencyService) {
        this.iAdminComponentDependencyService = iAdminComponentDependencyService;
    }
}
