package com.particle.navigation.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.app.executor.representation.NavigationSiteQueryCommandExecutor;
import com.particle.navigation.client.api.representation.INavigationSiteRepresentationApplicationService;
import com.particle.navigation.client.dto.command.representation.NavigationSitePageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSiteQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationSiteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 导航网站 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:56
 */
@Service
@CatchAndLog
public class NavigationSiteRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements INavigationSiteRepresentationApplicationService {

    private NavigationSiteQueryCommandExecutor navigationSiteQueryCommandExecutor;

    @Override
    public SingleResponse<NavigationSiteVO> queryDetail(IdCommand detailCommand) {
        return navigationSiteQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<NavigationSiteVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return navigationSiteQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<NavigationSiteVO> pageQuery(NavigationSitePageQueryCommand navigationSitePageQueryCommand) {
        return navigationSiteQueryCommandExecutor.execute(navigationSitePageQueryCommand);
    }

    @Override
    public MultiResponse<NavigationSiteVO> queryList(NavigationSiteQueryListCommand navigationSiteQueryListCommand) {
        return navigationSiteQueryCommandExecutor.execute(navigationSiteQueryListCommand);
    }


    @Autowired
    public void setNavigationSiteQueryCommandExecutor(NavigationSiteQueryCommandExecutor navigationSiteQueryCommandExecutor) {
        this.navigationSiteQueryCommandExecutor = navigationSiteQueryCommandExecutor;
    }
}
