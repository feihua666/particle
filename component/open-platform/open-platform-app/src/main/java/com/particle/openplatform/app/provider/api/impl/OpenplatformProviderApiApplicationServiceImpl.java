package com.particle.openplatform.app.provider.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.provider.executor.OpenplatformProviderApiCommandExecutor;
import com.particle.openplatform.app.provider.executor.OpenplatformProviderApiCreateCommandExecutor;
import com.particle.openplatform.app.provider.executor.OpenplatformProviderApiDeleteCommandExecutor;
import com.particle.openplatform.app.provider.executor.OpenplatformProviderApiUpdateCommandExecutor;
import com.particle.openplatform.client.provider.api.IOpenplatformProviderApiApplicationService;
import com.particle.openplatform.client.provider.dto.command.OpenplatformProviderApiCreateCommand;
import com.particle.openplatform.client.provider.dto.command.OpenplatformProviderApiUpdateCommand;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderApiVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放平台供应商接口 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:53:52
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformProviderApiApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformProviderApiApplicationService {

    private OpenplatformProviderApiCreateCommandExecutor openplatformProviderApiCreateCommandExecutor;

    private OpenplatformProviderApiDeleteCommandExecutor openplatformProviderApiDeleteCommandExecutor;

    private OpenplatformProviderApiUpdateCommandExecutor openplatformProviderApiUpdateCommandExecutor;

    private OpenplatformProviderApiCommandExecutor openplatformProviderApiCommandExecutor;


    @Override
    public SingleResponse<OpenplatformProviderApiVO> create(OpenplatformProviderApiCreateCommand openplatformProviderApiCreateCommand) {
        return openplatformProviderApiCreateCommandExecutor.execute(openplatformProviderApiCreateCommand);
    }

    @Override
    public SingleResponse<OpenplatformProviderApiVO> delete(IdCommand deleteCommand) {
        return openplatformProviderApiDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<OpenplatformProviderApiVO> update(OpenplatformProviderApiUpdateCommand openplatformProviderApiUpdateCommand) {
        return openplatformProviderApiUpdateCommandExecutor.execute(openplatformProviderApiUpdateCommand);
    }


    @Autowired
    public void setOpenplatformProviderApiCreateCommandExecutor(OpenplatformProviderApiCreateCommandExecutor openplatformProviderApiCreateCommandExecutor) {
        this.openplatformProviderApiCreateCommandExecutor = openplatformProviderApiCreateCommandExecutor;
    }

    @Autowired
    public void setOpenplatformProviderApiDeleteCommandExecutor(OpenplatformProviderApiDeleteCommandExecutor openplatformProviderApiDeleteCommandExecutor) {
        this.openplatformProviderApiDeleteCommandExecutor = openplatformProviderApiDeleteCommandExecutor;
    }
    @Autowired
    public void setOpenplatformProviderApiUpdateCommandExecutor(OpenplatformProviderApiUpdateCommandExecutor openplatformProviderApiUpdateCommandExecutor) {
        this.openplatformProviderApiUpdateCommandExecutor = openplatformProviderApiUpdateCommandExecutor;
    }
    @Autowired
    public void setOpenplatformProviderApiCommandExecutor(OpenplatformProviderApiCommandExecutor openplatformProviderApiCommandExecutor) {
        this.openplatformProviderApiCommandExecutor = openplatformProviderApiCommandExecutor;
    }
}
