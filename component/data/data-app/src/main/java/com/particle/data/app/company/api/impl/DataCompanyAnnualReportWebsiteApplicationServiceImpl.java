package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyAnnualReportWebsiteCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportWebsiteDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportWebsiteUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportWebsiteCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportWebsiteUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyAnnualReportWebsiteApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportWebsiteCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportWebsiteVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportWebsiteWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportWebsiteExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyAnnualReportWebsiteWarehouseCommandExecutor;
/**
 * <p>
 * 企业年报网站网店 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:16
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyAnnualReportWebsiteApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAnnualReportWebsiteApplicationService {

    private DataCompanyAnnualReportWebsiteCreateCommandExecutor dataCompanyAnnualReportWebsiteCreateCommandExecutor;

    private DataCompanyAnnualReportWebsiteDeleteCommandExecutor dataCompanyAnnualReportWebsiteDeleteCommandExecutor;

    private DataCompanyAnnualReportWebsiteUpdateCommandExecutor dataCompanyAnnualReportWebsiteUpdateCommandExecutor;

    private DataCompanyAnnualReportWebsiteCommandExecutor dataCompanyAnnualReportWebsiteCommandExecutor;

    private DataCompanyAnnualReportWebsiteWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAnnualReportWebsiteVO> create(DataCompanyAnnualReportWebsiteCreateCommand dataCompanyAnnualReportWebsiteCreateCommand) {
        return dataCompanyAnnualReportWebsiteCreateCommandExecutor.execute(dataCompanyAnnualReportWebsiteCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportWebsiteVO> delete(IdCommand deleteCommand) {
        return dataCompanyAnnualReportWebsiteDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportWebsiteVO> update(DataCompanyAnnualReportWebsiteUpdateCommand dataCompanyAnnualReportWebsiteUpdateCommand) {
        return dataCompanyAnnualReportWebsiteUpdateCommandExecutor.execute(dataCompanyAnnualReportWebsiteUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyAnnualReportWebsiteExWarehouseVO> warehouse(DataCompanyAnnualReportWebsiteWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyAnnualReportWebsiteCreateCommandExecutor(DataCompanyAnnualReportWebsiteCreateCommandExecutor dataCompanyAnnualReportWebsiteCreateCommandExecutor) {
        this.dataCompanyAnnualReportWebsiteCreateCommandExecutor = dataCompanyAnnualReportWebsiteCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyAnnualReportWebsiteDeleteCommandExecutor(DataCompanyAnnualReportWebsiteDeleteCommandExecutor dataCompanyAnnualReportWebsiteDeleteCommandExecutor) {
        this.dataCompanyAnnualReportWebsiteDeleteCommandExecutor = dataCompanyAnnualReportWebsiteDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportWebsiteUpdateCommandExecutor(DataCompanyAnnualReportWebsiteUpdateCommandExecutor dataCompanyAnnualReportWebsiteUpdateCommandExecutor) {
        this.dataCompanyAnnualReportWebsiteUpdateCommandExecutor = dataCompanyAnnualReportWebsiteUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportWebsiteCommandExecutor(DataCompanyAnnualReportWebsiteCommandExecutor dataCompanyAnnualReportWebsiteCommandExecutor) {
        this.dataCompanyAnnualReportWebsiteCommandExecutor = dataCompanyAnnualReportWebsiteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportWebsiteWarehouseCommandExecutor(DataCompanyAnnualReportWebsiteWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}