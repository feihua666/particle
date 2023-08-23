package com.particle.openplatform.app.openapirecord.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.openapirecord.executor.representation.OpenplatformOpenapiRecordQueryCommandExecutor;
import com.particle.openplatform.client.openapirecord.api.representation.IOpenplatformOpenapiRecordRepresentationApplicationService;
import com.particle.openplatform.client.openapirecord.dto.command.representation.OpenplatformOpenapiRecordPageQueryCommand;
import com.particle.openplatform.client.openapirecord.dto.command.representation.OpenplatformOpenapiRecordQueryListCommand;
import com.particle.openplatform.client.openapirecord.dto.data.OpenplatformOpenapiRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放平台开放接口调用记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:13:46
 */
@Service
@CatchAndLog
public class OpenplatformOpenapiRecordRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiRecordRepresentationApplicationService {

    private OpenplatformOpenapiRecordQueryCommandExecutor openplatformOpenapiRecordQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformOpenapiRecordVO> queryDetail(IdCommand detailCommand) {
        return openplatformOpenapiRecordQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public PageResponse<OpenplatformOpenapiRecordVO> pageQuery(OpenplatformOpenapiRecordPageQueryCommand openplatformOpenapiRecordPageQueryCommand) {
        return openplatformOpenapiRecordQueryCommandExecutor.execute(openplatformOpenapiRecordPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformOpenapiRecordVO> queryList(OpenplatformOpenapiRecordQueryListCommand openplatformOpenapiRecordQueryListCommand) {
        return openplatformOpenapiRecordQueryCommandExecutor.execute(openplatformOpenapiRecordQueryListCommand);
    }

    @Autowired
    public void setOpenplatformOpenapiRecordQueryCommandExecutor(OpenplatformOpenapiRecordQueryCommandExecutor openplatformOpenapiRecordQueryCommandExecutor) {
        this.openplatformOpenapiRecordQueryCommandExecutor = openplatformOpenapiRecordQueryCommandExecutor;
    }
}
