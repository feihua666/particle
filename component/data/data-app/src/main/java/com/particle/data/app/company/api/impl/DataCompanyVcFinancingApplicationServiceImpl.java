package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyVcFinancingCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyVcFinancingDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyVcFinancingUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyVcFinancingCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyVcFinancingUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyVcFinancingApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyVcFinancingCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcFinancingVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcFinancingWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcFinancingExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyVcFinancingWarehouseCommandExecutor;
/**
 * <p>
 * 企业融资 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:43
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyVcFinancingApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyVcFinancingApplicationService {

    private DataCompanyVcFinancingCreateCommandExecutor dataCompanyVcFinancingCreateCommandExecutor;

    private DataCompanyVcFinancingDeleteCommandExecutor dataCompanyVcFinancingDeleteCommandExecutor;

    private DataCompanyVcFinancingUpdateCommandExecutor dataCompanyVcFinancingUpdateCommandExecutor;

    private DataCompanyVcFinancingCommandExecutor dataCompanyVcFinancingCommandExecutor;

    private DataCompanyVcFinancingWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyVcFinancingVO> create(DataCompanyVcFinancingCreateCommand dataCompanyVcFinancingCreateCommand) {
        return dataCompanyVcFinancingCreateCommandExecutor.execute(dataCompanyVcFinancingCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyVcFinancingVO> delete(IdCommand deleteCommand) {
        return dataCompanyVcFinancingDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyVcFinancingVO> update(DataCompanyVcFinancingUpdateCommand dataCompanyVcFinancingUpdateCommand) {
        return dataCompanyVcFinancingUpdateCommandExecutor.execute(dataCompanyVcFinancingUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyVcFinancingExWarehouseVO> warehouse(DataCompanyVcFinancingWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyVcFinancingCreateCommandExecutor(DataCompanyVcFinancingCreateCommandExecutor dataCompanyVcFinancingCreateCommandExecutor) {
        this.dataCompanyVcFinancingCreateCommandExecutor = dataCompanyVcFinancingCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyVcFinancingDeleteCommandExecutor(DataCompanyVcFinancingDeleteCommandExecutor dataCompanyVcFinancingDeleteCommandExecutor) {
        this.dataCompanyVcFinancingDeleteCommandExecutor = dataCompanyVcFinancingDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcFinancingUpdateCommandExecutor(DataCompanyVcFinancingUpdateCommandExecutor dataCompanyVcFinancingUpdateCommandExecutor) {
        this.dataCompanyVcFinancingUpdateCommandExecutor = dataCompanyVcFinancingUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcFinancingCommandExecutor(DataCompanyVcFinancingCommandExecutor dataCompanyVcFinancingCommandExecutor) {
        this.dataCompanyVcFinancingCommandExecutor = dataCompanyVcFinancingCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcFinancingWarehouseCommandExecutor(DataCompanyVcFinancingWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}