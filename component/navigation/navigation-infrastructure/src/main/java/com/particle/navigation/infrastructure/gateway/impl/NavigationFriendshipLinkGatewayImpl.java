package com.particle.navigation.infrastructure.gateway.impl;

import com.particle.navigation.domain.NavigationFriendshipLink;
import com.particle.navigation.domain.NavigationFriendshipLinkId;
import com.particle.navigation.domain.gateway.NavigationFriendshipLinkGateway;
import com.particle.navigation.infrastructure.service.INavigationFriendshipLinkService;
import com.particle.navigation.infrastructure.dos.NavigationFriendshipLinkDO;
import com.particle.navigation.infrastructure.structmapping.NavigationFriendshipLinkInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 导航友情链接 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:01
 */
@Component
public class NavigationFriendshipLinkGatewayImpl extends AbstractBaseGatewayImpl<NavigationFriendshipLinkId,NavigationFriendshipLink> implements NavigationFriendshipLinkGateway {

    private INavigationFriendshipLinkService iNavigationFriendshipLinkService;

    @Override
    public NavigationFriendshipLink getById(NavigationFriendshipLinkId navigationFriendshipLinkId) {
        NavigationFriendshipLinkDO byId = iNavigationFriendshipLinkService.getById(navigationFriendshipLinkId.getId());
        NavigationFriendshipLink navigationFriendshipLink = DomainFactory.create(NavigationFriendshipLink.class);
        navigationFriendshipLink = NavigationFriendshipLinkInfrastructureStructMapping.instance. navigationFriendshipLinkDOToNavigationFriendshipLink(navigationFriendshipLink,byId);
        return navigationFriendshipLink;
    }

    @Override
    public boolean doSave(NavigationFriendshipLink navigationFriendshipLink) {
        NavigationFriendshipLinkDO navigationFriendshipLinkDO = NavigationFriendshipLinkInfrastructureStructMapping.instance.navigationFriendshipLinkToNavigationFriendshipLinkDO(navigationFriendshipLink);
        if (navigationFriendshipLinkDO.getId() == null) {
            navigationFriendshipLinkDO.setAddControl(navigationFriendshipLink.getAddControl());
            NavigationFriendshipLinkDO add = iNavigationFriendshipLinkService.add(navigationFriendshipLinkDO);
            navigationFriendshipLink.setId(NavigationFriendshipLinkId.of(add.getId()));
            return add != null;
        }
        navigationFriendshipLinkDO.setUpdateControl(navigationFriendshipLink.getUpdateControl());
        NavigationFriendshipLinkDO update = iNavigationFriendshipLinkService.update(navigationFriendshipLinkDO);
        return update != null;
    }

    @Override
    public boolean delete(NavigationFriendshipLinkId navigationFriendshipLinkId) {
        return iNavigationFriendshipLinkService.deleteById(navigationFriendshipLinkId.getId());
    }

    @Override
    public boolean delete(NavigationFriendshipLinkId navigationFriendshipLinkId, IdCommand idCommand) {
        return iNavigationFriendshipLinkService.deleteById(idCommand);
    }

    @Autowired
    public void setINavigationFriendshipLinkService(INavigationFriendshipLinkService iNavigationFriendshipLinkService) {
        this.iNavigationFriendshipLinkService = iNavigationFriendshipLinkService;
    }
}
