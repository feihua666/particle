package com.particle.openplatform.app.doc.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.executor.representation.OpenplatformDocApiDirRelQueryCommandExecutor;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocApiDirRelRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDirRelPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDirRelQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDirRelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放接口文档接口与目录关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:20
 */
@Service
@CatchAndLog
public class OpenplatformDocApiDirRelRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformDocApiDirRelRepresentationApplicationService {

    private OpenplatformDocApiDirRelQueryCommandExecutor openplatformDocApiDirRelQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformDocApiDirRelVO> queryDetail(IdCommand detailCommand) {
        return openplatformDocApiDirRelQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformDocApiDirRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformDocApiDirRelQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformDocApiDirRelVO> pageQuery(OpenplatformDocApiDirRelPageQueryCommand openplatformDocApiDirRelPageQueryCommand) {
        return openplatformDocApiDirRelQueryCommandExecutor.execute(openplatformDocApiDirRelPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformDocApiDirRelVO> queryList(OpenplatformDocApiDirRelQueryListCommand openplatformDocApiDirRelQueryListCommand) {
        return openplatformDocApiDirRelQueryCommandExecutor.execute(openplatformDocApiDirRelQueryListCommand);
    }

    @Autowired
    public void setOpenplatformDocApiDirRelQueryCommandExecutor(OpenplatformDocApiDirRelQueryCommandExecutor openplatformDocApiDirRelQueryCommandExecutor) {
        this.openplatformDocApiDirRelQueryCommandExecutor = openplatformDocApiDirRelQueryCommandExecutor;
    }
}
