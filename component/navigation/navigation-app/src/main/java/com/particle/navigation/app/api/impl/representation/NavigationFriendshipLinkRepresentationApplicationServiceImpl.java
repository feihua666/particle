package com.particle.navigation.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.app.executor.representation.NavigationFriendshipLinkQueryCommandExecutor;
import com.particle.navigation.client.api.representation.INavigationFriendshipLinkRepresentationApplicationService;
import com.particle.navigation.client.dto.command.representation.NavigationFriendshipLinkPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationFriendshipLinkQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationFriendshipLinkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 导航友情链接 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:01
 */
@Service
@CatchAndLog
public class NavigationFriendshipLinkRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements INavigationFriendshipLinkRepresentationApplicationService {

    private NavigationFriendshipLinkQueryCommandExecutor navigationFriendshipLinkQueryCommandExecutor;

    @Override
    public SingleResponse<NavigationFriendshipLinkVO> queryDetail(IdCommand detailCommand) {
        return navigationFriendshipLinkQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<NavigationFriendshipLinkVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return navigationFriendshipLinkQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<NavigationFriendshipLinkVO> pageQuery(NavigationFriendshipLinkPageQueryCommand navigationFriendshipLinkPageQueryCommand) {
        return navigationFriendshipLinkQueryCommandExecutor.execute(navigationFriendshipLinkPageQueryCommand);
    }

    @Override
    public MultiResponse<NavigationFriendshipLinkVO> queryList(NavigationFriendshipLinkQueryListCommand navigationFriendshipLinkQueryListCommand) {
        return navigationFriendshipLinkQueryCommandExecutor.execute(navigationFriendshipLinkQueryListCommand);
    }


    @Autowired
    public void setNavigationFriendshipLinkQueryCommandExecutor(NavigationFriendshipLinkQueryCommandExecutor navigationFriendshipLinkQueryCommandExecutor) {
        this.navigationFriendshipLinkQueryCommandExecutor = navigationFriendshipLinkQueryCommandExecutor;
    }
}
