package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyPersonCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyPersonDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyPersonUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyPersonCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyPersonUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyPersonApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyPersonCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyPersonVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyPersonWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPersonExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyPersonWarehouseCommandExecutor;
/**
 * <p>
 * 企业个人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:50
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyPersonApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyPersonApplicationService {

    private DataCompanyPersonCreateCommandExecutor dataCompanyPersonCreateCommandExecutor;

    private DataCompanyPersonDeleteCommandExecutor dataCompanyPersonDeleteCommandExecutor;

    private DataCompanyPersonUpdateCommandExecutor dataCompanyPersonUpdateCommandExecutor;

    private DataCompanyPersonCommandExecutor dataCompanyPersonCommandExecutor;

    private DataCompanyPersonWarehouseCommandExecutor dataCompanyPersonWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyPersonVO> create(DataCompanyPersonCreateCommand dataCompanyPersonCreateCommand) {
        return dataCompanyPersonCreateCommandExecutor.execute(dataCompanyPersonCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyPersonVO> delete(IdCommand deleteCommand) {
        return dataCompanyPersonDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyPersonVO> update(DataCompanyPersonUpdateCommand dataCompanyPersonUpdateCommand) {
        return dataCompanyPersonUpdateCommandExecutor.execute(dataCompanyPersonUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyPersonExWarehouseVO> warehouse(DataCompanyPersonWarehouseCommand dataCompanyPersonWarehouseCommand) {
        return dataCompanyPersonWarehouseCommandExecutor.warehouse(dataCompanyPersonWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyPersonCreateCommandExecutor(DataCompanyPersonCreateCommandExecutor dataCompanyPersonCreateCommandExecutor) {
        this.dataCompanyPersonCreateCommandExecutor = dataCompanyPersonCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyPersonDeleteCommandExecutor(DataCompanyPersonDeleteCommandExecutor dataCompanyPersonDeleteCommandExecutor) {
        this.dataCompanyPersonDeleteCommandExecutor = dataCompanyPersonDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyPersonUpdateCommandExecutor(DataCompanyPersonUpdateCommandExecutor dataCompanyPersonUpdateCommandExecutor) {
        this.dataCompanyPersonUpdateCommandExecutor = dataCompanyPersonUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyPersonCommandExecutor(DataCompanyPersonCommandExecutor dataCompanyPersonCommandExecutor) {
        this.dataCompanyPersonCommandExecutor = dataCompanyPersonCommandExecutor;
    }
    @Autowired
    public void setDataCompanyPersonWarehouseCommandExecutor(DataCompanyPersonWarehouseCommandExecutor dataCompanyPersonWarehouseCommandExecutor) {
        this.dataCompanyPersonWarehouseCommandExecutor = dataCompanyPersonWarehouseCommandExecutor;
    }

}