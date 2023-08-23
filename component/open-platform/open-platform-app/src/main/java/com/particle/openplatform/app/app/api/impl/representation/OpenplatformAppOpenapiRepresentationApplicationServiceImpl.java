package com.particle.openplatform.app.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.app.executor.representation.OpenplatformAppOpenapiQueryCommandExecutor;
import com.particle.openplatform.client.app.api.representation.IOpenplatformAppOpenapiRepresentationApplicationService;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppOpenapiPageQueryCommand;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppOpenapiQueryListCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppOpenapiVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放平台应用与开放接口配置 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:49:06
 */
@Service
@CatchAndLog
public class OpenplatformAppOpenapiRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformAppOpenapiRepresentationApplicationService {

    private OpenplatformAppOpenapiQueryCommandExecutor openplatformAppOpenapiQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformAppOpenapiVO> queryDetail(IdCommand detailCommand) {
        return openplatformAppOpenapiQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformAppOpenapiVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformAppOpenapiQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformAppOpenapiVO> pageQuery(OpenplatformAppOpenapiPageQueryCommand openplatformAppOpenapiPageQueryCommand) {
        return openplatformAppOpenapiQueryCommandExecutor.execute(openplatformAppOpenapiPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformAppOpenapiVO> queryList(OpenplatformAppOpenapiQueryListCommand openplatformAppOpenapiQueryListCommand) {
        return openplatformAppOpenapiQueryCommandExecutor.execute(openplatformAppOpenapiQueryListCommand);
    }

    @Autowired
    public void setOpenplatformAppOpenapiQueryCommandExecutor(OpenplatformAppOpenapiQueryCommandExecutor openplatformAppOpenapiQueryCommandExecutor) {
        this.openplatformAppOpenapiQueryCommandExecutor = openplatformAppOpenapiQueryCommandExecutor;
    }
}
