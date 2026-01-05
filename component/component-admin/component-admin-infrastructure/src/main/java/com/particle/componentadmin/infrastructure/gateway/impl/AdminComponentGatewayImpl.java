package com.particle.componentadmin.infrastructure.gateway.impl;

import com.particle.componentadmin.domain.AdminComponent;
import com.particle.componentadmin.domain.AdminComponentId;
import com.particle.componentadmin.domain.gateway.AdminComponentGateway;
import com.particle.componentadmin.infrastructure.service.IAdminComponentService;
import com.particle.componentadmin.infrastructure.dos.AdminComponentDO;
import com.particle.componentadmin.infrastructure.structmapping.AdminComponentInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 组件 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:21:31
 */
@Component
public class AdminComponentGatewayImpl extends AbstractBaseGatewayImpl<AdminComponentId,AdminComponent> implements AdminComponentGateway {

    private IAdminComponentService iAdminComponentService;

    @Override
    public AdminComponent getById(AdminComponentId adminComponentId) {
        AdminComponentDO byId = iAdminComponentService.getById(adminComponentId.getId());
        AdminComponent adminComponent = DomainFactory.create(AdminComponent.class);
        adminComponent = AdminComponentInfrastructureStructMapping.instance. adminComponentDOToAdminComponent(adminComponent,byId);
        return adminComponent;
    }

    @Override
    public boolean doSave(AdminComponent adminComponent) {
        AdminComponentDO adminComponentDO = AdminComponentInfrastructureStructMapping.instance.adminComponentToAdminComponentDO(adminComponent);
        if (adminComponentDO.getId() == null) {
            adminComponentDO.setAddControl(adminComponent.getAddControl());
            AdminComponentDO add = iAdminComponentService.add(adminComponentDO);
            adminComponent.setId(AdminComponentId.of(add.getId()));
            return add != null;
        }
        adminComponentDO.setUpdateControl(adminComponent.getUpdateControl());
        AdminComponentDO update = iAdminComponentService.update(adminComponentDO);
        return update != null;
    }

    @Override
    public boolean delete(AdminComponentId adminComponentId) {
        return iAdminComponentService.deleteById(adminComponentId.getId());
    }

    @Override
    public boolean delete(AdminComponentId adminComponentId, IdCommand idCommand) {
        return iAdminComponentService.deleteById(idCommand);
    }

    @Autowired
    public void setIAdminComponentService(IAdminComponentService iAdminComponentService) {
        this.iAdminComponentService = iAdminComponentService;
    }
}
