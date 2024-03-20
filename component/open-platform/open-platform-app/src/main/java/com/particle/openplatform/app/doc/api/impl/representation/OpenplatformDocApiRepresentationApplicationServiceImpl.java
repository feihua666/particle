package com.particle.openplatform.app.doc.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.executor.representation.OpenplatformDocApiQueryCommandExecutor;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocApiRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDetailVO;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放接口文档接口 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
@Service
@CatchAndLog
public class OpenplatformDocApiRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformDocApiRepresentationApplicationService {

    private OpenplatformDocApiQueryCommandExecutor openplatformDocApiQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformDocApiVO> queryDetail(IdCommand detailCommand) {
        return openplatformDocApiQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformDocApiDetailVO> queryAllDetail(IdCommand detailCommand) {
        return openplatformDocApiQueryCommandExecutor.queryAllDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformDocApiVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformDocApiQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformDocApiVO> pageQuery(OpenplatformDocApiPageQueryCommand openplatformDocApiPageQueryCommand) {
        return openplatformDocApiQueryCommandExecutor.execute(openplatformDocApiPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformDocApiVO> queryList(OpenplatformDocApiQueryListCommand openplatformDocApiQueryListCommand) {
        return openplatformDocApiQueryCommandExecutor.execute(openplatformDocApiQueryListCommand);
    }

    @Autowired
    public void setOpenplatformDocApiQueryCommandExecutor(OpenplatformDocApiQueryCommandExecutor openplatformDocApiQueryCommandExecutor) {
        this.openplatformDocApiQueryCommandExecutor = openplatformDocApiQueryCommandExecutor;
    }
}
