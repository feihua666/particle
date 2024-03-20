package com.particle.openplatform.app.doc.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.executor.representation.OpenplatformDocApiDocTemplateQueryCommandExecutor;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocApiDocTemplateRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplatePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放接口文档模板 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:39
 */
@Service
@CatchAndLog
public class OpenplatformDocApiDocTemplateRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformDocApiDocTemplateRepresentationApplicationService {

    private OpenplatformDocApiDocTemplateQueryCommandExecutor openplatformDocApiDocTemplateQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformDocApiDocTemplateVO> queryDetail(IdCommand detailCommand) {
        return openplatformDocApiDocTemplateQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformDocApiDocTemplateVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformDocApiDocTemplateQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformDocApiDocTemplateVO> pageQuery(OpenplatformDocApiDocTemplatePageQueryCommand openplatformDocApiDocTemplatePageQueryCommand) {
        return openplatformDocApiDocTemplateQueryCommandExecutor.execute(openplatformDocApiDocTemplatePageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformDocApiDocTemplateVO> queryList(OpenplatformDocApiDocTemplateQueryListCommand openplatformDocApiDocTemplateQueryListCommand) {
        return openplatformDocApiDocTemplateQueryCommandExecutor.execute(openplatformDocApiDocTemplateQueryListCommand);
    }

    @Autowired
    public void setOpenplatformDocApiDocTemplateQueryCommandExecutor(OpenplatformDocApiDocTemplateQueryCommandExecutor openplatformDocApiDocTemplateQueryCommandExecutor) {
        this.openplatformDocApiDocTemplateQueryCommandExecutor = openplatformDocApiDocTemplateQueryCommandExecutor;
    }
}
