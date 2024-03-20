package com.particle.openplatform.app.doc.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.executor.representation.OpenplatformDocApiDocTemplateResponseCodeQueryCommandExecutor;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocApiDocTemplateResponseCodeRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateResponseCodePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateResponseCodeQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateResponseCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放接口文档模板响应码 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:49:10
 */
@Service
@CatchAndLog
public class OpenplatformDocApiDocTemplateResponseCodeRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformDocApiDocTemplateResponseCodeRepresentationApplicationService {

    private OpenplatformDocApiDocTemplateResponseCodeQueryCommandExecutor openplatformDocApiDocTemplateResponseCodeQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformDocApiDocTemplateResponseCodeVO> queryDetail(IdCommand detailCommand) {
        return openplatformDocApiDocTemplateResponseCodeQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformDocApiDocTemplateResponseCodeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformDocApiDocTemplateResponseCodeQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformDocApiDocTemplateResponseCodeVO> pageQuery(OpenplatformDocApiDocTemplateResponseCodePageQueryCommand openplatformDocApiDocTemplateResponseCodePageQueryCommand) {
        return openplatformDocApiDocTemplateResponseCodeQueryCommandExecutor.execute(openplatformDocApiDocTemplateResponseCodePageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformDocApiDocTemplateResponseCodeVO> queryList(OpenplatformDocApiDocTemplateResponseCodeQueryListCommand openplatformDocApiDocTemplateResponseCodeQueryListCommand) {
        return openplatformDocApiDocTemplateResponseCodeQueryCommandExecutor.execute(openplatformDocApiDocTemplateResponseCodeQueryListCommand);
    }

    @Autowired
    public void setOpenplatformDocApiDocTemplateResponseCodeQueryCommandExecutor(OpenplatformDocApiDocTemplateResponseCodeQueryCommandExecutor openplatformDocApiDocTemplateResponseCodeQueryCommandExecutor) {
        this.openplatformDocApiDocTemplateResponseCodeQueryCommandExecutor = openplatformDocApiDocTemplateResponseCodeQueryCommandExecutor;
    }
}
