package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprTrademarkLicenseCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkLicenseDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkLicenseUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprTrademarkLicenseCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkLicenseUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprTrademarkLicenseApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkLicenseCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkLicenseVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkLicenseWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkLicenseExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprTrademarkLicenseWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权商标许可信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:10
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprTrademarkLicenseApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprTrademarkLicenseApplicationService {

    private DataCompanyIprTrademarkLicenseCreateCommandExecutor dataCompanyIprTrademarkLicenseCreateCommandExecutor;

    private DataCompanyIprTrademarkLicenseDeleteCommandExecutor dataCompanyIprTrademarkLicenseDeleteCommandExecutor;

    private DataCompanyIprTrademarkLicenseUpdateCommandExecutor dataCompanyIprTrademarkLicenseUpdateCommandExecutor;

    private DataCompanyIprTrademarkLicenseCommandExecutor dataCompanyIprTrademarkLicenseCommandExecutor;

    private DataCompanyIprTrademarkLicenseWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprTrademarkLicenseVO> create(DataCompanyIprTrademarkLicenseCreateCommand dataCompanyIprTrademarkLicenseCreateCommand) {
        return dataCompanyIprTrademarkLicenseCreateCommandExecutor.execute(dataCompanyIprTrademarkLicenseCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprTrademarkLicenseVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprTrademarkLicenseDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprTrademarkLicenseVO> update(DataCompanyIprTrademarkLicenseUpdateCommand dataCompanyIprTrademarkLicenseUpdateCommand) {
        return dataCompanyIprTrademarkLicenseUpdateCommandExecutor.execute(dataCompanyIprTrademarkLicenseUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprTrademarkLicenseExWarehouseVO> warehouse(DataCompanyIprTrademarkLicenseWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprTrademarkLicenseCreateCommandExecutor(DataCompanyIprTrademarkLicenseCreateCommandExecutor dataCompanyIprTrademarkLicenseCreateCommandExecutor) {
        this.dataCompanyIprTrademarkLicenseCreateCommandExecutor = dataCompanyIprTrademarkLicenseCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprTrademarkLicenseDeleteCommandExecutor(DataCompanyIprTrademarkLicenseDeleteCommandExecutor dataCompanyIprTrademarkLicenseDeleteCommandExecutor) {
        this.dataCompanyIprTrademarkLicenseDeleteCommandExecutor = dataCompanyIprTrademarkLicenseDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkLicenseUpdateCommandExecutor(DataCompanyIprTrademarkLicenseUpdateCommandExecutor dataCompanyIprTrademarkLicenseUpdateCommandExecutor) {
        this.dataCompanyIprTrademarkLicenseUpdateCommandExecutor = dataCompanyIprTrademarkLicenseUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkLicenseCommandExecutor(DataCompanyIprTrademarkLicenseCommandExecutor dataCompanyIprTrademarkLicenseCommandExecutor) {
        this.dataCompanyIprTrademarkLicenseCommandExecutor = dataCompanyIprTrademarkLicenseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprTrademarkLicenseWarehouseCommandExecutor(DataCompanyIprTrademarkLicenseWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}