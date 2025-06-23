package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyPrimeStaffPositionCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyPrimeStaffPositionDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyPrimeStaffPositionUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyPrimeStaffPositionCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyPrimeStaffPositionUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyPrimeStaffPositionApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyPrimeStaffPositionCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyPrimeStaffPositionVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyPrimeStaffPositionWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPrimeStaffPositionExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyPrimeStaffPositionWarehouseCommandExecutor;
/**
 * <p>
 * 企业主要人员职位 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-22 15:07:33
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyPrimeStaffPositionApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyPrimeStaffPositionApplicationService {

    private DataCompanyPrimeStaffPositionCreateCommandExecutor dataCompanyPrimeStaffPositionCreateCommandExecutor;

    private DataCompanyPrimeStaffPositionDeleteCommandExecutor dataCompanyPrimeStaffPositionDeleteCommandExecutor;

    private DataCompanyPrimeStaffPositionUpdateCommandExecutor dataCompanyPrimeStaffPositionUpdateCommandExecutor;

    private DataCompanyPrimeStaffPositionCommandExecutor dataCompanyPrimeStaffPositionCommandExecutor;

    private DataCompanyPrimeStaffPositionWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyPrimeStaffPositionVO> create(DataCompanyPrimeStaffPositionCreateCommand dataCompanyPrimeStaffPositionCreateCommand) {
        return dataCompanyPrimeStaffPositionCreateCommandExecutor.execute(dataCompanyPrimeStaffPositionCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyPrimeStaffPositionVO> delete(IdCommand deleteCommand) {
        return dataCompanyPrimeStaffPositionDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyPrimeStaffPositionVO> update(DataCompanyPrimeStaffPositionUpdateCommand dataCompanyPrimeStaffPositionUpdateCommand) {
        return dataCompanyPrimeStaffPositionUpdateCommandExecutor.execute(dataCompanyPrimeStaffPositionUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyPrimeStaffPositionExWarehouseVO> warehouse(DataCompanyPrimeStaffPositionWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyPrimeStaffPositionCreateCommandExecutor(DataCompanyPrimeStaffPositionCreateCommandExecutor dataCompanyPrimeStaffPositionCreateCommandExecutor) {
        this.dataCompanyPrimeStaffPositionCreateCommandExecutor = dataCompanyPrimeStaffPositionCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyPrimeStaffPositionDeleteCommandExecutor(DataCompanyPrimeStaffPositionDeleteCommandExecutor dataCompanyPrimeStaffPositionDeleteCommandExecutor) {
        this.dataCompanyPrimeStaffPositionDeleteCommandExecutor = dataCompanyPrimeStaffPositionDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyPrimeStaffPositionUpdateCommandExecutor(DataCompanyPrimeStaffPositionUpdateCommandExecutor dataCompanyPrimeStaffPositionUpdateCommandExecutor) {
        this.dataCompanyPrimeStaffPositionUpdateCommandExecutor = dataCompanyPrimeStaffPositionUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyPrimeStaffPositionCommandExecutor(DataCompanyPrimeStaffPositionCommandExecutor dataCompanyPrimeStaffPositionCommandExecutor) {
        this.dataCompanyPrimeStaffPositionCommandExecutor = dataCompanyPrimeStaffPositionCommandExecutor;
    }
    @Autowired
    public void setDataCompanyPrimeStaffPositionWarehouseCommandExecutor(DataCompanyPrimeStaffPositionWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}