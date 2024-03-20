package com.particle.openplatform.app.doc.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.executor.representation.OpenplatformDocDirQueryCommandExecutor;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocDirRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocDirPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocDirQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocDirVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放接口目录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:55:42
 */
@Service
@CatchAndLog
public class OpenplatformDocDirRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformDocDirRepresentationApplicationService {

    private OpenplatformDocDirQueryCommandExecutor openplatformDocDirQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformDocDirVO> queryDetail(IdCommand detailCommand) {
        return openplatformDocDirQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformDocDirVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformDocDirQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformDocDirVO> pageQuery(OpenplatformDocDirPageQueryCommand openplatformDocDirPageQueryCommand) {
        return openplatformDocDirQueryCommandExecutor.execute(openplatformDocDirPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformDocDirVO> queryList(OpenplatformDocDirQueryListCommand openplatformDocDirQueryListCommand) {
        return openplatformDocDirQueryCommandExecutor.execute(openplatformDocDirQueryListCommand);
    }

    @Autowired
    public void setOpenplatformDocDirQueryCommandExecutor(OpenplatformDocDirQueryCommandExecutor openplatformDocDirQueryCommandExecutor) {
        this.openplatformDocDirQueryCommandExecutor = openplatformDocDirQueryCommandExecutor;
    }
}
