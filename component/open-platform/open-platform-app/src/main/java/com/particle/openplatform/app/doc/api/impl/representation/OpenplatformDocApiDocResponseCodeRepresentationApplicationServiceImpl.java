package com.particle.openplatform.app.doc.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.executor.representation.OpenplatformDocApiDocResponseCodeQueryCommandExecutor;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocApiDocResponseCodeRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocResponseCodePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocResponseCodeQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocResponseCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放接口文档响应码 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 14:44:43
 */
@Service
@CatchAndLog
public class OpenplatformDocApiDocResponseCodeRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformDocApiDocResponseCodeRepresentationApplicationService {

    private OpenplatformDocApiDocResponseCodeQueryCommandExecutor openplatformDocApiDocResponseCodeQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformDocApiDocResponseCodeVO> queryDetail(IdCommand detailCommand) {
        return openplatformDocApiDocResponseCodeQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformDocApiDocResponseCodeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformDocApiDocResponseCodeQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformDocApiDocResponseCodeVO> pageQuery(OpenplatformDocApiDocResponseCodePageQueryCommand openplatformDocApiDocResponseCodePageQueryCommand) {
        return openplatformDocApiDocResponseCodeQueryCommandExecutor.execute(openplatformDocApiDocResponseCodePageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformDocApiDocResponseCodeVO> queryList(OpenplatformDocApiDocResponseCodeQueryListCommand openplatformDocApiDocResponseCodeQueryListCommand) {
        return openplatformDocApiDocResponseCodeQueryCommandExecutor.execute(openplatformDocApiDocResponseCodeQueryListCommand);
    }

    @Autowired
    public void setOpenplatformDocApiDocResponseCodeQueryCommandExecutor(OpenplatformDocApiDocResponseCodeQueryCommandExecutor openplatformDocApiDocResponseCodeQueryCommandExecutor) {
        this.openplatformDocApiDocResponseCodeQueryCommandExecutor = openplatformDocApiDocResponseCodeQueryCommandExecutor;
    }
}
