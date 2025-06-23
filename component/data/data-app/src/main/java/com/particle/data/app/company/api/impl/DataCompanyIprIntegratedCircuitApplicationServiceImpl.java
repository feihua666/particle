package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprIntegratedCircuitCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprIntegratedCircuitDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprIntegratedCircuitUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprIntegratedCircuitCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprIntegratedCircuitUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprIntegratedCircuitApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprIntegratedCircuitCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprIntegratedCircuitVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprIntegratedCircuitWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprIntegratedCircuitExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprIntegratedCircuitWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权集成电路 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:28
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprIntegratedCircuitApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprIntegratedCircuitApplicationService {

    private DataCompanyIprIntegratedCircuitCreateCommandExecutor dataCompanyIprIntegratedCircuitCreateCommandExecutor;

    private DataCompanyIprIntegratedCircuitDeleteCommandExecutor dataCompanyIprIntegratedCircuitDeleteCommandExecutor;

    private DataCompanyIprIntegratedCircuitUpdateCommandExecutor dataCompanyIprIntegratedCircuitUpdateCommandExecutor;

    private DataCompanyIprIntegratedCircuitCommandExecutor dataCompanyIprIntegratedCircuitCommandExecutor;

    private DataCompanyIprIntegratedCircuitWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprIntegratedCircuitVO> create(DataCompanyIprIntegratedCircuitCreateCommand dataCompanyIprIntegratedCircuitCreateCommand) {
        return dataCompanyIprIntegratedCircuitCreateCommandExecutor.execute(dataCompanyIprIntegratedCircuitCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprIntegratedCircuitVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprIntegratedCircuitDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprIntegratedCircuitVO> update(DataCompanyIprIntegratedCircuitUpdateCommand dataCompanyIprIntegratedCircuitUpdateCommand) {
        return dataCompanyIprIntegratedCircuitUpdateCommandExecutor.execute(dataCompanyIprIntegratedCircuitUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprIntegratedCircuitExWarehouseVO> warehouse(DataCompanyIprIntegratedCircuitWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprIntegratedCircuitCreateCommandExecutor(DataCompanyIprIntegratedCircuitCreateCommandExecutor dataCompanyIprIntegratedCircuitCreateCommandExecutor) {
        this.dataCompanyIprIntegratedCircuitCreateCommandExecutor = dataCompanyIprIntegratedCircuitCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprIntegratedCircuitDeleteCommandExecutor(DataCompanyIprIntegratedCircuitDeleteCommandExecutor dataCompanyIprIntegratedCircuitDeleteCommandExecutor) {
        this.dataCompanyIprIntegratedCircuitDeleteCommandExecutor = dataCompanyIprIntegratedCircuitDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprIntegratedCircuitUpdateCommandExecutor(DataCompanyIprIntegratedCircuitUpdateCommandExecutor dataCompanyIprIntegratedCircuitUpdateCommandExecutor) {
        this.dataCompanyIprIntegratedCircuitUpdateCommandExecutor = dataCompanyIprIntegratedCircuitUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprIntegratedCircuitCommandExecutor(DataCompanyIprIntegratedCircuitCommandExecutor dataCompanyIprIntegratedCircuitCommandExecutor) {
        this.dataCompanyIprIntegratedCircuitCommandExecutor = dataCompanyIprIntegratedCircuitCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprIntegratedCircuitWarehouseCommandExecutor(DataCompanyIprIntegratedCircuitWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}