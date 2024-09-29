package com.particle.openplatform.app.provider.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.provider.executor.representation.OpenplatformProviderApiQueryCommandExecutor;
import com.particle.openplatform.client.provider.api.representation.IOpenplatformProviderApiRepresentationApplicationService;
import com.particle.openplatform.client.provider.dto.command.representation.OpenplatformProviderApiPageQueryCommand;
import com.particle.openplatform.client.provider.dto.command.representation.OpenplatformProviderApiQueryListCommand;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderApiVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放平台供应商接口 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:53:52
 */
@Service
@CatchAndLog
public class OpenplatformProviderApiRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformProviderApiRepresentationApplicationService {

    private OpenplatformProviderApiQueryCommandExecutor openplatformProviderApiQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformProviderApiVO> queryDetail(IdCommand detailCommand) {
        return openplatformProviderApiQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformProviderApiVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformProviderApiQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformProviderApiVO> pageQuery(OpenplatformProviderApiPageQueryCommand openplatformProviderApiPageQueryCommand) {
        return openplatformProviderApiQueryCommandExecutor.execute(openplatformProviderApiPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformProviderApiVO> queryList(OpenplatformProviderApiQueryListCommand openplatformProviderApiQueryListCommand) {
        return openplatformProviderApiQueryCommandExecutor.execute(openplatformProviderApiQueryListCommand);
    }


    @Autowired
    public void setOpenplatformProviderApiQueryCommandExecutor(OpenplatformProviderApiQueryCommandExecutor openplatformProviderApiQueryCommandExecutor) {
        this.openplatformProviderApiQueryCommandExecutor = openplatformProviderApiQueryCommandExecutor;
    }
}
