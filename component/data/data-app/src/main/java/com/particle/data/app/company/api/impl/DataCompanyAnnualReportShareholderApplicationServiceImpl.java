package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyAnnualReportShareholderCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportShareholderDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportShareholderUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportShareholderCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportShareholderUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyAnnualReportShareholderApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportShareholderCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportShareholderVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportShareholderWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportShareholderExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyAnnualReportShareholderWarehouseCommandExecutor;
/**
 * <p>
 * 企业年报股东 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:37
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyAnnualReportShareholderApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAnnualReportShareholderApplicationService {

    private DataCompanyAnnualReportShareholderCreateCommandExecutor dataCompanyAnnualReportShareholderCreateCommandExecutor;

    private DataCompanyAnnualReportShareholderDeleteCommandExecutor dataCompanyAnnualReportShareholderDeleteCommandExecutor;

    private DataCompanyAnnualReportShareholderUpdateCommandExecutor dataCompanyAnnualReportShareholderUpdateCommandExecutor;

    private DataCompanyAnnualReportShareholderCommandExecutor dataCompanyAnnualReportShareholderCommandExecutor;

    private DataCompanyAnnualReportShareholderWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAnnualReportShareholderVO> create(DataCompanyAnnualReportShareholderCreateCommand dataCompanyAnnualReportShareholderCreateCommand) {
        return dataCompanyAnnualReportShareholderCreateCommandExecutor.execute(dataCompanyAnnualReportShareholderCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportShareholderVO> delete(IdCommand deleteCommand) {
        return dataCompanyAnnualReportShareholderDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportShareholderVO> update(DataCompanyAnnualReportShareholderUpdateCommand dataCompanyAnnualReportShareholderUpdateCommand) {
        return dataCompanyAnnualReportShareholderUpdateCommandExecutor.execute(dataCompanyAnnualReportShareholderUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyAnnualReportShareholderExWarehouseVO> warehouse(DataCompanyAnnualReportShareholderWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyAnnualReportShareholderCreateCommandExecutor(DataCompanyAnnualReportShareholderCreateCommandExecutor dataCompanyAnnualReportShareholderCreateCommandExecutor) {
        this.dataCompanyAnnualReportShareholderCreateCommandExecutor = dataCompanyAnnualReportShareholderCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyAnnualReportShareholderDeleteCommandExecutor(DataCompanyAnnualReportShareholderDeleteCommandExecutor dataCompanyAnnualReportShareholderDeleteCommandExecutor) {
        this.dataCompanyAnnualReportShareholderDeleteCommandExecutor = dataCompanyAnnualReportShareholderDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportShareholderUpdateCommandExecutor(DataCompanyAnnualReportShareholderUpdateCommandExecutor dataCompanyAnnualReportShareholderUpdateCommandExecutor) {
        this.dataCompanyAnnualReportShareholderUpdateCommandExecutor = dataCompanyAnnualReportShareholderUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportShareholderCommandExecutor(DataCompanyAnnualReportShareholderCommandExecutor dataCompanyAnnualReportShareholderCommandExecutor) {
        this.dataCompanyAnnualReportShareholderCommandExecutor = dataCompanyAnnualReportShareholderCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportShareholderWarehouseCommandExecutor(DataCompanyAnnualReportShareholderWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}