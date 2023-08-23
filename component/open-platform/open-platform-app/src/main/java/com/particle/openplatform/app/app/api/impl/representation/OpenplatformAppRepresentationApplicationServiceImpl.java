package com.particle.openplatform.app.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.app.executor.representation.OpenplatformAppQueryCommandExecutor;
import com.particle.openplatform.client.app.api.representation.IOpenplatformAppRepresentationApplicationService;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppPageQueryCommand;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppQueryListCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放平台应用 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:40:15
 */
@Service
@CatchAndLog
public class OpenplatformAppRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformAppRepresentationApplicationService {

    private OpenplatformAppQueryCommandExecutor openplatformAppQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformAppVO> queryDetail(IdCommand detailCommand) {
        return openplatformAppQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformAppVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformAppQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformAppVO> pageQuery(OpenplatformAppPageQueryCommand openplatformAppPageQueryCommand) {
        return openplatformAppQueryCommandExecutor.execute(openplatformAppPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformAppVO> queryList(OpenplatformAppQueryListCommand openplatformAppQueryListCommand) {
        return openplatformAppQueryCommandExecutor.execute(openplatformAppQueryListCommand);
    }

    @Autowired
    public void setOpenplatformAppQueryCommandExecutor(OpenplatformAppQueryCommandExecutor openplatformAppQueryCommandExecutor) {
        this.openplatformAppQueryCommandExecutor = openplatformAppQueryCommandExecutor;
    }
}
