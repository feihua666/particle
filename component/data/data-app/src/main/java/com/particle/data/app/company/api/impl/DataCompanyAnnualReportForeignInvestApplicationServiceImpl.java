package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyAnnualReportForeignInvestCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportForeignInvestDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportForeignInvestUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportForeignInvestCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportForeignInvestUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyAnnualReportForeignInvestApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportForeignInvestCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportForeignInvestVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportForeignInvestWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportForeignInvestExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyAnnualReportForeignInvestWarehouseCommandExecutor;
/**
 * <p>
 * 企业年报对外投资 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:23
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyAnnualReportForeignInvestApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAnnualReportForeignInvestApplicationService {

    private DataCompanyAnnualReportForeignInvestCreateCommandExecutor dataCompanyAnnualReportForeignInvestCreateCommandExecutor;

    private DataCompanyAnnualReportForeignInvestDeleteCommandExecutor dataCompanyAnnualReportForeignInvestDeleteCommandExecutor;

    private DataCompanyAnnualReportForeignInvestUpdateCommandExecutor dataCompanyAnnualReportForeignInvestUpdateCommandExecutor;

    private DataCompanyAnnualReportForeignInvestCommandExecutor dataCompanyAnnualReportForeignInvestCommandExecutor;

    private DataCompanyAnnualReportForeignInvestWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAnnualReportForeignInvestVO> create(DataCompanyAnnualReportForeignInvestCreateCommand dataCompanyAnnualReportForeignInvestCreateCommand) {
        return dataCompanyAnnualReportForeignInvestCreateCommandExecutor.execute(dataCompanyAnnualReportForeignInvestCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportForeignInvestVO> delete(IdCommand deleteCommand) {
        return dataCompanyAnnualReportForeignInvestDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportForeignInvestVO> update(DataCompanyAnnualReportForeignInvestUpdateCommand dataCompanyAnnualReportForeignInvestUpdateCommand) {
        return dataCompanyAnnualReportForeignInvestUpdateCommandExecutor.execute(dataCompanyAnnualReportForeignInvestUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyAnnualReportForeignInvestExWarehouseVO> warehouse(DataCompanyAnnualReportForeignInvestWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyAnnualReportForeignInvestCreateCommandExecutor(DataCompanyAnnualReportForeignInvestCreateCommandExecutor dataCompanyAnnualReportForeignInvestCreateCommandExecutor) {
        this.dataCompanyAnnualReportForeignInvestCreateCommandExecutor = dataCompanyAnnualReportForeignInvestCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyAnnualReportForeignInvestDeleteCommandExecutor(DataCompanyAnnualReportForeignInvestDeleteCommandExecutor dataCompanyAnnualReportForeignInvestDeleteCommandExecutor) {
        this.dataCompanyAnnualReportForeignInvestDeleteCommandExecutor = dataCompanyAnnualReportForeignInvestDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportForeignInvestUpdateCommandExecutor(DataCompanyAnnualReportForeignInvestUpdateCommandExecutor dataCompanyAnnualReportForeignInvestUpdateCommandExecutor) {
        this.dataCompanyAnnualReportForeignInvestUpdateCommandExecutor = dataCompanyAnnualReportForeignInvestUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportForeignInvestCommandExecutor(DataCompanyAnnualReportForeignInvestCommandExecutor dataCompanyAnnualReportForeignInvestCommandExecutor) {
        this.dataCompanyAnnualReportForeignInvestCommandExecutor = dataCompanyAnnualReportForeignInvestCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportForeignInvestWarehouseCommandExecutor(DataCompanyAnnualReportForeignInvestWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}