package com.particle.openplatform.app.doc.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.executor.representation.OpenplatformDocApiDocTemplateExampleCodeQueryCommandExecutor;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocApiDocTemplateExampleCodeRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateExampleCodePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateExampleCodeQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateExampleCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放接口文档模板示例代码 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:26
 */
@Service
@CatchAndLog
public class OpenplatformDocApiDocTemplateExampleCodeRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformDocApiDocTemplateExampleCodeRepresentationApplicationService {

    private OpenplatformDocApiDocTemplateExampleCodeQueryCommandExecutor openplatformDocApiDocTemplateExampleCodeQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformDocApiDocTemplateExampleCodeVO> queryDetail(IdCommand detailCommand) {
        return openplatformDocApiDocTemplateExampleCodeQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformDocApiDocTemplateExampleCodeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformDocApiDocTemplateExampleCodeQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformDocApiDocTemplateExampleCodeVO> pageQuery(OpenplatformDocApiDocTemplateExampleCodePageQueryCommand openplatformDocApiDocTemplateExampleCodePageQueryCommand) {
        return openplatformDocApiDocTemplateExampleCodeQueryCommandExecutor.execute(openplatformDocApiDocTemplateExampleCodePageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformDocApiDocTemplateExampleCodeVO> queryList(OpenplatformDocApiDocTemplateExampleCodeQueryListCommand openplatformDocApiDocTemplateExampleCodeQueryListCommand) {
        return openplatformDocApiDocTemplateExampleCodeQueryCommandExecutor.execute(openplatformDocApiDocTemplateExampleCodeQueryListCommand);
    }

    @Autowired
    public void setOpenplatformDocApiDocTemplateExampleCodeQueryCommandExecutor(OpenplatformDocApiDocTemplateExampleCodeQueryCommandExecutor openplatformDocApiDocTemplateExampleCodeQueryCommandExecutor) {
        this.openplatformDocApiDocTemplateExampleCodeQueryCommandExecutor = openplatformDocApiDocTemplateExampleCodeQueryCommandExecutor;
    }
}
