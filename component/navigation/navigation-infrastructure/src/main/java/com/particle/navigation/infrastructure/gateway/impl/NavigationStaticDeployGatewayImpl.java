package com.particle.navigation.infrastructure.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.navigation.domain.NavigationStaticDeploy;
import com.particle.navigation.domain.NavigationStaticDeployId;
import com.particle.navigation.domain.gateway.NavigationStaticDeployGateway;
import com.particle.navigation.infrastructure.dos.NavigationStaticDeployDO;
import com.particle.navigation.infrastructure.service.INavigationStaticDeployService;
import com.particle.navigation.infrastructure.structmapping.NavigationStaticDeployInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 导航网站静态部署 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-11-01 10:02:52
 */
@Component
public class NavigationStaticDeployGatewayImpl extends AbstractBaseGatewayImpl<NavigationStaticDeployId,NavigationStaticDeploy> implements NavigationStaticDeployGateway {

    private INavigationStaticDeployService iNavigationStaticDeployService;

    @Override
    public NavigationStaticDeploy getById(NavigationStaticDeployId navigationStaticDeployId) {
        NavigationStaticDeployDO byId = iNavigationStaticDeployService.getById(navigationStaticDeployId.getId());
        NavigationStaticDeploy navigationStaticDeploy = DomainFactory.create(NavigationStaticDeploy.class);
        navigationStaticDeploy = NavigationStaticDeployInfrastructureStructMapping.instance. navigationStaticDeployDOToNavigationStaticDeploy(navigationStaticDeploy,byId);
        return navigationStaticDeploy;
    }

    @Override
    public boolean doSave(NavigationStaticDeploy navigationStaticDeploy) {
        NavigationStaticDeployDO navigationStaticDeployDO = NavigationStaticDeployInfrastructureStructMapping.instance.navigationStaticDeployToNavigationStaticDeployDO(navigationStaticDeploy);
        if (navigationStaticDeployDO.getId() == null) {
            navigationStaticDeployDO.setAddControl(navigationStaticDeploy.getAddControl());
            NavigationStaticDeployDO add = iNavigationStaticDeployService.add(navigationStaticDeployDO);
            navigationStaticDeploy.setId(NavigationStaticDeployId.of(add.getId()));
            return add != null;
        }
        navigationStaticDeployDO.setUpdateControl(navigationStaticDeploy.getUpdateControl());
        NavigationStaticDeployDO update = iNavigationStaticDeployService.update(navigationStaticDeployDO);
        return update != null;
    }

    @Override
    public boolean delete(NavigationStaticDeployId navigationStaticDeployId) {
        return iNavigationStaticDeployService.deleteById(navigationStaticDeployId.getId());
    }

    @Override
    public boolean delete(NavigationStaticDeployId navigationStaticDeployId, IdCommand idCommand) {
        return iNavigationStaticDeployService.deleteById(idCommand);
    }

    @Autowired
    public void setINavigationStaticDeployService(INavigationStaticDeployService iNavigationStaticDeployService) {
        this.iNavigationStaticDeployService = iNavigationStaticDeployService;
    }
}
