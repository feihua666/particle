package com.particle.navigation.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.app.executor.NavigationSiteCommandExecutor;
import com.particle.navigation.app.executor.NavigationSiteCreateCommandExecutor;
import com.particle.navigation.app.executor.NavigationSiteDeleteCommandExecutor;
import com.particle.navigation.app.executor.NavigationSiteUpdateCommandExecutor;
import com.particle.navigation.client.api.INavigationSiteApplicationService;
import com.particle.navigation.client.dto.command.NavigationSiteCreateCommand;
import com.particle.navigation.client.dto.command.NavigationSiteUpdateCommand;
import com.particle.navigation.client.dto.data.NavigationSiteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 导航网站 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:56
 */
@Transactional
@Service
@CatchAndLog
public class NavigationSiteApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements INavigationSiteApplicationService {

    private NavigationSiteCreateCommandExecutor navigationSiteCreateCommandExecutor;

    private NavigationSiteDeleteCommandExecutor navigationSiteDeleteCommandExecutor;

    private NavigationSiteUpdateCommandExecutor navigationSiteUpdateCommandExecutor;

    private NavigationSiteCommandExecutor navigationSiteCommandExecutor;


    @Override
    public SingleResponse<NavigationSiteVO> create(NavigationSiteCreateCommand navigationSiteCreateCommand) {
        return navigationSiteCreateCommandExecutor.execute(navigationSiteCreateCommand);
    }

    @Override
    public SingleResponse<NavigationSiteVO> delete(IdCommand deleteCommand) {
        return navigationSiteDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<NavigationSiteVO> update(NavigationSiteUpdateCommand navigationSiteUpdateCommand) {
        return navigationSiteUpdateCommandExecutor.execute(navigationSiteUpdateCommand);
    }


    @Autowired
    public void setNavigationSiteCreateCommandExecutor(NavigationSiteCreateCommandExecutor navigationSiteCreateCommandExecutor) {
        this.navigationSiteCreateCommandExecutor = navigationSiteCreateCommandExecutor;
    }

    @Autowired
    public void setNavigationSiteDeleteCommandExecutor(NavigationSiteDeleteCommandExecutor navigationSiteDeleteCommandExecutor) {
        this.navigationSiteDeleteCommandExecutor = navigationSiteDeleteCommandExecutor;
    }
    @Autowired
    public void setNavigationSiteUpdateCommandExecutor(NavigationSiteUpdateCommandExecutor navigationSiteUpdateCommandExecutor) {
        this.navigationSiteUpdateCommandExecutor = navigationSiteUpdateCommandExecutor;
    }
    @Autowired
    public void setNavigationSiteCommandExecutor(NavigationSiteCommandExecutor navigationSiteCommandExecutor) {
        this.navigationSiteCommandExecutor = navigationSiteCommandExecutor;
    }
}
