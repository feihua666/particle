package com.particle.navigation.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.app.executor.representation.NavigationSiteTagQueryCommandExecutor;
import com.particle.navigation.client.api.representation.INavigationSiteTagRepresentationApplicationService;
import com.particle.navigation.client.dto.command.representation.NavigationSiteTagPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSiteTagQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationSiteTagVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 导航网站标签 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:38:23
 */
@Service
@CatchAndLog
public class NavigationSiteTagRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements INavigationSiteTagRepresentationApplicationService {

    private NavigationSiteTagQueryCommandExecutor navigationSiteTagQueryCommandExecutor;

    @Override
    public SingleResponse<NavigationSiteTagVO> queryDetail(IdCommand detailCommand) {
        return navigationSiteTagQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<NavigationSiteTagVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return navigationSiteTagQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<NavigationSiteTagVO> pageQuery(NavigationSiteTagPageQueryCommand navigationSiteTagPageQueryCommand) {
        return navigationSiteTagQueryCommandExecutor.execute(navigationSiteTagPageQueryCommand);
    }

    @Override
    public MultiResponse<NavigationSiteTagVO> queryList(NavigationSiteTagQueryListCommand navigationSiteTagQueryListCommand) {
        return navigationSiteTagQueryCommandExecutor.execute(navigationSiteTagQueryListCommand);
    }


    @Autowired
    public void setNavigationSiteTagQueryCommandExecutor(NavigationSiteTagQueryCommandExecutor navigationSiteTagQueryCommandExecutor) {
        this.navigationSiteTagQueryCommandExecutor = navigationSiteTagQueryCommandExecutor;
    }
}
