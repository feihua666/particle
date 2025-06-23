package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprTrademarkCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprTrademarkApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprTrademarkWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权商标 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:14:45
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprTrademarkApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprTrademarkApplicationService {

    private DataCompanyIprTrademarkCreateCommandExecutor dataCompanyIprTrademarkCreateCommandExecutor;

    private DataCompanyIprTrademarkDeleteCommandExecutor dataCompanyIprTrademarkDeleteCommandExecutor;

    private DataCompanyIprTrademarkUpdateCommandExecutor dataCompanyIprTrademarkUpdateCommandExecutor;

    private DataCompanyIprTrademarkCommandExecutor dataCompanyIprTrademarkCommandExecutor;

    private DataCompanyIprTrademarkWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprTrademarkVO> create(DataCompanyIprTrademarkCreateCommand dataCompanyIprTrademarkCreateCommand) {
        return dataCompanyIprTrademarkCreateCommandExecutor.execute(dataCompanyIprTrademarkCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprTrademarkVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprTrademarkDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprTrademarkVO> update(DataCompanyIprTrademarkUpdateCommand dataCompanyIprTrademarkUpdateCommand) {
        return dataCompanyIprTrademarkUpdateCommandExecutor.execute(dataCompanyIprTrademarkUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprTrademarkExWarehouseVO> warehouse(DataCompanyIprTrademarkWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprTrademarkCreateCommandExecutor(DataCompanyIprTrademarkCreateCommandExecutor dataCompanyIprTrademarkCreateCommandExecutor) {
        this.dataCompanyIprTrademarkCreateCommandExecutor = dataCompanyIprTrademarkCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprTrademarkDeleteCommandExecutor(DataCompanyIprTrademarkDeleteCommandExecutor dataCompanyIprTrademarkDeleteCommandExecutor) {
        this.dataCompanyIprTrademarkDeleteCommandExecutor = dataCompanyIprTrademarkDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkUpdateCommandExecutor(DataCompanyIprTrademarkUpdateCommandExecutor dataCompanyIprTrademarkUpdateCommandExecutor) {
        this.dataCompanyIprTrademarkUpdateCommandExecutor = dataCompanyIprTrademarkUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkCommandExecutor(DataCompanyIprTrademarkCommandExecutor dataCompanyIprTrademarkCommandExecutor) {
        this.dataCompanyIprTrademarkCommandExecutor = dataCompanyIprTrademarkCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkWarehouseCommandExecutor(DataCompanyIprTrademarkWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}