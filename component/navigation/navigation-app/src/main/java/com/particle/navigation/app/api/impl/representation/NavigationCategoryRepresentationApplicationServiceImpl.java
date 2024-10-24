package com.particle.navigation.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.app.executor.representation.NavigationCategoryQueryCommandExecutor;
import com.particle.navigation.client.api.representation.INavigationCategoryRepresentationApplicationService;
import com.particle.navigation.client.dto.command.representation.NavigationCategoryPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationCategoryQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 导航分类 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:42
 */
@Service
@CatchAndLog
public class NavigationCategoryRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements INavigationCategoryRepresentationApplicationService {

    private NavigationCategoryQueryCommandExecutor navigationCategoryQueryCommandExecutor;

    @Override
    public SingleResponse<NavigationCategoryVO> queryDetail(IdCommand detailCommand) {
        return navigationCategoryQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<NavigationCategoryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return navigationCategoryQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<NavigationCategoryVO> pageQuery(NavigationCategoryPageQueryCommand navigationCategoryPageQueryCommand) {
        return navigationCategoryQueryCommandExecutor.execute(navigationCategoryPageQueryCommand);
    }

    @Override
    public MultiResponse<NavigationCategoryVO> queryList(NavigationCategoryQueryListCommand navigationCategoryQueryListCommand) {
        return navigationCategoryQueryCommandExecutor.execute(navigationCategoryQueryListCommand);
    }


    @Autowired
    public void setNavigationCategoryQueryCommandExecutor(NavigationCategoryQueryCommandExecutor navigationCategoryQueryCommandExecutor) {
        this.navigationCategoryQueryCommandExecutor = navigationCategoryQueryCommandExecutor;
    }
}
