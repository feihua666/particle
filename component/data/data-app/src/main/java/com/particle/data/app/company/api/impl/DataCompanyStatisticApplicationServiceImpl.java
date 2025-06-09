package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyStatisticCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyStatisticDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyStatisticUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyStatisticCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyStatisticUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyStatisticApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyStatisticCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyStatisticVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyStatisticWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyStatisticExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyStatisticWarehouseCommandExecutor;
/**
 * <p>
 * 企业统计 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-04 15:53:01
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyStatisticApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyStatisticApplicationService {

    private DataCompanyStatisticCreateCommandExecutor dataCompanyStatisticCreateCommandExecutor;

    private DataCompanyStatisticDeleteCommandExecutor dataCompanyStatisticDeleteCommandExecutor;

    private DataCompanyStatisticUpdateCommandExecutor dataCompanyStatisticUpdateCommandExecutor;

    private DataCompanyStatisticCommandExecutor dataCompanyStatisticCommandExecutor;

    private DataCompanyStatisticWarehouseCommandExecutor dataCompanyStatisticWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyStatisticVO> create(DataCompanyStatisticCreateCommand dataCompanyStatisticCreateCommand) {
        return dataCompanyStatisticCreateCommandExecutor.execute(dataCompanyStatisticCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyStatisticVO> delete(IdCommand deleteCommand) {
        return dataCompanyStatisticDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyStatisticVO> update(DataCompanyStatisticUpdateCommand dataCompanyStatisticUpdateCommand) {
        return dataCompanyStatisticUpdateCommandExecutor.execute(dataCompanyStatisticUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyStatisticExWarehouseVO> warehouse(DataCompanyStatisticWarehouseCommand dataCompanyStatisticWarehouseCommand) {
        return dataCompanyStatisticWarehouseCommandExecutor.warehouse(dataCompanyStatisticWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyStatisticCreateCommandExecutor(DataCompanyStatisticCreateCommandExecutor dataCompanyStatisticCreateCommandExecutor) {
        this.dataCompanyStatisticCreateCommandExecutor = dataCompanyStatisticCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyStatisticDeleteCommandExecutor(DataCompanyStatisticDeleteCommandExecutor dataCompanyStatisticDeleteCommandExecutor) {
        this.dataCompanyStatisticDeleteCommandExecutor = dataCompanyStatisticDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyStatisticUpdateCommandExecutor(DataCompanyStatisticUpdateCommandExecutor dataCompanyStatisticUpdateCommandExecutor) {
        this.dataCompanyStatisticUpdateCommandExecutor = dataCompanyStatisticUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyStatisticCommandExecutor(DataCompanyStatisticCommandExecutor dataCompanyStatisticCommandExecutor) {
        this.dataCompanyStatisticCommandExecutor = dataCompanyStatisticCommandExecutor;
    }
    @Autowired
    public void setDataCompanyStatisticWarehouseCommandExecutor(DataCompanyStatisticWarehouseCommandExecutor dataCompanyStatisticWarehouseCommandExecutor) {
        this.dataCompanyStatisticWarehouseCommandExecutor = dataCompanyStatisticWarehouseCommandExecutor;
    }

}