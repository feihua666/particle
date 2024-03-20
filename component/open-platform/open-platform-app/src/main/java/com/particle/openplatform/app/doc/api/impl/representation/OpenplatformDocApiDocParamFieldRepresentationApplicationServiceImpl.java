package com.particle.openplatform.app.doc.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.executor.representation.OpenplatformDocApiDocParamFieldQueryCommandExecutor;
import com.particle.openplatform.client.doc.api.representation.IOpenplatformDocApiDocParamFieldRepresentationApplicationService;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocParamFieldPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocParamFieldQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocParamFieldVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放接口文档参数字段 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:55
 */
@Service
@CatchAndLog
public class OpenplatformDocApiDocParamFieldRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformDocApiDocParamFieldRepresentationApplicationService {

    private OpenplatformDocApiDocParamFieldQueryCommandExecutor openplatformDocApiDocParamFieldQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformDocApiDocParamFieldVO> queryDetail(IdCommand detailCommand) {
        return openplatformDocApiDocParamFieldQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformDocApiDocParamFieldVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformDocApiDocParamFieldQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformDocApiDocParamFieldVO> pageQuery(OpenplatformDocApiDocParamFieldPageQueryCommand openplatformDocApiDocParamFieldPageQueryCommand) {
        return openplatformDocApiDocParamFieldQueryCommandExecutor.execute(openplatformDocApiDocParamFieldPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformDocApiDocParamFieldVO> queryList(OpenplatformDocApiDocParamFieldQueryListCommand openplatformDocApiDocParamFieldQueryListCommand) {
        return openplatformDocApiDocParamFieldQueryCommandExecutor.execute(openplatformDocApiDocParamFieldQueryListCommand);
    }

    @Autowired
    public void setOpenplatformDocApiDocParamFieldQueryCommandExecutor(OpenplatformDocApiDocParamFieldQueryCommandExecutor openplatformDocApiDocParamFieldQueryCommandExecutor) {
        this.openplatformDocApiDocParamFieldQueryCommandExecutor = openplatformDocApiDocParamFieldQueryCommandExecutor;
    }
}
