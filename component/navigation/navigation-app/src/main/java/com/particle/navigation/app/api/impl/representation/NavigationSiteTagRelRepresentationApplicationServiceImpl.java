package com.particle.navigation.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.app.executor.representation.NavigationSiteTagRelQueryCommandExecutor;
import com.particle.navigation.client.api.representation.INavigationSiteTagRelRepresentationApplicationService;
import com.particle.navigation.client.dto.command.representation.NavigationSiteTagRelPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSiteTagRelQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationSiteTagRelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 导航网站标签关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:39:14
 */
@Service
@CatchAndLog
public class NavigationSiteTagRelRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements INavigationSiteTagRelRepresentationApplicationService {

    private NavigationSiteTagRelQueryCommandExecutor navigationSiteTagRelQueryCommandExecutor;

    @Override
    public SingleResponse<NavigationSiteTagRelVO> queryDetail(IdCommand detailCommand) {
        return navigationSiteTagRelQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<NavigationSiteTagRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return navigationSiteTagRelQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<NavigationSiteTagRelVO> pageQuery(NavigationSiteTagRelPageQueryCommand navigationSiteTagRelPageQueryCommand) {
        return navigationSiteTagRelQueryCommandExecutor.execute(navigationSiteTagRelPageQueryCommand);
    }

    @Override
    public MultiResponse<NavigationSiteTagRelVO> queryList(NavigationSiteTagRelQueryListCommand navigationSiteTagRelQueryListCommand) {
        return navigationSiteTagRelQueryCommandExecutor.execute(navigationSiteTagRelQueryListCommand);
    }

	@Override
	public MultiResponse<Long> queryNavigationSiteTagIdsByNavigationSiteId(IdCommand navigationSiteIdCommand) {

		return navigationSiteTagRelQueryCommandExecutor.queryNavigationSiteTagIdsByNavigationSiteId(navigationSiteIdCommand);
	}

	@Override
	public MultiResponse<Long> queryNavigationSiteIdsByNavigationSiteTagId(IdCommand navigationSiteTagIdCommand) {
		return navigationSiteTagRelQueryCommandExecutor.queryNavigationSiteIdsByNavigationSiteTagId(navigationSiteTagIdCommand);
	}

    @Autowired
    public void setNavigationSiteTagRelQueryCommandExecutor(NavigationSiteTagRelQueryCommandExecutor navigationSiteTagRelQueryCommandExecutor) {
        this.navigationSiteTagRelQueryCommandExecutor = navigationSiteTagRelQueryCommandExecutor;
    }
}
