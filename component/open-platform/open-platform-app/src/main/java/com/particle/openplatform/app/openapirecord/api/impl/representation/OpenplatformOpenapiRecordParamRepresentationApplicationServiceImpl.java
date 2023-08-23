package com.particle.openplatform.app.openapirecord.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.openapirecord.executor.representation.OpenplatformOpenapiRecordParamQueryCommandExecutor;
import com.particle.openplatform.client.openapirecord.api.representation.IOpenplatformOpenapiRecordParamRepresentationApplicationService;
import com.particle.openplatform.client.openapirecord.dto.command.representation.OpenplatformOpenapiRecordParamPageQueryCommand;
import com.particle.openplatform.client.openapirecord.dto.command.representation.OpenplatformOpenapiRecordParamQueryListCommand;
import com.particle.openplatform.client.openapirecord.dto.data.OpenplatformOpenapiRecordParamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放平台开放接口调用记录参数 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:03
 */
@Service
@CatchAndLog
public class OpenplatformOpenapiRecordParamRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiRecordParamRepresentationApplicationService {

    private OpenplatformOpenapiRecordParamQueryCommandExecutor openplatformOpenapiRecordParamQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformOpenapiRecordParamVO> queryDetail(IdCommand detailCommand) {
        return openplatformOpenapiRecordParamQueryCommandExecutor.executeDetail(detailCommand);
    }
    @Override
    public SingleResponse<OpenplatformOpenapiRecordParamVO> detailByOpenplatformOpenapiRecordId(IdCommand detailCommand) {
        return openplatformOpenapiRecordParamQueryCommandExecutor.detailByOpenplatformOpenapiRecordId(detailCommand);
    }

    @Override
    public PageResponse<OpenplatformOpenapiRecordParamVO> pageQuery(OpenplatformOpenapiRecordParamPageQueryCommand openplatformOpenapiRecordParamPageQueryCommand) {
        return openplatformOpenapiRecordParamQueryCommandExecutor.execute(openplatformOpenapiRecordParamPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformOpenapiRecordParamVO> queryList(OpenplatformOpenapiRecordParamQueryListCommand openplatformOpenapiRecordParamQueryListCommand) {
        return openplatformOpenapiRecordParamQueryCommandExecutor.execute(openplatformOpenapiRecordParamQueryListCommand);
    }

    @Autowired
    public void setOpenplatformOpenapiRecordParamQueryCommandExecutor(OpenplatformOpenapiRecordParamQueryCommandExecutor openplatformOpenapiRecordParamQueryCommandExecutor) {
        this.openplatformOpenapiRecordParamQueryCommandExecutor = openplatformOpenapiRecordParamQueryCommandExecutor;
    }
}
