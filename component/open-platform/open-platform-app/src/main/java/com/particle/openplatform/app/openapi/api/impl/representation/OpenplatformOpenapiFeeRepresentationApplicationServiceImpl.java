package com.particle.openplatform.app.openapi.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.openapi.executor.representation.OpenplatformOpenapiFeeQueryCommandExecutor;
import com.particle.openplatform.client.openapi.api.representation.IOpenplatformOpenapiFeeRepresentationApplicationService;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiFeePageQueryCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiFeeQueryListCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiFeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放平台开放接口费用 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:59:32
 */
@Service
@CatchAndLog
public class OpenplatformOpenapiFeeRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiFeeRepresentationApplicationService {

    private OpenplatformOpenapiFeeQueryCommandExecutor openplatformOpenapiFeeQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformOpenapiFeeVO> queryDetail(IdCommand detailCommand) {
        return openplatformOpenapiFeeQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiFeeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformOpenapiFeeQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformOpenapiFeeVO> pageQuery(OpenplatformOpenapiFeePageQueryCommand openplatformOpenapiFeePageQueryCommand) {
        return openplatformOpenapiFeeQueryCommandExecutor.execute(openplatformOpenapiFeePageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformOpenapiFeeVO> queryList(OpenplatformOpenapiFeeQueryListCommand openplatformOpenapiFeeQueryListCommand) {
        return openplatformOpenapiFeeQueryCommandExecutor.execute(openplatformOpenapiFeeQueryListCommand);
    }

    @Autowired
    public void setOpenplatformOpenapiFeeQueryCommandExecutor(OpenplatformOpenapiFeeQueryCommandExecutor openplatformOpenapiFeeQueryCommandExecutor) {
        this.openplatformOpenapiFeeQueryCommandExecutor = openplatformOpenapiFeeQueryCommandExecutor;
    }
}
