package com.particle.openplatform.app.doc.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.executor.representation.OpenplatformDocDirNameQueryCommandExecutor;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocDirNameRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocDirNamePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocDirNameQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocDirNameVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放接口目录名称 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:53:48
 */
@Service
@CatchAndLog
public class OpenplatformDocDirNameRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformDocDirNameRepresentationApplicationService {

    private OpenplatformDocDirNameQueryCommandExecutor openplatformDocDirNameQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformDocDirNameVO> queryDetail(IdCommand detailCommand) {
        return openplatformDocDirNameQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformDocDirNameVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformDocDirNameQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformDocDirNameVO> pageQuery(OpenplatformDocDirNamePageQueryCommand openplatformDocDirNamePageQueryCommand) {
        return openplatformDocDirNameQueryCommandExecutor.execute(openplatformDocDirNamePageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformDocDirNameVO> queryList(OpenplatformDocDirNameQueryListCommand openplatformDocDirNameQueryListCommand) {
        return openplatformDocDirNameQueryCommandExecutor.execute(openplatformDocDirNameQueryListCommand);
    }

    @Autowired
    public void setOpenplatformDocDirNameQueryCommandExecutor(OpenplatformDocDirNameQueryCommandExecutor openplatformDocDirNameQueryCommandExecutor) {
        this.openplatformDocDirNameQueryCommandExecutor = openplatformDocDirNameQueryCommandExecutor;
    }
}
