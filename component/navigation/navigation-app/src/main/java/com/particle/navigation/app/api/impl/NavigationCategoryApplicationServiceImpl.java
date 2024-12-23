package com.particle.navigation.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.app.executor.NavigationCategoryCommandExecutor;
import com.particle.navigation.app.executor.NavigationCategoryCreateCommandExecutor;
import com.particle.navigation.app.executor.NavigationCategoryDeleteCommandExecutor;
import com.particle.navigation.app.executor.NavigationCategoryUpdateCommandExecutor;
import com.particle.navigation.client.api.INavigationCategoryApplicationService;
import com.particle.navigation.client.dto.command.NavigationCategoryCreateCommand;
import com.particle.navigation.client.dto.command.NavigationCategoryUpdateCommand;
import com.particle.navigation.client.dto.data.NavigationCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 导航分类 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:42
 */
@Transactional
@Service
@CatchAndLog
public class NavigationCategoryApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements INavigationCategoryApplicationService {

    private NavigationCategoryCreateCommandExecutor navigationCategoryCreateCommandExecutor;

    private NavigationCategoryDeleteCommandExecutor navigationCategoryDeleteCommandExecutor;

    private NavigationCategoryUpdateCommandExecutor navigationCategoryUpdateCommandExecutor;

    private NavigationCategoryCommandExecutor navigationCategoryCommandExecutor;


    @Override
    public SingleResponse<NavigationCategoryVO> create(NavigationCategoryCreateCommand navigationCategoryCreateCommand) {
        return navigationCategoryCreateCommandExecutor.execute(navigationCategoryCreateCommand);
    }

    @Override
    public SingleResponse<NavigationCategoryVO> delete(IdCommand deleteCommand) {
        return navigationCategoryDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<NavigationCategoryVO> update(NavigationCategoryUpdateCommand navigationCategoryUpdateCommand) {
        return navigationCategoryUpdateCommandExecutor.execute(navigationCategoryUpdateCommand);
    }


    @Autowired
    public void setNavigationCategoryCreateCommandExecutor(NavigationCategoryCreateCommandExecutor navigationCategoryCreateCommandExecutor) {
        this.navigationCategoryCreateCommandExecutor = navigationCategoryCreateCommandExecutor;
    }

    @Autowired
    public void setNavigationCategoryDeleteCommandExecutor(NavigationCategoryDeleteCommandExecutor navigationCategoryDeleteCommandExecutor) {
        this.navigationCategoryDeleteCommandExecutor = navigationCategoryDeleteCommandExecutor;
    }
    @Autowired
    public void setNavigationCategoryUpdateCommandExecutor(NavigationCategoryUpdateCommandExecutor navigationCategoryUpdateCommandExecutor) {
        this.navigationCategoryUpdateCommandExecutor = navigationCategoryUpdateCommandExecutor;
    }
    @Autowired
    public void setNavigationCategoryCommandExecutor(NavigationCategoryCommandExecutor navigationCategoryCommandExecutor) {
        this.navigationCategoryCommandExecutor = navigationCategoryCommandExecutor;
    }
}
