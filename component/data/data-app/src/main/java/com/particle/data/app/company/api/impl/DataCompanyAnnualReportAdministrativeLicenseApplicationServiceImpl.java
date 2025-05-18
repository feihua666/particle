package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyAnnualReportAdministrativeLicenseCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportAdministrativeLicenseDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportAdministrativeLicenseUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportAdministrativeLicenseCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportAdministrativeLicenseUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyAnnualReportAdministrativeLicenseApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportAdministrativeLicenseCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportAdministrativeLicenseVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportAdministrativeLicenseWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyAnnualReportAdministrativeLicenseWarehouseCommandExecutor;
/**
 * <p>
 * 企业年报行政许可 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:15
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyAnnualReportAdministrativeLicenseApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAnnualReportAdministrativeLicenseApplicationService {

    private DataCompanyAnnualReportAdministrativeLicenseCreateCommandExecutor dataCompanyAnnualReportAdministrativeLicenseCreateCommandExecutor;

    private DataCompanyAnnualReportAdministrativeLicenseDeleteCommandExecutor dataCompanyAnnualReportAdministrativeLicenseDeleteCommandExecutor;

    private DataCompanyAnnualReportAdministrativeLicenseUpdateCommandExecutor dataCompanyAnnualReportAdministrativeLicenseUpdateCommandExecutor;

    private DataCompanyAnnualReportAdministrativeLicenseCommandExecutor dataCompanyAnnualReportAdministrativeLicenseCommandExecutor;

    private DataCompanyAnnualReportAdministrativeLicenseWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAnnualReportAdministrativeLicenseVO> create(DataCompanyAnnualReportAdministrativeLicenseCreateCommand dataCompanyAnnualReportAdministrativeLicenseCreateCommand) {
        return dataCompanyAnnualReportAdministrativeLicenseCreateCommandExecutor.execute(dataCompanyAnnualReportAdministrativeLicenseCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportAdministrativeLicenseVO> delete(IdCommand deleteCommand) {
        return dataCompanyAnnualReportAdministrativeLicenseDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportAdministrativeLicenseVO> update(DataCompanyAnnualReportAdministrativeLicenseUpdateCommand dataCompanyAnnualReportAdministrativeLicenseUpdateCommand) {
        return dataCompanyAnnualReportAdministrativeLicenseUpdateCommandExecutor.execute(dataCompanyAnnualReportAdministrativeLicenseUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO> warehouse(DataCompanyAnnualReportAdministrativeLicenseWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyAnnualReportAdministrativeLicenseCreateCommandExecutor(DataCompanyAnnualReportAdministrativeLicenseCreateCommandExecutor dataCompanyAnnualReportAdministrativeLicenseCreateCommandExecutor) {
        this.dataCompanyAnnualReportAdministrativeLicenseCreateCommandExecutor = dataCompanyAnnualReportAdministrativeLicenseCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyAnnualReportAdministrativeLicenseDeleteCommandExecutor(DataCompanyAnnualReportAdministrativeLicenseDeleteCommandExecutor dataCompanyAnnualReportAdministrativeLicenseDeleteCommandExecutor) {
        this.dataCompanyAnnualReportAdministrativeLicenseDeleteCommandExecutor = dataCompanyAnnualReportAdministrativeLicenseDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportAdministrativeLicenseUpdateCommandExecutor(DataCompanyAnnualReportAdministrativeLicenseUpdateCommandExecutor dataCompanyAnnualReportAdministrativeLicenseUpdateCommandExecutor) {
        this.dataCompanyAnnualReportAdministrativeLicenseUpdateCommandExecutor = dataCompanyAnnualReportAdministrativeLicenseUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportAdministrativeLicenseCommandExecutor(DataCompanyAnnualReportAdministrativeLicenseCommandExecutor dataCompanyAnnualReportAdministrativeLicenseCommandExecutor) {
        this.dataCompanyAnnualReportAdministrativeLicenseCommandExecutor = dataCompanyAnnualReportAdministrativeLicenseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportAdministrativeLicenseWarehouseCommandExecutor(DataCompanyAnnualReportAdministrativeLicenseWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}