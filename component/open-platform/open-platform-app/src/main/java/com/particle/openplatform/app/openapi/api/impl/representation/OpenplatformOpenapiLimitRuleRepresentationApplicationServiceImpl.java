package com.particle.openplatform.app.openapi.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.openapi.executor.representation.OpenplatformOpenapiLimitRuleQueryCommandExecutor;
import com.particle.openplatform.client.openapi.api.representation.IOpenplatformOpenapiLimitRuleRepresentationApplicationService;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiLimitRulePageQueryCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiLimitRuleQueryListCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiLimitRuleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放平台开放接口限制规则 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-10-14 11:03:30
 */
@Service
@CatchAndLog
public class OpenplatformOpenapiLimitRuleRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiLimitRuleRepresentationApplicationService {

    private OpenplatformOpenapiLimitRuleQueryCommandExecutor openplatformOpenapiLimitRuleQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformOpenapiLimitRuleVO> queryDetail(IdCommand detailCommand) {
        return openplatformOpenapiLimitRuleQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiLimitRuleVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformOpenapiLimitRuleQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformOpenapiLimitRuleVO> pageQuery(OpenplatformOpenapiLimitRulePageQueryCommand openplatformOpenapiLimitRulePageQueryCommand) {
        return openplatformOpenapiLimitRuleQueryCommandExecutor.execute(openplatformOpenapiLimitRulePageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformOpenapiLimitRuleVO> queryList(OpenplatformOpenapiLimitRuleQueryListCommand openplatformOpenapiLimitRuleQueryListCommand) {
        return openplatformOpenapiLimitRuleQueryCommandExecutor.execute(openplatformOpenapiLimitRuleQueryListCommand);
    }


    @Autowired
    public void setOpenplatformOpenapiLimitRuleQueryCommandExecutor(OpenplatformOpenapiLimitRuleQueryCommandExecutor openplatformOpenapiLimitRuleQueryCommandExecutor) {
        this.openplatformOpenapiLimitRuleQueryCommandExecutor = openplatformOpenapiLimitRuleQueryCommandExecutor;
    }
}
