package com.particle.openplatform.app.doc.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.executor.representation.OpenplatformDocApiDocExampleCodeQueryCommandExecutor;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocApiDocExampleCodeRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocExampleCodePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocExampleCodeQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocExampleCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放接口文档示例代码 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:11
 */
@Service
@CatchAndLog
public class OpenplatformDocApiDocExampleCodeRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformDocApiDocExampleCodeRepresentationApplicationService {

    private OpenplatformDocApiDocExampleCodeQueryCommandExecutor openplatformDocApiDocExampleCodeQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformDocApiDocExampleCodeVO> queryDetail(IdCommand detailCommand) {
        return openplatformDocApiDocExampleCodeQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformDocApiDocExampleCodeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformDocApiDocExampleCodeQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformDocApiDocExampleCodeVO> pageQuery(OpenplatformDocApiDocExampleCodePageQueryCommand openplatformDocApiDocExampleCodePageQueryCommand) {
        return openplatformDocApiDocExampleCodeQueryCommandExecutor.execute(openplatformDocApiDocExampleCodePageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformDocApiDocExampleCodeVO> queryList(OpenplatformDocApiDocExampleCodeQueryListCommand openplatformDocApiDocExampleCodeQueryListCommand) {
        return openplatformDocApiDocExampleCodeQueryCommandExecutor.execute(openplatformDocApiDocExampleCodeQueryListCommand);
    }

    @Autowired
    public void setOpenplatformDocApiDocExampleCodeQueryCommandExecutor(OpenplatformDocApiDocExampleCodeQueryCommandExecutor openplatformDocApiDocExampleCodeQueryCommandExecutor) {
        this.openplatformDocApiDocExampleCodeQueryCommandExecutor = openplatformDocApiDocExampleCodeQueryCommandExecutor;
    }
}
