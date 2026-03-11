package com.particle.componentadmin.app.api.impl;

import com.particle.componentadmin.app.executor.AdminComponentCreateCommandExecutor;
import com.particle.componentadmin.app.executor.AdminComponentDeleteCommandExecutor;
import com.particle.componentadmin.app.executor.AdminComponentUpdateCommandExecutor;
import com.particle.componentadmin.app.executor.AdminComponentCommandExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.componentadmin.client.dto.command.AdminComponentUpdateCommand;
import com.particle.componentadmin.client.api.IAdminComponentApplicationService;
import com.particle.componentadmin.client.dto.command.AdminComponentCreateCommand;
import com.particle.componentadmin.client.dto.data.AdminComponentVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 组件 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:21:31
 */
@Transactional
@Service
@CatchAndLog
public class AdminComponentApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAdminComponentApplicationService {

    private AdminComponentCreateCommandExecutor adminComponentCreateCommandExecutor;

    private AdminComponentDeleteCommandExecutor adminComponentDeleteCommandExecutor;

    private AdminComponentUpdateCommandExecutor adminComponentUpdateCommandExecutor;

    private AdminComponentCommandExecutor adminComponentCommandExecutor;


    @Override
    public SingleResponse<AdminComponentVO> create(AdminComponentCreateCommand adminComponentCreateCommand) {
        return adminComponentCreateCommandExecutor.execute(adminComponentCreateCommand);
    }

    @Override
    public SingleResponse<AdminComponentVO> delete(CommonIdCommand deleteCommand) {
        return adminComponentDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<AdminComponentVO> update(AdminComponentUpdateCommand adminComponentUpdateCommand) {
        return adminComponentUpdateCommandExecutor.execute(adminComponentUpdateCommand);
    }


    @Autowired
    public void setAdminComponentCreateCommandExecutor(AdminComponentCreateCommandExecutor adminComponentCreateCommandExecutor) {
        this.adminComponentCreateCommandExecutor = adminComponentCreateCommandExecutor;
    }

    @Autowired
    public void setAdminComponentDeleteCommandExecutor(AdminComponentDeleteCommandExecutor adminComponentDeleteCommandExecutor) {
        this.adminComponentDeleteCommandExecutor = adminComponentDeleteCommandExecutor;
    }
    @Autowired
    public void setAdminComponentUpdateCommandExecutor(AdminComponentUpdateCommandExecutor adminComponentUpdateCommandExecutor) {
        this.adminComponentUpdateCommandExecutor = adminComponentUpdateCommandExecutor;
    }
    @Autowired
    public void setAdminComponentCommandExecutor(AdminComponentCommandExecutor adminComponentCommandExecutor) {
        this.adminComponentCommandExecutor = adminComponentCommandExecutor;
    }
}
