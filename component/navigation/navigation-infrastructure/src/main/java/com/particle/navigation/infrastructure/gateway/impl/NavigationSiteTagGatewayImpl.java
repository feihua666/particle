package com.particle.navigation.infrastructure.gateway.impl;

import com.particle.navigation.domain.NavigationSiteTag;
import com.particle.navigation.domain.NavigationSiteTagId;
import com.particle.navigation.domain.gateway.NavigationSiteTagGateway;
import com.particle.navigation.infrastructure.service.INavigationSiteTagService;
import com.particle.navigation.infrastructure.dos.NavigationSiteTagDO;
import com.particle.navigation.infrastructure.structmapping.NavigationSiteTagInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 导航网站标签 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:38:23
 */
@Component
public class NavigationSiteTagGatewayImpl extends AbstractBaseGatewayImpl<NavigationSiteTagId,NavigationSiteTag> implements NavigationSiteTagGateway {

    private INavigationSiteTagService iNavigationSiteTagService;

    @Override
    public NavigationSiteTag getById(NavigationSiteTagId navigationSiteTagId) {
        NavigationSiteTagDO byId = iNavigationSiteTagService.getById(navigationSiteTagId.getId());
        NavigationSiteTag navigationSiteTag = DomainFactory.create(NavigationSiteTag.class);
        navigationSiteTag = NavigationSiteTagInfrastructureStructMapping.instance. navigationSiteTagDOToNavigationSiteTag(navigationSiteTag,byId);
        return navigationSiteTag;
    }

    @Override
    public boolean doSave(NavigationSiteTag navigationSiteTag) {
        NavigationSiteTagDO navigationSiteTagDO = NavigationSiteTagInfrastructureStructMapping.instance.navigationSiteTagToNavigationSiteTagDO(navigationSiteTag);
        if (navigationSiteTagDO.getId() == null) {
            navigationSiteTagDO.setAddControl(navigationSiteTag.getAddControl());
            NavigationSiteTagDO add = iNavigationSiteTagService.add(navigationSiteTagDO);
            navigationSiteTag.setId(NavigationSiteTagId.of(add.getId()));
            return add != null;
        }
        navigationSiteTagDO.setUpdateControl(navigationSiteTag.getUpdateControl());
        NavigationSiteTagDO update = iNavigationSiteTagService.update(navigationSiteTagDO);
        return update != null;
    }

    @Override
    public boolean delete(NavigationSiteTagId navigationSiteTagId) {
        return iNavigationSiteTagService.deleteById(navigationSiteTagId.getId());
    }

    @Override
    public boolean delete(NavigationSiteTagId navigationSiteTagId, IdCommand idCommand) {
        return iNavigationSiteTagService.deleteById(idCommand);
    }

    @Autowired
    public void setINavigationSiteTagService(INavigationSiteTagService iNavigationSiteTagService) {
        this.iNavigationSiteTagService = iNavigationSiteTagService;
    }
}
