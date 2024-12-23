package com.particle.navigation.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.app.executor.NavigationFriendshipLinkCommandExecutor;
import com.particle.navigation.app.executor.NavigationFriendshipLinkCreateCommandExecutor;
import com.particle.navigation.app.executor.NavigationFriendshipLinkDeleteCommandExecutor;
import com.particle.navigation.app.executor.NavigationFriendshipLinkUpdateCommandExecutor;
import com.particle.navigation.client.api.INavigationFriendshipLinkApplicationService;
import com.particle.navigation.client.dto.command.NavigationFriendshipLinkCreateCommand;
import com.particle.navigation.client.dto.command.NavigationFriendshipLinkUpdateCommand;
import com.particle.navigation.client.dto.data.NavigationFriendshipLinkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 导航友情链接 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:01
 */
@Transactional
@Service
@CatchAndLog
public class NavigationFriendshipLinkApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements INavigationFriendshipLinkApplicationService {

    private NavigationFriendshipLinkCreateCommandExecutor navigationFriendshipLinkCreateCommandExecutor;

    private NavigationFriendshipLinkDeleteCommandExecutor navigationFriendshipLinkDeleteCommandExecutor;

    private NavigationFriendshipLinkUpdateCommandExecutor navigationFriendshipLinkUpdateCommandExecutor;

    private NavigationFriendshipLinkCommandExecutor navigationFriendshipLinkCommandExecutor;


    @Override
    public SingleResponse<NavigationFriendshipLinkVO> create(NavigationFriendshipLinkCreateCommand navigationFriendshipLinkCreateCommand) {
        return navigationFriendshipLinkCreateCommandExecutor.execute(navigationFriendshipLinkCreateCommand);
    }

    @Override
    public SingleResponse<NavigationFriendshipLinkVO> delete(IdCommand deleteCommand) {
        return navigationFriendshipLinkDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<NavigationFriendshipLinkVO> update(NavigationFriendshipLinkUpdateCommand navigationFriendshipLinkUpdateCommand) {
        return navigationFriendshipLinkUpdateCommandExecutor.execute(navigationFriendshipLinkUpdateCommand);
    }


    @Autowired
    public void setNavigationFriendshipLinkCreateCommandExecutor(NavigationFriendshipLinkCreateCommandExecutor navigationFriendshipLinkCreateCommandExecutor) {
        this.navigationFriendshipLinkCreateCommandExecutor = navigationFriendshipLinkCreateCommandExecutor;
    }

    @Autowired
    public void setNavigationFriendshipLinkDeleteCommandExecutor(NavigationFriendshipLinkDeleteCommandExecutor navigationFriendshipLinkDeleteCommandExecutor) {
        this.navigationFriendshipLinkDeleteCommandExecutor = navigationFriendshipLinkDeleteCommandExecutor;
    }
    @Autowired
    public void setNavigationFriendshipLinkUpdateCommandExecutor(NavigationFriendshipLinkUpdateCommandExecutor navigationFriendshipLinkUpdateCommandExecutor) {
        this.navigationFriendshipLinkUpdateCommandExecutor = navigationFriendshipLinkUpdateCommandExecutor;
    }
    @Autowired
    public void setNavigationFriendshipLinkCommandExecutor(NavigationFriendshipLinkCommandExecutor navigationFriendshipLinkCommandExecutor) {
        this.navigationFriendshipLinkCommandExecutor = navigationFriendshipLinkCommandExecutor;
    }
}
