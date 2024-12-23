package com.particle.openplatform.app.openapi.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.openapi.executor.OpenplatformOpenapiLimitRuleCommandExecutor;
import com.particle.openplatform.app.openapi.executor.OpenplatformOpenapiLimitRuleCreateCommandExecutor;
import com.particle.openplatform.app.openapi.executor.OpenplatformOpenapiLimitRuleDeleteCommandExecutor;
import com.particle.openplatform.app.openapi.executor.OpenplatformOpenapiLimitRuleUpdateCommandExecutor;
import com.particle.openplatform.client.openapi.api.IOpenplatformOpenapiLimitRuleApplicationService;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiLimitRuleCreateCommand;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiLimitRuleUpdateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiLimitRuleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放平台开放接口限制规则 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-10-14 11:03:30
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformOpenapiLimitRuleApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformOpenapiLimitRuleApplicationService {

    private OpenplatformOpenapiLimitRuleCreateCommandExecutor openplatformOpenapiLimitRuleCreateCommandExecutor;

    private OpenplatformOpenapiLimitRuleDeleteCommandExecutor openplatformOpenapiLimitRuleDeleteCommandExecutor;

    private OpenplatformOpenapiLimitRuleUpdateCommandExecutor openplatformOpenapiLimitRuleUpdateCommandExecutor;

    private OpenplatformOpenapiLimitRuleCommandExecutor openplatformOpenapiLimitRuleCommandExecutor;


    @Override
    public SingleResponse<OpenplatformOpenapiLimitRuleVO> create(OpenplatformOpenapiLimitRuleCreateCommand openplatformOpenapiLimitRuleCreateCommand) {
        return openplatformOpenapiLimitRuleCreateCommandExecutor.execute(openplatformOpenapiLimitRuleCreateCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiLimitRuleVO> delete(IdCommand deleteCommand) {
        return openplatformOpenapiLimitRuleDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<OpenplatformOpenapiLimitRuleVO> update(OpenplatformOpenapiLimitRuleUpdateCommand openplatformOpenapiLimitRuleUpdateCommand) {
        return openplatformOpenapiLimitRuleUpdateCommandExecutor.execute(openplatformOpenapiLimitRuleUpdateCommand);
    }


    @Autowired
    public void setOpenplatformOpenapiLimitRuleCreateCommandExecutor(OpenplatformOpenapiLimitRuleCreateCommandExecutor openplatformOpenapiLimitRuleCreateCommandExecutor) {
        this.openplatformOpenapiLimitRuleCreateCommandExecutor = openplatformOpenapiLimitRuleCreateCommandExecutor;
    }

    @Autowired
    public void setOpenplatformOpenapiLimitRuleDeleteCommandExecutor(OpenplatformOpenapiLimitRuleDeleteCommandExecutor openplatformOpenapiLimitRuleDeleteCommandExecutor) {
        this.openplatformOpenapiLimitRuleDeleteCommandExecutor = openplatformOpenapiLimitRuleDeleteCommandExecutor;
    }
    @Autowired
    public void setOpenplatformOpenapiLimitRuleUpdateCommandExecutor(OpenplatformOpenapiLimitRuleUpdateCommandExecutor openplatformOpenapiLimitRuleUpdateCommandExecutor) {
        this.openplatformOpenapiLimitRuleUpdateCommandExecutor = openplatformOpenapiLimitRuleUpdateCommandExecutor;
    }
    @Autowired
    public void setOpenplatformOpenapiLimitRuleCommandExecutor(OpenplatformOpenapiLimitRuleCommandExecutor openplatformOpenapiLimitRuleCommandExecutor) {
        this.openplatformOpenapiLimitRuleCommandExecutor = openplatformOpenapiLimitRuleCommandExecutor;
    }
}
