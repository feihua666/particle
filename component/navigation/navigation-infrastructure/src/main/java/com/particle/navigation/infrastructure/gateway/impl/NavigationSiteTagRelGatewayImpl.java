package com.particle.navigation.infrastructure.gateway.impl;

import com.particle.navigation.domain.NavigationSiteTagRel;
import com.particle.navigation.domain.NavigationSiteTagRelId;
import com.particle.navigation.domain.gateway.NavigationSiteTagRelGateway;
import com.particle.navigation.infrastructure.service.INavigationSiteTagRelService;
import com.particle.navigation.infrastructure.dos.NavigationSiteTagRelDO;
import com.particle.navigation.infrastructure.structmapping.NavigationSiteTagRelInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 导航网站标签关系 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:39:14
 */
@Component
public class NavigationSiteTagRelGatewayImpl extends AbstractBaseGatewayImpl<NavigationSiteTagRelId,NavigationSiteTagRel> implements NavigationSiteTagRelGateway {

    private INavigationSiteTagRelService iNavigationSiteTagRelService;

    @Override
    public NavigationSiteTagRel getById(NavigationSiteTagRelId navigationSiteTagRelId) {
        NavigationSiteTagRelDO byId = iNavigationSiteTagRelService.getById(navigationSiteTagRelId.getId());
        NavigationSiteTagRel navigationSiteTagRel = DomainFactory.create(NavigationSiteTagRel.class);
        navigationSiteTagRel = NavigationSiteTagRelInfrastructureStructMapping.instance. navigationSiteTagRelDOToNavigationSiteTagRel(navigationSiteTagRel,byId);
        return navigationSiteTagRel;
    }

    @Override
    public boolean doSave(NavigationSiteTagRel navigationSiteTagRel) {
        NavigationSiteTagRelDO navigationSiteTagRelDO = NavigationSiteTagRelInfrastructureStructMapping.instance.navigationSiteTagRelToNavigationSiteTagRelDO(navigationSiteTagRel);
        if (navigationSiteTagRelDO.getId() == null) {
            navigationSiteTagRelDO.setAddControl(navigationSiteTagRel.getAddControl());
            NavigationSiteTagRelDO add = iNavigationSiteTagRelService.add(navigationSiteTagRelDO);
            navigationSiteTagRel.setId(NavigationSiteTagRelId.of(add.getId()));
            return add != null;
        }
        navigationSiteTagRelDO.setUpdateControl(navigationSiteTagRel.getUpdateControl());
        NavigationSiteTagRelDO update = iNavigationSiteTagRelService.update(navigationSiteTagRelDO);
        return update != null;
    }

    @Override
    public boolean delete(NavigationSiteTagRelId navigationSiteTagRelId) {
        return iNavigationSiteTagRelService.deleteById(navigationSiteTagRelId.getId());
    }

    @Override
    public boolean delete(NavigationSiteTagRelId navigationSiteTagRelId, IdCommand idCommand) {
        return iNavigationSiteTagRelService.deleteById(idCommand);
    }

    @Autowired
    public void setINavigationSiteTagRelService(INavigationSiteTagRelService iNavigationSiteTagRelService) {
        this.iNavigationSiteTagRelService = iNavigationSiteTagRelService;
    }
}
