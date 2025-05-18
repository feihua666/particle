package com.particle.tools.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.Response;
import com.particle.tools.app.executor.ParticleAddFieldCommandExecutor;
import com.particle.tools.app.executor.ParticleAddWarehouseAndExWarehouseCommandExecutor;
import com.particle.tools.app.executor.ParticleDeleteModelServiceCommandExecutor;
import com.particle.tools.client.api.IParticleApplicationService;
import com.particle.tools.client.dto.command.AddFieldCommand;
import com.particle.tools.client.dto.command.AddWarehouseAndExWarehouseCommand;
import com.particle.tools.client.dto.command.DeleteModelServiceCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 企业年报行政许可 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:15
 */
@Transactional
@Service
@CatchAndLog
public class ParticleApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IParticleApplicationService {

    private ParticleAddFieldCommandExecutor particleAddFieldCommandExecutor;
    private ParticleDeleteModelServiceCommandExecutor particleDeleteModelServiceCommandExecutor;
    private ParticleAddWarehouseAndExWarehouseCommandExecutor particleAddWarehouseAndExWarehouseCommandExecutor;


    @Override
    public Response addField(AddFieldCommand addFieldCommand) {
        return particleAddFieldCommandExecutor.addField(addFieldCommand);
    }

    @Override
    public Response deleteModelService(DeleteModelServiceCommand deleteModelServiceCommand) {
        return particleDeleteModelServiceCommandExecutor.deleteModelService(deleteModelServiceCommand);
    }

    @Override
    public Response addWarehouseAndExWarehouse(AddWarehouseAndExWarehouseCommand addWarehouseAndExWarehouseCommand) {
        return particleAddWarehouseAndExWarehouseCommandExecutor.addWarehouseAndExWarehouse(addWarehouseAndExWarehouseCommand);
    }


    @Autowired
    public void setParticleAddFieldCommandExecutor(ParticleAddFieldCommandExecutor particleAddFieldCommandExecutor) {
        this.particleAddFieldCommandExecutor = particleAddFieldCommandExecutor;
    }
    @Autowired
    public void setParticleDeleteModelServiceCommandExecutor(ParticleDeleteModelServiceCommandExecutor particleDeleteModelServiceCommandExecutor) {
        this.particleDeleteModelServiceCommandExecutor = particleDeleteModelServiceCommandExecutor;
    }
    @Autowired
    public void setParticleAddWarehouseAndExWarehouseCommandExecutor(ParticleAddWarehouseAndExWarehouseCommandExecutor particleAddWarehouseAndExWarehouseCommandExecutor) {
        this.particleAddWarehouseAndExWarehouseCommandExecutor = particleAddWarehouseAndExWarehouseCommandExecutor;
    }
}
