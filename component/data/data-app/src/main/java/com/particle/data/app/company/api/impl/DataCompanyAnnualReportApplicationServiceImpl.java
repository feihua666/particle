package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyAnnualReportCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyAnnualReportApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyAnnualReportWarehouseCommandExecutor;
/**
 * <p>
 * 企业年报 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:58
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyAnnualReportApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAnnualReportApplicationService {

    private DataCompanyAnnualReportCreateCommandExecutor dataCompanyAnnualReportCreateCommandExecutor;

    private DataCompanyAnnualReportDeleteCommandExecutor dataCompanyAnnualReportDeleteCommandExecutor;

    private DataCompanyAnnualReportUpdateCommandExecutor dataCompanyAnnualReportUpdateCommandExecutor;

    private DataCompanyAnnualReportCommandExecutor dataCompanyAnnualReportCommandExecutor;

    private DataCompanyAnnualReportWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAnnualReportVO> create(DataCompanyAnnualReportCreateCommand dataCompanyAnnualReportCreateCommand) {
        return dataCompanyAnnualReportCreateCommandExecutor.execute(dataCompanyAnnualReportCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportVO> delete(IdCommand deleteCommand) {
        return dataCompanyAnnualReportDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportVO> update(DataCompanyAnnualReportUpdateCommand dataCompanyAnnualReportUpdateCommand) {
        return dataCompanyAnnualReportUpdateCommandExecutor.execute(dataCompanyAnnualReportUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyAnnualReportExWarehouseVO> warehouse(DataCompanyAnnualReportWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyAnnualReportCreateCommandExecutor(DataCompanyAnnualReportCreateCommandExecutor dataCompanyAnnualReportCreateCommandExecutor) {
        this.dataCompanyAnnualReportCreateCommandExecutor = dataCompanyAnnualReportCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyAnnualReportDeleteCommandExecutor(DataCompanyAnnualReportDeleteCommandExecutor dataCompanyAnnualReportDeleteCommandExecutor) {
        this.dataCompanyAnnualReportDeleteCommandExecutor = dataCompanyAnnualReportDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportUpdateCommandExecutor(DataCompanyAnnualReportUpdateCommandExecutor dataCompanyAnnualReportUpdateCommandExecutor) {
        this.dataCompanyAnnualReportUpdateCommandExecutor = dataCompanyAnnualReportUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportCommandExecutor(DataCompanyAnnualReportCommandExecutor dataCompanyAnnualReportCommandExecutor) {
        this.dataCompanyAnnualReportCommandExecutor = dataCompanyAnnualReportCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportWarehouseCommandExecutor(DataCompanyAnnualReportWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}