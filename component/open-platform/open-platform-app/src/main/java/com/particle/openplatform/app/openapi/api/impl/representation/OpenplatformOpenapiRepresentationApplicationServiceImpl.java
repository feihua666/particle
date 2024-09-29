package com.particle.openplatform.app.openapi.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.openapi.executor.representation.OpenplatformOpenapiQueryCommandExecutor;
import com.particle.openplatform.client.openapi.api.representation.IOpenplatformOpenapiRepresentationApplicationService;
import com.particle.openplatform.client.openapi.dto.command.representation.*;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiDownloadBatchQueryTemplateVO;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 开放平台开放接口 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-08 11:13:24
 */
@Service
@CatchAndLog
public class OpenplatformOpenapiRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiRepresentationApplicationService {

    private OpenplatformOpenapiQueryCommandExecutor openplatformOpenapiQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformOpenapiVO> queryDetail(IdCommand detailCommand) {
        return openplatformOpenapiQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformOpenapiQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformOpenapiVO> pageQuery(OpenplatformOpenapiPageQueryCommand openplatformOpenapiPageQueryCommand) {
        return openplatformOpenapiQueryCommandExecutor.execute(openplatformOpenapiPageQueryCommand);
    }

    @Override
    public SingleResponse<String> singleQuery(OpenplatformOpenapiSingleQueryCommand openplatformOpenapiSingleQueryCommand) {
        return openplatformOpenapiQueryCommandExecutor.singleQuery(openplatformOpenapiSingleQueryCommand);
    }
    @Transactional
    @Override
    public Response batchQuery(OpenplatformOpenapiBatchQueryCommand openplatformOpenapiBatchQueryCommand) {
        return openplatformOpenapiQueryCommandExecutor.batchQuery(openplatformOpenapiBatchQueryCommand);
    }

    @Override
    public OpenplatformOpenapiDownloadBatchQueryTemplateVO downloadBatchQueryTemplate(OpenplatformOpenapiDownloadBatchQueryTemplateCommand openplatformOpenapiDownloadBatchQueryTemplateCommand) {
        return openplatformOpenapiQueryCommandExecutor.downloadBatchQueryTemplate(openplatformOpenapiDownloadBatchQueryTemplateCommand);
    }

    @Override
    public MultiResponse<OpenplatformOpenapiVO> queryList(OpenplatformOpenapiQueryListCommand openplatformOpenapiQueryListCommand) {
        return openplatformOpenapiQueryCommandExecutor.execute(openplatformOpenapiQueryListCommand);
    }

    @Autowired
    public void setOpenplatformOpenapiQueryCommandExecutor(OpenplatformOpenapiQueryCommandExecutor openplatformOpenapiQueryCommandExecutor) {
        this.openplatformOpenapiQueryCommandExecutor = openplatformOpenapiQueryCommandExecutor;
    }
}
