package com.particle.openplatform.app.doc.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.executor.representation.OpenplatformDocApiDocQueryCommandExecutor;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocApiDocRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放接口文档 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:37
 */
@Service
@CatchAndLog
public class OpenplatformDocApiDocRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformDocApiDocRepresentationApplicationService {

    private OpenplatformDocApiDocQueryCommandExecutor openplatformDocApiDocQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformDocApiDocVO> queryDetail(IdCommand detailCommand) {
        return openplatformDocApiDocQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformDocApiDocVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformDocApiDocQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformDocApiDocVO> pageQuery(OpenplatformDocApiDocPageQueryCommand openplatformDocApiDocPageQueryCommand) {
        return openplatformDocApiDocQueryCommandExecutor.execute(openplatformDocApiDocPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformDocApiDocVO> queryList(OpenplatformDocApiDocQueryListCommand openplatformDocApiDocQueryListCommand) {
        return openplatformDocApiDocQueryCommandExecutor.execute(openplatformDocApiDocQueryListCommand);
    }

    @Autowired
    public void setOpenplatformDocApiDocQueryCommandExecutor(OpenplatformDocApiDocQueryCommandExecutor openplatformDocApiDocQueryCommandExecutor) {
        this.openplatformDocApiDocQueryCommandExecutor = openplatformDocApiDocQueryCommandExecutor;
    }
}
