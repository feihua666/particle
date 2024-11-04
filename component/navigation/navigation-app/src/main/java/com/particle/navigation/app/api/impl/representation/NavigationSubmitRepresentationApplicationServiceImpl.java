package com.particle.navigation.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.app.executor.representation.NavigationSubmitQueryCommandExecutor;
import com.particle.navigation.client.api.representation.INavigationSubmitRepresentationApplicationService;
import com.particle.navigation.client.dto.command.representation.NavigationSubmitPageQueryCommand;
import com.particle.navigation.client.dto.command.representation.NavigationSubmitQueryListCommand;
import com.particle.navigation.client.dto.data.NavigationSubmitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 导航提交 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
@Service
@CatchAndLog
public class NavigationSubmitRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements INavigationSubmitRepresentationApplicationService {

    private NavigationSubmitQueryCommandExecutor navigationSubmitQueryCommandExecutor;

    @Override
    public SingleResponse<NavigationSubmitVO> queryDetail(IdCommand detailCommand) {
        return navigationSubmitQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<NavigationSubmitVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return navigationSubmitQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<NavigationSubmitVO> pageQuery(NavigationSubmitPageQueryCommand navigationSubmitPageQueryCommand) {
        return navigationSubmitQueryCommandExecutor.execute(navigationSubmitPageQueryCommand);
    }

    @Override
    public MultiResponse<NavigationSubmitVO> queryList(NavigationSubmitQueryListCommand navigationSubmitQueryListCommand) {
        return navigationSubmitQueryCommandExecutor.execute(navigationSubmitQueryListCommand);
    }


    @Autowired
    public void setNavigationSubmitQueryCommandExecutor(NavigationSubmitQueryCommandExecutor navigationSubmitQueryCommandExecutor) {
        this.navigationSubmitQueryCommandExecutor = navigationSubmitQueryCommandExecutor;
    }
}
