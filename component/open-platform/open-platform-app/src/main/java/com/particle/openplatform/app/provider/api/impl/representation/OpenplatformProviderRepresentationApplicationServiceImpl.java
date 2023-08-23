package com.particle.openplatform.app.provider.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.provider.executor.representation.OpenplatformProviderQueryCommandExecutor;
import com.particle.openplatform.client.provider.api.representation.IOpenplatformProviderRepresentationApplicationService;
import com.particle.openplatform.client.provider.dto.command.representation.OpenplatformProviderPageQueryCommand;
import com.particle.openplatform.client.provider.dto.command.representation.OpenplatformProviderQueryListCommand;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放平台开放接口供应商 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:58
 */
@Service
@CatchAndLog
public class OpenplatformProviderRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformProviderRepresentationApplicationService {

    private OpenplatformProviderQueryCommandExecutor openplatformProviderQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformProviderVO> queryDetail(IdCommand detailCommand) {
        return openplatformProviderQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformProviderVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformProviderQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformProviderVO> pageQuery(OpenplatformProviderPageQueryCommand openplatformProviderPageQueryCommand) {
        return openplatformProviderQueryCommandExecutor.execute(openplatformProviderPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformProviderVO> queryList(OpenplatformProviderQueryListCommand openplatformProviderQueryListCommand) {
        return openplatformProviderQueryCommandExecutor.execute(openplatformProviderQueryListCommand);
    }

    @Autowired
    public void setOpenplatformProviderQueryCommandExecutor(OpenplatformProviderQueryCommandExecutor openplatformProviderQueryCommandExecutor) {
        this.openplatformProviderQueryCommandExecutor = openplatformProviderQueryCommandExecutor;
    }
}
