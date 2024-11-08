package com.particle.navigation.app.api.impl;

import com.particle.navigation.app.executor.NavigationSiteTagRelCreateCommandExecutor;
import com.particle.navigation.app.executor.NavigationSiteTagRelDeleteCommandExecutor;
import com.particle.navigation.app.executor.NavigationSiteTagRelUpdateCommandExecutor;
import com.particle.navigation.app.executor.NavigationSiteTagRelCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.navigation.client.dto.command.NavigationSiteTagRelUpdateCommand;
import com.particle.navigation.client.api.INavigationSiteTagRelApplicationService;
import com.particle.navigation.client.dto.command.NavigationSiteTagRelCreateCommand;
import com.particle.navigation.client.dto.data.NavigationSiteTagRelVO;

import com.particle.navigation.app.executor.NavigationSiteTagRelCommandExecutor;
import com.particle.navigation.client.dto.command.NavigationSiteAssignNavigationSiteTagCommand;
import com.particle.navigation.client.dto.command.NavigationSiteTagAssignNavigationSiteCommand;

import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 导航网站标签关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:39:14
 */
@Transactional
@Service
@CatchAndLog
public class NavigationSiteTagRelApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements INavigationSiteTagRelApplicationService {

    private NavigationSiteTagRelCreateCommandExecutor navigationSiteTagRelCreateCommandExecutor;

    private NavigationSiteTagRelDeleteCommandExecutor navigationSiteTagRelDeleteCommandExecutor;

    private NavigationSiteTagRelUpdateCommandExecutor navigationSiteTagRelUpdateCommandExecutor;

    private NavigationSiteTagRelCommandExecutor navigationSiteTagRelCommandExecutor;


    @Override
    public SingleResponse<NavigationSiteTagRelVO> create(NavigationSiteTagRelCreateCommand navigationSiteTagRelCreateCommand) {
        return navigationSiteTagRelCreateCommandExecutor.execute(navigationSiteTagRelCreateCommand);
    }

    @Override
    public SingleResponse<NavigationSiteTagRelVO> delete(IdCommand deleteCommand) {
        return navigationSiteTagRelDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<NavigationSiteTagRelVO> update(NavigationSiteTagRelUpdateCommand navigationSiteTagRelUpdateCommand) {
        return navigationSiteTagRelUpdateCommandExecutor.execute(navigationSiteTagRelUpdateCommand);
    }


    @Override
    public Response navigationSiteAssignNavigationSiteTag(NavigationSiteAssignNavigationSiteTagCommand navigationSiteAssignNavigationSiteTagCommand){
        return navigationSiteTagRelCommandExecutor.navigationSiteAssignNavigationSiteTag(navigationSiteAssignNavigationSiteTagCommand);
    }

    @Override
    public Response navigationSiteTagAssignNavigationSite(NavigationSiteTagAssignNavigationSiteCommand navigationSiteTagAssignNavigationSiteCommand){
        return navigationSiteTagRelCommandExecutor.navigationSiteTagAssignNavigationSite(navigationSiteTagAssignNavigationSiteCommand);
    }

    @Override
    public Response deleteByNavigationSiteId(IdCommand idCommand){
        return navigationSiteTagRelDeleteCommandExecutor.deleteByNavigationSiteId(idCommand);
    }

    @Override
    public Response deleteByNavigationSiteTagId(IdCommand idCommand){
        return navigationSiteTagRelDeleteCommandExecutor.deleteByNavigationSiteTagId(idCommand);
    }

    @Autowired
    public void setNavigationSiteTagRelCreateCommandExecutor(NavigationSiteTagRelCreateCommandExecutor navigationSiteTagRelCreateCommandExecutor) {
        this.navigationSiteTagRelCreateCommandExecutor = navigationSiteTagRelCreateCommandExecutor;
    }

    @Autowired
    public void setNavigationSiteTagRelDeleteCommandExecutor(NavigationSiteTagRelDeleteCommandExecutor navigationSiteTagRelDeleteCommandExecutor) {
        this.navigationSiteTagRelDeleteCommandExecutor = navigationSiteTagRelDeleteCommandExecutor;
    }
    @Autowired
    public void setNavigationSiteTagRelUpdateCommandExecutor(NavigationSiteTagRelUpdateCommandExecutor navigationSiteTagRelUpdateCommandExecutor) {
        this.navigationSiteTagRelUpdateCommandExecutor = navigationSiteTagRelUpdateCommandExecutor;
    }
    @Autowired
    public void setNavigationSiteTagRelCommandExecutor(NavigationSiteTagRelCommandExecutor navigationSiteTagRelCommandExecutor) {
        this.navigationSiteTagRelCommandExecutor = navigationSiteTagRelCommandExecutor;
    }
}
