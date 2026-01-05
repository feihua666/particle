package com.particle.componentadmin.app.api.impl;

import com.particle.componentadmin.app.executor.AdminComponentDependencyCreateCommandExecutor;
import com.particle.componentadmin.app.executor.AdminComponentDependencyDeleteCommandExecutor;
import com.particle.componentadmin.app.executor.AdminComponentDependencyUpdateCommandExecutor;
import com.particle.componentadmin.app.executor.AdminComponentDependencyCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.componentadmin.client.dto.command.AdminComponentDependencyUpdateCommand;
import com.particle.componentadmin.client.api.IAdminComponentDependencyApplicationService;
import com.particle.componentadmin.client.dto.command.AdminComponentDependencyCreateCommand;
import com.particle.componentadmin.client.dto.data.AdminComponentDependencyVO;

import com.particle.componentadmin.app.executor.AdminComponentDependencyCommandExecutor;
import com.particle.componentadmin.client.dto.command.ComponentAssignDependComponentCommand;
import com.particle.componentadmin.client.dto.command.DependComponentAssignComponentCommand;

import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 组件依赖关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:22:06
 */
@Transactional
@Service
@CatchAndLog
public class AdminComponentDependencyApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAdminComponentDependencyApplicationService {

    private AdminComponentDependencyCreateCommandExecutor adminComponentDependencyCreateCommandExecutor;

    private AdminComponentDependencyDeleteCommandExecutor adminComponentDependencyDeleteCommandExecutor;

    private AdminComponentDependencyUpdateCommandExecutor adminComponentDependencyUpdateCommandExecutor;

    private AdminComponentDependencyCommandExecutor adminComponentDependencyCommandExecutor;


    @Override
    public SingleResponse<AdminComponentDependencyVO> create(AdminComponentDependencyCreateCommand adminComponentDependencyCreateCommand) {
        return adminComponentDependencyCreateCommandExecutor.execute(adminComponentDependencyCreateCommand);
    }

    @Override
    public SingleResponse<AdminComponentDependencyVO> delete(IdCommand deleteCommand) {
        return adminComponentDependencyDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<AdminComponentDependencyVO> update(AdminComponentDependencyUpdateCommand adminComponentDependencyUpdateCommand) {
        return adminComponentDependencyUpdateCommandExecutor.execute(adminComponentDependencyUpdateCommand);
    }


    @Override
    public Response componentAssignDependComponent(ComponentAssignDependComponentCommand componentAssignDependComponentCommand){
        return adminComponentDependencyCommandExecutor.componentAssignDependComponent(componentAssignDependComponentCommand);
    }

    @Override
    public Response dependComponentAssignComponent(DependComponentAssignComponentCommand dependComponentAssignComponentCommand){
        return adminComponentDependencyCommandExecutor.dependComponentAssignComponent(dependComponentAssignComponentCommand);
    }

    @Override
    public Response deleteByComponentId(IdCommand idCommand){
        return adminComponentDependencyDeleteCommandExecutor.deleteByComponentId(idCommand);
    }

    @Override
    public Response deleteByDependComponentId(IdCommand idCommand){
        return adminComponentDependencyDeleteCommandExecutor.deleteByDependComponentId(idCommand);
    }

    @Autowired
    public void setAdminComponentDependencyCreateCommandExecutor(AdminComponentDependencyCreateCommandExecutor adminComponentDependencyCreateCommandExecutor) {
        this.adminComponentDependencyCreateCommandExecutor = adminComponentDependencyCreateCommandExecutor;
    }

    @Autowired
    public void setAdminComponentDependencyDeleteCommandExecutor(AdminComponentDependencyDeleteCommandExecutor adminComponentDependencyDeleteCommandExecutor) {
        this.adminComponentDependencyDeleteCommandExecutor = adminComponentDependencyDeleteCommandExecutor;
    }
    @Autowired
    public void setAdminComponentDependencyUpdateCommandExecutor(AdminComponentDependencyUpdateCommandExecutor adminComponentDependencyUpdateCommandExecutor) {
        this.adminComponentDependencyUpdateCommandExecutor = adminComponentDependencyUpdateCommandExecutor;
    }
    @Autowired
    public void setAdminComponentDependencyCommandExecutor(AdminComponentDependencyCommandExecutor adminComponentDependencyCommandExecutor) {
        this.adminComponentDependencyCommandExecutor = adminComponentDependencyCommandExecutor;
    }
}
