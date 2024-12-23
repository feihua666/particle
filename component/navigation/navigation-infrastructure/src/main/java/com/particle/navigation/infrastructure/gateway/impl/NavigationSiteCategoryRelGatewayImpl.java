package com.particle.navigation.infrastructure.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.navigation.domain.NavigationSiteCategoryRel;
import com.particle.navigation.domain.NavigationSiteCategoryRelId;
import com.particle.navigation.domain.gateway.NavigationSiteCategoryRelGateway;
import com.particle.navigation.infrastructure.dos.NavigationSiteCategoryRelDO;
import com.particle.navigation.infrastructure.service.INavigationSiteCategoryRelService;
import com.particle.navigation.infrastructure.structmapping.NavigationSiteCategoryRelInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 导航网站分类关系 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
@Component
public class NavigationSiteCategoryRelGatewayImpl extends AbstractBaseGatewayImpl<NavigationSiteCategoryRelId,NavigationSiteCategoryRel> implements NavigationSiteCategoryRelGateway {

    private INavigationSiteCategoryRelService iNavigationSiteCategoryRelService;

    @Override
    public NavigationSiteCategoryRel getById(NavigationSiteCategoryRelId navigationSiteCategoryRelId) {
        NavigationSiteCategoryRelDO byId = iNavigationSiteCategoryRelService.getById(navigationSiteCategoryRelId.getId());
        NavigationSiteCategoryRel navigationSiteCategoryRel = DomainFactory.create(NavigationSiteCategoryRel.class);
        navigationSiteCategoryRel = NavigationSiteCategoryRelInfrastructureStructMapping.instance. navigationSiteCategoryRelDOToNavigationSiteCategoryRel(navigationSiteCategoryRel,byId);
        return navigationSiteCategoryRel;
    }

    @Override
    public boolean doSave(NavigationSiteCategoryRel navigationSiteCategoryRel) {
        NavigationSiteCategoryRelDO navigationSiteCategoryRelDO = NavigationSiteCategoryRelInfrastructureStructMapping.instance.navigationSiteCategoryRelToNavigationSiteCategoryRelDO(navigationSiteCategoryRel);
        if (navigationSiteCategoryRelDO.getId() == null) {
            navigationSiteCategoryRelDO.setAddControl(navigationSiteCategoryRel.getAddControl());
            NavigationSiteCategoryRelDO add = iNavigationSiteCategoryRelService.add(navigationSiteCategoryRelDO);
            navigationSiteCategoryRel.setId(NavigationSiteCategoryRelId.of(add.getId()));
            return add != null;
        }
        navigationSiteCategoryRelDO.setUpdateControl(navigationSiteCategoryRel.getUpdateControl());
        NavigationSiteCategoryRelDO update = iNavigationSiteCategoryRelService.update(navigationSiteCategoryRelDO);
        return update != null;
    }

    @Override
    public boolean delete(NavigationSiteCategoryRelId navigationSiteCategoryRelId) {
        return iNavigationSiteCategoryRelService.deleteById(navigationSiteCategoryRelId.getId());
    }

    @Override
    public boolean delete(NavigationSiteCategoryRelId navigationSiteCategoryRelId, IdCommand idCommand) {
        return iNavigationSiteCategoryRelService.deleteById(idCommand);
    }

    @Autowired
    public void setINavigationSiteCategoryRelService(INavigationSiteCategoryRelService iNavigationSiteCategoryRelService) {
        this.iNavigationSiteCategoryRelService = iNavigationSiteCategoryRelService;
    }
}
