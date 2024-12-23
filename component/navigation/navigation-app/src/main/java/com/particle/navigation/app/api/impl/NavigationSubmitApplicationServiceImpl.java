package com.particle.navigation.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.app.executor.NavigationSubmitCommandExecutor;
import com.particle.navigation.app.executor.NavigationSubmitCreateCommandExecutor;
import com.particle.navigation.app.executor.NavigationSubmitDeleteCommandExecutor;
import com.particle.navigation.app.executor.NavigationSubmitUpdateCommandExecutor;
import com.particle.navigation.client.api.INavigationSubmitApplicationService;
import com.particle.navigation.client.dto.command.NavigationSubmitCreateCommand;
import com.particle.navigation.client.dto.command.NavigationSubmitUpdateCommand;
import com.particle.navigation.client.dto.data.NavigationSubmitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 导航提交 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
@Transactional
@Service
@CatchAndLog
public class NavigationSubmitApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements INavigationSubmitApplicationService {

    private NavigationSubmitCreateCommandExecutor navigationSubmitCreateCommandExecutor;

    private NavigationSubmitDeleteCommandExecutor navigationSubmitDeleteCommandExecutor;

    private NavigationSubmitUpdateCommandExecutor navigationSubmitUpdateCommandExecutor;

    private NavigationSubmitCommandExecutor navigationSubmitCommandExecutor;


    @Override
    public SingleResponse<NavigationSubmitVO> create(NavigationSubmitCreateCommand navigationSubmitCreateCommand) {
        return navigationSubmitCreateCommandExecutor.execute(navigationSubmitCreateCommand);
    }

    @Override
    public SingleResponse<NavigationSubmitVO> delete(IdCommand deleteCommand) {
        return navigationSubmitDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<NavigationSubmitVO> update(NavigationSubmitUpdateCommand navigationSubmitUpdateCommand) {
        return navigationSubmitUpdateCommandExecutor.execute(navigationSubmitUpdateCommand);
    }

    @Override
    public Response sureSubmit(IdCommand idCommand) {
        return navigationSubmitCommandExecutor.sureSubmit(idCommand);
    }


    @Autowired
    public void setNavigationSubmitCreateCommandExecutor(NavigationSubmitCreateCommandExecutor navigationSubmitCreateCommandExecutor) {
        this.navigationSubmitCreateCommandExecutor = navigationSubmitCreateCommandExecutor;
    }

    @Autowired
    public void setNavigationSubmitDeleteCommandExecutor(NavigationSubmitDeleteCommandExecutor navigationSubmitDeleteCommandExecutor) {
        this.navigationSubmitDeleteCommandExecutor = navigationSubmitDeleteCommandExecutor;
    }
    @Autowired
    public void setNavigationSubmitUpdateCommandExecutor(NavigationSubmitUpdateCommandExecutor navigationSubmitUpdateCommandExecutor) {
        this.navigationSubmitUpdateCommandExecutor = navigationSubmitUpdateCommandExecutor;
    }
    @Autowired
    public void setNavigationSubmitCommandExecutor(NavigationSubmitCommandExecutor navigationSubmitCommandExecutor) {
        this.navigationSubmitCommandExecutor = navigationSubmitCommandExecutor;
    }
}
