package com.particle.navigation.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.app.executor.representation.NavigationSiteCategoryRelQueryCommandExecutor;
import com.particle.navigation.client.api.representation.INavigationSiteCategoryRelRepresentationApplicationService;
import com.particle.navigation.client.dto.command.representation.NavigationSiteCategoryRelPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSiteCategoryRelQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationSiteCategoryRelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 导航网站分类关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
@Service
@CatchAndLog
public class NavigationSiteCategoryRelRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements INavigationSiteCategoryRelRepresentationApplicationService {

    private NavigationSiteCategoryRelQueryCommandExecutor navigationSiteCategoryRelQueryCommandExecutor;

    @Override
    public SingleResponse<NavigationSiteCategoryRelVO> queryDetail(IdCommand detailCommand) {
        return navigationSiteCategoryRelQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<NavigationSiteCategoryRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return navigationSiteCategoryRelQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<NavigationSiteCategoryRelVO> pageQuery(NavigationSiteCategoryRelPageQueryCommand navigationSiteCategoryRelPageQueryCommand) {
        return navigationSiteCategoryRelQueryCommandExecutor.execute(navigationSiteCategoryRelPageQueryCommand);
    }

    @Override
    public MultiResponse<NavigationSiteCategoryRelVO> queryList(NavigationSiteCategoryRelQueryListCommand navigationSiteCategoryRelQueryListCommand) {
        return navigationSiteCategoryRelQueryCommandExecutor.execute(navigationSiteCategoryRelQueryListCommand);
    }

	@Override
	public MultiResponse<Long> queryNavigationCategoryIdsByNavigationSiteId(IdCommand navigationSiteIdCommand) {

		return navigationSiteCategoryRelQueryCommandExecutor.queryNavigationCategoryIdsByNavigationSiteId(navigationSiteIdCommand);
	}

	@Override
	public MultiResponse<Long> queryNavigationSiteIdsByNavigationCategoryId(IdCommand navigationCategoryIdCommand) {
		return navigationSiteCategoryRelQueryCommandExecutor.queryNavigationSiteIdsByNavigationCategoryId(navigationCategoryIdCommand);
	}

    @Autowired
    public void setNavigationSiteCategoryRelQueryCommandExecutor(NavigationSiteCategoryRelQueryCommandExecutor navigationSiteCategoryRelQueryCommandExecutor) {
        this.navigationSiteCategoryRelQueryCommandExecutor = navigationSiteCategoryRelQueryCommandExecutor;
    }
}
