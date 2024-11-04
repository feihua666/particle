package com.particle.navigation.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.app.executor.representation.NavigationStaticDeployQueryCommandExecutor;
import com.particle.navigation.client.api.representation.INavigationStaticDeployRepresentationApplicationService;
import com.particle.navigation.client.dto.command.representation.NavigationStaticDeployPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationStaticDeployQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationStaticDeployVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 导航网站静态部署 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-11-01 10:02:52
 */
@Service
@CatchAndLog
public class NavigationStaticDeployRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements INavigationStaticDeployRepresentationApplicationService {

    private NavigationStaticDeployQueryCommandExecutor navigationStaticDeployQueryCommandExecutor;

    @Override
    public SingleResponse<NavigationStaticDeployVO> queryDetail(IdCommand detailCommand) {
        return navigationStaticDeployQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<NavigationStaticDeployVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return navigationStaticDeployQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<NavigationStaticDeployVO> pageQuery(NavigationStaticDeployPageQueryCommand navigationStaticDeployPageQueryCommand) {
        return navigationStaticDeployQueryCommandExecutor.execute(navigationStaticDeployPageQueryCommand);
    }

    @Override
    public MultiResponse<NavigationStaticDeployVO> queryList(NavigationStaticDeployQueryListCommand navigationStaticDeployQueryListCommand) {
        return navigationStaticDeployQueryCommandExecutor.execute(navigationStaticDeployQueryListCommand);
    }


    @Autowired
    public void setNavigationStaticDeployQueryCommandExecutor(NavigationStaticDeployQueryCommandExecutor navigationStaticDeployQueryCommandExecutor) {
        this.navigationStaticDeployQueryCommandExecutor = navigationStaticDeployQueryCommandExecutor;
    }
}
