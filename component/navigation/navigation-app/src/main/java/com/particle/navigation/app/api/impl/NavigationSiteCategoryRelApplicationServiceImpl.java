package com.particle.navigation.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.app.executor.NavigationSiteCategoryRelCommandExecutor;
import com.particle.navigation.app.executor.NavigationSiteCategoryRelCreateCommandExecutor;
import com.particle.navigation.app.executor.NavigationSiteCategoryRelDeleteCommandExecutor;
import com.particle.navigation.app.executor.NavigationSiteCategoryRelUpdateCommandExecutor;
import com.particle.navigation.client.api.INavigationSiteCategoryRelApplicationService;
import com.particle.navigation.client.dto.command.NavigationCategoryAssignNavigationSiteCommand;
import com.particle.navigation.client.dto.command.NavigationSiteAssignNavigationCategoryCommand;
import com.particle.navigation.client.dto.command.NavigationSiteCategoryRelCreateCommand;
import com.particle.navigation.client.dto.command.NavigationSiteCategoryRelUpdateCommand;
import com.particle.navigation.client.dto.data.NavigationSiteCategoryRelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 导航网站分类关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
@Transactional
@Service
@CatchAndLog
public class NavigationSiteCategoryRelApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements INavigationSiteCategoryRelApplicationService {

    private NavigationSiteCategoryRelCreateCommandExecutor navigationSiteCategoryRelCreateCommandExecutor;

    private NavigationSiteCategoryRelDeleteCommandExecutor navigationSiteCategoryRelDeleteCommandExecutor;

    private NavigationSiteCategoryRelUpdateCommandExecutor navigationSiteCategoryRelUpdateCommandExecutor;

    private NavigationSiteCategoryRelCommandExecutor navigationSiteCategoryRelCommandExecutor;


    @Override
    public SingleResponse<NavigationSiteCategoryRelVO> create(NavigationSiteCategoryRelCreateCommand navigationSiteCategoryRelCreateCommand) {
        return navigationSiteCategoryRelCreateCommandExecutor.execute(navigationSiteCategoryRelCreateCommand);
    }

    @Override
    public SingleResponse<NavigationSiteCategoryRelVO> delete(IdCommand deleteCommand) {
        return navigationSiteCategoryRelDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<NavigationSiteCategoryRelVO> update(NavigationSiteCategoryRelUpdateCommand navigationSiteCategoryRelUpdateCommand) {
        return navigationSiteCategoryRelUpdateCommandExecutor.execute(navigationSiteCategoryRelUpdateCommand);
    }


    @Override
    public Response navigationSiteAssignNavigationCategory(NavigationSiteAssignNavigationCategoryCommand navigationSiteAssignNavigationCategoryCommand){
        return navigationSiteCategoryRelCommandExecutor.navigationSiteAssignNavigationCategory(navigationSiteAssignNavigationCategoryCommand);
    }

    @Override
    public Response navigationCategoryAssignNavigationSite(NavigationCategoryAssignNavigationSiteCommand navigationCategoryAssignNavigationSiteCommand){
        return navigationSiteCategoryRelCommandExecutor.navigationCategoryAssignNavigationSite(navigationCategoryAssignNavigationSiteCommand);
    }

    @Override
    public Response deleteByNavigationSiteId(IdCommand idCommand){
        return navigationSiteCategoryRelDeleteCommandExecutor.deleteByNavigationSiteId(idCommand);
    }

    @Override
    public Response deleteByNavigationCategoryId(IdCommand idCommand){
        return navigationSiteCategoryRelDeleteCommandExecutor.deleteByNavigationCategoryId(idCommand);
    }

    @Autowired
    public void setNavigationSiteCategoryRelCreateCommandExecutor(NavigationSiteCategoryRelCreateCommandExecutor navigationSiteCategoryRelCreateCommandExecutor) {
        this.navigationSiteCategoryRelCreateCommandExecutor = navigationSiteCategoryRelCreateCommandExecutor;
    }

    @Autowired
    public void setNavigationSiteCategoryRelDeleteCommandExecutor(NavigationSiteCategoryRelDeleteCommandExecutor navigationSiteCategoryRelDeleteCommandExecutor) {
        this.navigationSiteCategoryRelDeleteCommandExecutor = navigationSiteCategoryRelDeleteCommandExecutor;
    }
    @Autowired
    public void setNavigationSiteCategoryRelUpdateCommandExecutor(NavigationSiteCategoryRelUpdateCommandExecutor navigationSiteCategoryRelUpdateCommandExecutor) {
        this.navigationSiteCategoryRelUpdateCommandExecutor = navigationSiteCategoryRelUpdateCommandExecutor;
    }
    @Autowired
    public void setNavigationSiteCategoryRelCommandExecutor(NavigationSiteCategoryRelCommandExecutor navigationSiteCategoryRelCommandExecutor) {
        this.navigationSiteCategoryRelCommandExecutor = navigationSiteCategoryRelCommandExecutor;
    }
}
