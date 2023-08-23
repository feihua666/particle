package com.particle.openplatform.app.providerrecord.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.providerrecord.executor.representation.OpenplatformProviderRecordParamQueryCommandExecutor;
import com.particle.openplatform.client.openapirecord.dto.data.OpenplatformOpenapiRecordParamVO;
import com.particle.openplatform.client.providerrecord.api.representation.IOpenplatformProviderRecordParamRepresentationApplicationService;
import com.particle.openplatform.client.providerrecord.dto.command.representation.OpenplatformProviderRecordParamPageQueryCommand;
import com.particle.openplatform.client.providerrecord.dto.command.representation.OpenplatformProviderRecordParamQueryListCommand;
import com.particle.openplatform.client.providerrecord.dto.data.OpenplatformProviderRecordParamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放平台开放接口供应商调用记录参数 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:18:54
 */
@Service
@CatchAndLog
public class OpenplatformProviderRecordParamRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformProviderRecordParamRepresentationApplicationService {

    private OpenplatformProviderRecordParamQueryCommandExecutor openplatformProviderRecordParamQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformProviderRecordParamVO> queryDetail(IdCommand detailCommand) {
        return openplatformProviderRecordParamQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformProviderRecordParamVO> detailByOpenplatformProviderRecordId(IdCommand detailCommand) {
        return openplatformProviderRecordParamQueryCommandExecutor.detailByOpenplatformProviderRecordId(detailCommand);
    }

    @Override
    public PageResponse<OpenplatformProviderRecordParamVO> pageQuery(OpenplatformProviderRecordParamPageQueryCommand openplatformProviderRecordParamPageQueryCommand) {
        return openplatformProviderRecordParamQueryCommandExecutor.execute(openplatformProviderRecordParamPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformProviderRecordParamVO> queryList(OpenplatformProviderRecordParamQueryListCommand openplatformProviderRecordParamQueryListCommand) {
        return openplatformProviderRecordParamQueryCommandExecutor.execute(openplatformProviderRecordParamQueryListCommand);
    }

    @Autowired
    public void setOpenplatformProviderRecordParamQueryCommandExecutor(OpenplatformProviderRecordParamQueryCommandExecutor openplatformProviderRecordParamQueryCommandExecutor) {
        this.openplatformProviderRecordParamQueryCommandExecutor = openplatformProviderRecordParamQueryCommandExecutor;
    }
}
