package com.particle.navigation.infrastructure.gateway.impl;

import com.particle.navigation.domain.NavigationSite;
import com.particle.navigation.domain.NavigationSiteId;
import com.particle.navigation.domain.gateway.NavigationSiteGateway;
import com.particle.navigation.infrastructure.service.INavigationSiteService;
import com.particle.navigation.infrastructure.dos.NavigationSiteDO;
import com.particle.navigation.infrastructure.structmapping.NavigationSiteInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 导航网站 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:56
 */
@Component
public class NavigationSiteGatewayImpl extends AbstractBaseGatewayImpl<NavigationSiteId,NavigationSite> implements NavigationSiteGateway {

    private INavigationSiteService iNavigationSiteService;

    @Override
    public NavigationSite getById(NavigationSiteId navigationSiteId) {
        NavigationSiteDO byId = iNavigationSiteService.getById(navigationSiteId.getId());
        NavigationSite navigationSite = DomainFactory.create(NavigationSite.class);
        navigationSite = NavigationSiteInfrastructureStructMapping.instance. navigationSiteDOToNavigationSite(navigationSite,byId);
        return navigationSite;
    }

    @Override
    public boolean doSave(NavigationSite navigationSite) {
        NavigationSiteDO navigationSiteDO = NavigationSiteInfrastructureStructMapping.instance.navigationSiteToNavigationSiteDO(navigationSite);
        if (navigationSiteDO.getId() == null) {
            navigationSiteDO.setAddControl(navigationSite.getAddControl());
            NavigationSiteDO add = iNavigationSiteService.add(navigationSiteDO);
            navigationSite.setId(NavigationSiteId.of(add.getId()));
            return add != null;
        }
        navigationSiteDO.setUpdateControl(navigationSite.getUpdateControl());
        NavigationSiteDO update = iNavigationSiteService.update(navigationSiteDO);
        return update != null;
    }

    @Override
    public boolean delete(NavigationSiteId navigationSiteId) {
        return iNavigationSiteService.deleteById(navigationSiteId.getId());
    }

    @Override
    public boolean delete(NavigationSiteId navigationSiteId, IdCommand idCommand) {
        return iNavigationSiteService.deleteById(idCommand);
    }

    @Autowired
    public void setINavigationSiteService(INavigationSiteService iNavigationSiteService) {
        this.iNavigationSiteService = iNavigationSiteService;
    }
}
