package com.particle.openplatform.app.app.api.impl;

import com.particle.openplatform.app.app.executor.OpenplatformAppQuotaCreateCommandExecutor;
import com.particle.openplatform.app.app.executor.OpenplatformAppQuotaDeleteCommandExecutor;
import com.particle.openplatform.app.app.executor.OpenplatformAppQuotaUpdateCommandExecutor;
import com.particle.openplatform.app.app.executor.OpenplatformAppQuotaCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppQuotaUpdateCommand;
import com.particle.openplatform.client.app.api.IOpenplatformAppQuotaApplicationService;
import com.particle.openplatform.client.app.dto.command.OpenplatformAppQuotaCreateCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppQuotaVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 开放平台应用额度 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
@Transactional
@Service
@CatchAndLog
public class OpenplatformAppQuotaApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformAppQuotaApplicationService {

    private OpenplatformAppQuotaCreateCommandExecutor openplatformAppQuotaCreateCommandExecutor;

    private OpenplatformAppQuotaDeleteCommandExecutor openplatformAppQuotaDeleteCommandExecutor;

    private OpenplatformAppQuotaUpdateCommandExecutor openplatformAppQuotaUpdateCommandExecutor;

    private OpenplatformAppQuotaCommandExecutor openplatformAppQuotaCommandExecutor;


    @Override
    public SingleResponse<OpenplatformAppQuotaVO> create(OpenplatformAppQuotaCreateCommand openplatformAppQuotaCreateCommand) {
        return openplatformAppQuotaCreateCommandExecutor.execute(openplatformAppQuotaCreateCommand);
    }

    @Override
    public SingleResponse<OpenplatformAppQuotaVO> delete(IdCommand deleteCommand) {
        return openplatformAppQuotaDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<OpenplatformAppQuotaVO> update(OpenplatformAppQuotaUpdateCommand openplatformAppQuotaUpdateCommand) {
        return openplatformAppQuotaUpdateCommandExecutor.execute(openplatformAppQuotaUpdateCommand);
    }


    @Autowired
    public void setOpenplatformAppQuotaCreateCommandExecutor(OpenplatformAppQuotaCreateCommandExecutor openplatformAppQuotaCreateCommandExecutor) {
        this.openplatformAppQuotaCreateCommandExecutor = openplatformAppQuotaCreateCommandExecutor;
    }

    @Autowired
    public void setOpenplatformAppQuotaDeleteCommandExecutor(OpenplatformAppQuotaDeleteCommandExecutor openplatformAppQuotaDeleteCommandExecutor) {
        this.openplatformAppQuotaDeleteCommandExecutor = openplatformAppQuotaDeleteCommandExecutor;
    }
    @Autowired
    public void setOpenplatformAppQuotaUpdateCommandExecutor(OpenplatformAppQuotaUpdateCommandExecutor openplatformAppQuotaUpdateCommandExecutor) {
        this.openplatformAppQuotaUpdateCommandExecutor = openplatformAppQuotaUpdateCommandExecutor;
    }
    @Autowired
    public void setOpenplatformAppQuotaCommandExecutor(OpenplatformAppQuotaCommandExecutor openplatformAppQuotaCommandExecutor) {
        this.openplatformAppQuotaCommandExecutor = openplatformAppQuotaCommandExecutor;
    }
}
