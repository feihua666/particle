package com.particle.openplatform.app.doc.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.executor.representation.OpenplatformDocApiDocTemplateParamFieldQueryCommandExecutor;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocApiDocTemplateParamFieldRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateParamFieldPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateParamFieldQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateParamFieldVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放接口文档模板参数字段 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:56
 */
@Service
@CatchAndLog
public class OpenplatformDocApiDocTemplateParamFieldRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformDocApiDocTemplateParamFieldRepresentationApplicationService {

    private OpenplatformDocApiDocTemplateParamFieldQueryCommandExecutor openplatformDocApiDocTemplateParamFieldQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformDocApiDocTemplateParamFieldVO> queryDetail(IdCommand detailCommand) {
        return openplatformDocApiDocTemplateParamFieldQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformDocApiDocTemplateParamFieldVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformDocApiDocTemplateParamFieldQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformDocApiDocTemplateParamFieldVO> pageQuery(OpenplatformDocApiDocTemplateParamFieldPageQueryCommand openplatformDocApiDocTemplateParamFieldPageQueryCommand) {
        return openplatformDocApiDocTemplateParamFieldQueryCommandExecutor.execute(openplatformDocApiDocTemplateParamFieldPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformDocApiDocTemplateParamFieldVO> queryList(OpenplatformDocApiDocTemplateParamFieldQueryListCommand openplatformDocApiDocTemplateParamFieldQueryListCommand) {
        return openplatformDocApiDocTemplateParamFieldQueryCommandExecutor.execute(openplatformDocApiDocTemplateParamFieldQueryListCommand);
    }

    @Autowired
    public void setOpenplatformDocApiDocTemplateParamFieldQueryCommandExecutor(OpenplatformDocApiDocTemplateParamFieldQueryCommandExecutor openplatformDocApiDocTemplateParamFieldQueryCommandExecutor) {
        this.openplatformDocApiDocTemplateParamFieldQueryCommandExecutor = openplatformDocApiDocTemplateParamFieldQueryCommandExecutor;
    }
}
