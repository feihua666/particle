package com.particle.navigation.infrastructure.gateway.impl;

import com.particle.navigation.domain.NavigationCategory;
import com.particle.navigation.domain.NavigationCategoryId;
import com.particle.navigation.domain.gateway.NavigationCategoryGateway;
import com.particle.navigation.infrastructure.service.INavigationCategoryService;
import com.particle.navigation.infrastructure.dos.NavigationCategoryDO;
import com.particle.navigation.infrastructure.structmapping.NavigationCategoryInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 导航分类 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:42
 */
@Component
public class NavigationCategoryGatewayImpl extends AbstractBaseGatewayImpl<NavigationCategoryId,NavigationCategory> implements NavigationCategoryGateway {

    private INavigationCategoryService iNavigationCategoryService;

    @Override
    public NavigationCategory getById(NavigationCategoryId navigationCategoryId) {
        NavigationCategoryDO byId = iNavigationCategoryService.getById(navigationCategoryId.getId());
        NavigationCategory navigationCategory = DomainFactory.create(NavigationCategory.class);
        navigationCategory = NavigationCategoryInfrastructureStructMapping.instance. navigationCategoryDOToNavigationCategory(navigationCategory,byId);
        return navigationCategory;
    }

    @Override
    public boolean doSave(NavigationCategory navigationCategory) {
        NavigationCategoryDO navigationCategoryDO = NavigationCategoryInfrastructureStructMapping.instance.navigationCategoryToNavigationCategoryDO(navigationCategory);
        if (navigationCategoryDO.getId() == null) {
            navigationCategoryDO.setAddControl(navigationCategory.getAddControl());
            NavigationCategoryDO add = iNavigationCategoryService.add(navigationCategoryDO);
            navigationCategory.setId(NavigationCategoryId.of(add.getId()));
            return add != null;
        }
        navigationCategoryDO.setUpdateControl(navigationCategory.getUpdateControl());
        NavigationCategoryDO update = iNavigationCategoryService.update(navigationCategoryDO);
        return update != null;
    }

    @Override
    public boolean delete(NavigationCategoryId navigationCategoryId) {
        return iNavigationCategoryService.deleteById(navigationCategoryId.getId());
    }

    @Override
    public boolean delete(NavigationCategoryId navigationCategoryId, IdCommand idCommand) {
        return iNavigationCategoryService.deleteById(idCommand);
    }

    @Autowired
    public void setINavigationCategoryService(INavigationCategoryService iNavigationCategoryService) {
        this.iNavigationCategoryService = iNavigationCategoryService;
    }
}
