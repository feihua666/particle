package com.particle.openplatform.app.providerrecord.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.providerrecord.executor.representation.OpenplatformProviderRecordQueryCommandExecutor;
import com.particle.openplatform.client.providerrecord.api.representation.IOpenplatformProviderRecordRepresentationApplicationService;
import com.particle.openplatform.client.providerrecord.dto.command.representation.OpenplatformProviderRecordPageQueryCommand;
import com.particle.openplatform.client.providerrecord.dto.command.representation.OpenplatformProviderRecordQueryListCommand;
import com.particle.openplatform.client.providerrecord.dto.data.OpenplatformProviderRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放平台开放接口供应商调用记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:17:25
 */
@Service
@CatchAndLog
public class OpenplatformProviderRecordRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformProviderRecordRepresentationApplicationService {

    private OpenplatformProviderRecordQueryCommandExecutor openplatformProviderRecordQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformProviderRecordVO> queryDetail(IdCommand detailCommand) {
        return openplatformProviderRecordQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public PageResponse<OpenplatformProviderRecordVO> pageQuery(OpenplatformProviderRecordPageQueryCommand openplatformProviderRecordPageQueryCommand) {
        return openplatformProviderRecordQueryCommandExecutor.execute(openplatformProviderRecordPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformProviderRecordVO> queryList(OpenplatformProviderRecordQueryListCommand openplatformProviderRecordQueryListCommand) {
        return openplatformProviderRecordQueryCommandExecutor.execute(openplatformProviderRecordQueryListCommand);
    }

    @Autowired
    public void setOpenplatformProviderRecordQueryCommandExecutor(OpenplatformProviderRecordQueryCommandExecutor openplatformProviderRecordQueryCommandExecutor) {
        this.openplatformProviderRecordQueryCommandExecutor = openplatformProviderRecordQueryCommandExecutor;
    }
}
