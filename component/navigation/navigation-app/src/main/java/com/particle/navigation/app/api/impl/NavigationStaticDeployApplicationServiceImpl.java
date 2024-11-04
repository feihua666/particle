package com.particle.navigation.app.api.impl;

import com.particle.navigation.app.executor.NavigationStaticDeployCreateCommandExecutor;
import com.particle.navigation.app.executor.NavigationStaticDeployDeleteCommandExecutor;
import com.particle.navigation.app.executor.NavigationStaticDeployUpdateCommandExecutor;
import com.particle.navigation.app.executor.NavigationStaticDeployCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.navigation.client.dto.command.NavigationStaticDeployUpdateCommand;
import com.particle.navigation.client.api.INavigationStaticDeployApplicationService;
import com.particle.navigation.client.dto.command.NavigationStaticDeployCreateCommand;
import com.particle.navigation.client.dto.data.NavigationStaticDeployVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 导航网站静态部署 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-11-01 10:02:52
 */
@Transactional
@Service
@CatchAndLog
public class NavigationStaticDeployApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements INavigationStaticDeployApplicationService {

    private NavigationStaticDeployCreateCommandExecutor navigationStaticDeployCreateCommandExecutor;

    private NavigationStaticDeployDeleteCommandExecutor navigationStaticDeployDeleteCommandExecutor;

    private NavigationStaticDeployUpdateCommandExecutor navigationStaticDeployUpdateCommandExecutor;

    private NavigationStaticDeployCommandExecutor navigationStaticDeployCommandExecutor;


    @Override
    public SingleResponse<NavigationStaticDeployVO> create(NavigationStaticDeployCreateCommand navigationStaticDeployCreateCommand) {
        return navigationStaticDeployCreateCommandExecutor.execute(navigationStaticDeployCreateCommand);
    }

    @Override
    public SingleResponse<NavigationStaticDeployVO> delete(IdCommand deleteCommand) {
        return navigationStaticDeployDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<NavigationStaticDeployVO> update(NavigationStaticDeployUpdateCommand navigationStaticDeployUpdateCommand) {
        return navigationStaticDeployUpdateCommandExecutor.execute(navigationStaticDeployUpdateCommand);
    }

    @Override
    public Response updateLastDeployAt(IdCommand idCommand, LocalDateTime deployAt) {
        return navigationStaticDeployUpdateCommandExecutor.updateLastDeployAt(idCommand,deployAt);
    }


    @Autowired
    public void setNavigationStaticDeployCreateCommandExecutor(NavigationStaticDeployCreateCommandExecutor navigationStaticDeployCreateCommandExecutor) {
        this.navigationStaticDeployCreateCommandExecutor = navigationStaticDeployCreateCommandExecutor;
    }

    @Autowired
    public void setNavigationStaticDeployDeleteCommandExecutor(NavigationStaticDeployDeleteCommandExecutor navigationStaticDeployDeleteCommandExecutor) {
        this.navigationStaticDeployDeleteCommandExecutor = navigationStaticDeployDeleteCommandExecutor;
    }
    @Autowired
    public void setNavigationStaticDeployUpdateCommandExecutor(NavigationStaticDeployUpdateCommandExecutor navigationStaticDeployUpdateCommandExecutor) {
        this.navigationStaticDeployUpdateCommandExecutor = navigationStaticDeployUpdateCommandExecutor;
    }
    @Autowired
    public void setNavigationStaticDeployCommandExecutor(NavigationStaticDeployCommandExecutor navigationStaticDeployCommandExecutor) {
        this.navigationStaticDeployCommandExecutor = navigationStaticDeployCommandExecutor;
    }
}
