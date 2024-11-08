package com.particle.navigation.app.api.impl;

import com.particle.navigation.app.executor.NavigationSiteTagCreateCommandExecutor;
import com.particle.navigation.app.executor.NavigationSiteTagDeleteCommandExecutor;
import com.particle.navigation.app.executor.NavigationSiteTagUpdateCommandExecutor;
import com.particle.navigation.app.executor.NavigationSiteTagCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.navigation.client.dto.command.NavigationSiteTagUpdateCommand;
import com.particle.navigation.client.api.INavigationSiteTagApplicationService;
import com.particle.navigation.client.dto.command.NavigationSiteTagCreateCommand;
import com.particle.navigation.client.dto.data.NavigationSiteTagVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 导航网站标签 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:38:23
 */
@Transactional
@Service
@CatchAndLog
public class NavigationSiteTagApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements INavigationSiteTagApplicationService {

    private NavigationSiteTagCreateCommandExecutor navigationSiteTagCreateCommandExecutor;

    private NavigationSiteTagDeleteCommandExecutor navigationSiteTagDeleteCommandExecutor;

    private NavigationSiteTagUpdateCommandExecutor navigationSiteTagUpdateCommandExecutor;

    private NavigationSiteTagCommandExecutor navigationSiteTagCommandExecutor;


    @Override
    public SingleResponse<NavigationSiteTagVO> create(NavigationSiteTagCreateCommand navigationSiteTagCreateCommand) {
        return navigationSiteTagCreateCommandExecutor.execute(navigationSiteTagCreateCommand);
    }

    @Override
    public SingleResponse<NavigationSiteTagVO> delete(IdCommand deleteCommand) {
        return navigationSiteTagDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<NavigationSiteTagVO> update(NavigationSiteTagUpdateCommand navigationSiteTagUpdateCommand) {
        return navigationSiteTagUpdateCommandExecutor.execute(navigationSiteTagUpdateCommand);
    }


    @Autowired
    public void setNavigationSiteTagCreateCommandExecutor(NavigationSiteTagCreateCommandExecutor navigationSiteTagCreateCommandExecutor) {
        this.navigationSiteTagCreateCommandExecutor = navigationSiteTagCreateCommandExecutor;
    }

    @Autowired
    public void setNavigationSiteTagDeleteCommandExecutor(NavigationSiteTagDeleteCommandExecutor navigationSiteTagDeleteCommandExecutor) {
        this.navigationSiteTagDeleteCommandExecutor = navigationSiteTagDeleteCommandExecutor;
    }
    @Autowired
    public void setNavigationSiteTagUpdateCommandExecutor(NavigationSiteTagUpdateCommandExecutor navigationSiteTagUpdateCommandExecutor) {
        this.navigationSiteTagUpdateCommandExecutor = navigationSiteTagUpdateCommandExecutor;
    }
    @Autowired
    public void setNavigationSiteTagCommandExecutor(NavigationSiteTagCommandExecutor navigationSiteTagCommandExecutor) {
        this.navigationSiteTagCommandExecutor = navigationSiteTagCommandExecutor;
    }
}
