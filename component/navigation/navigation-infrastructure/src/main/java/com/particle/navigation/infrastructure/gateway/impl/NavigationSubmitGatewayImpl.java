package com.particle.navigation.infrastructure.gateway.impl;

import com.particle.navigation.domain.NavigationSubmit;
import com.particle.navigation.domain.NavigationSubmitId;
import com.particle.navigation.domain.gateway.NavigationSubmitGateway;
import com.particle.navigation.infrastructure.service.INavigationSubmitService;
import com.particle.navigation.infrastructure.dos.NavigationSubmitDO;
import com.particle.navigation.infrastructure.structmapping.NavigationSubmitInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 导航提交 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
@Component
public class NavigationSubmitGatewayImpl extends AbstractBaseGatewayImpl<NavigationSubmitId,NavigationSubmit> implements NavigationSubmitGateway {

    private INavigationSubmitService iNavigationSubmitService;

    @Override
    public NavigationSubmit getById(NavigationSubmitId navigationSubmitId) {
        NavigationSubmitDO byId = iNavigationSubmitService.getById(navigationSubmitId.getId());
        NavigationSubmit navigationSubmit = DomainFactory.create(NavigationSubmit.class);
        navigationSubmit = NavigationSubmitInfrastructureStructMapping.instance. navigationSubmitDOToNavigationSubmit(navigationSubmit,byId);
        return navigationSubmit;
    }

    @Override
    public boolean doSave(NavigationSubmit navigationSubmit) {
        NavigationSubmitDO navigationSubmitDO = NavigationSubmitInfrastructureStructMapping.instance.navigationSubmitToNavigationSubmitDO(navigationSubmit);
        if (navigationSubmitDO.getId() == null) {
            navigationSubmitDO.setAddControl(navigationSubmit.getAddControl());
            NavigationSubmitDO add = iNavigationSubmitService.add(navigationSubmitDO);
            navigationSubmit.setId(NavigationSubmitId.of(add.getId()));
            return add != null;
        }
        navigationSubmitDO.setUpdateControl(navigationSubmit.getUpdateControl());
        NavigationSubmitDO update = iNavigationSubmitService.update(navigationSubmitDO);
        return update != null;
    }

    @Override
    public boolean delete(NavigationSubmitId navigationSubmitId) {
        return iNavigationSubmitService.deleteById(navigationSubmitId.getId());
    }

    @Override
    public boolean delete(NavigationSubmitId navigationSubmitId, IdCommand idCommand) {
        return iNavigationSubmitService.deleteById(idCommand);
    }

    @Autowired
    public void setINavigationSubmitService(INavigationSubmitService iNavigationSubmitService) {
        this.iNavigationSubmitService = iNavigationSubmitService;
    }
}
