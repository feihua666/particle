package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyPrimeStaffCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyPrimeStaffDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyPrimeStaffUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyPrimeStaffCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyPrimeStaffUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyPrimeStaffApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyPrimeStaffCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyPrimeStaffVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyPrimeStaffWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPrimeStaffExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyPrimeStaffWarehouseCommandExecutor;
/**
 * <p>
 * 企业主要人员 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:44
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyPrimeStaffApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyPrimeStaffApplicationService {

    private DataCompanyPrimeStaffCreateCommandExecutor dataCompanyPrimeStaffCreateCommandExecutor;

    private DataCompanyPrimeStaffDeleteCommandExecutor dataCompanyPrimeStaffDeleteCommandExecutor;

    private DataCompanyPrimeStaffUpdateCommandExecutor dataCompanyPrimeStaffUpdateCommandExecutor;

    private DataCompanyPrimeStaffCommandExecutor dataCompanyPrimeStaffCommandExecutor;

    private DataCompanyPrimeStaffWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyPrimeStaffVO> create(DataCompanyPrimeStaffCreateCommand dataCompanyPrimeStaffCreateCommand) {
        return dataCompanyPrimeStaffCreateCommandExecutor.execute(dataCompanyPrimeStaffCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyPrimeStaffVO> delete(IdCommand deleteCommand) {
        return dataCompanyPrimeStaffDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyPrimeStaffVO> update(DataCompanyPrimeStaffUpdateCommand dataCompanyPrimeStaffUpdateCommand) {
        return dataCompanyPrimeStaffUpdateCommandExecutor.execute(dataCompanyPrimeStaffUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyPrimeStaffExWarehouseVO> warehouse(DataCompanyPrimeStaffWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyPrimeStaffCreateCommandExecutor(DataCompanyPrimeStaffCreateCommandExecutor dataCompanyPrimeStaffCreateCommandExecutor) {
        this.dataCompanyPrimeStaffCreateCommandExecutor = dataCompanyPrimeStaffCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyPrimeStaffDeleteCommandExecutor(DataCompanyPrimeStaffDeleteCommandExecutor dataCompanyPrimeStaffDeleteCommandExecutor) {
        this.dataCompanyPrimeStaffDeleteCommandExecutor = dataCompanyPrimeStaffDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyPrimeStaffUpdateCommandExecutor(DataCompanyPrimeStaffUpdateCommandExecutor dataCompanyPrimeStaffUpdateCommandExecutor) {
        this.dataCompanyPrimeStaffUpdateCommandExecutor = dataCompanyPrimeStaffUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyPrimeStaffCommandExecutor(DataCompanyPrimeStaffCommandExecutor dataCompanyPrimeStaffCommandExecutor) {
        this.dataCompanyPrimeStaffCommandExecutor = dataCompanyPrimeStaffCommandExecutor;
    }
    @Autowired
    public void setDataCompanyPrimeStaffWarehouseCommandExecutor(DataCompanyPrimeStaffWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}