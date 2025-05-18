package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyAnnualReportChangeCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportChangeDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportChangeUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportChangeCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportChangeUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyAnnualReportChangeApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportChangeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportChangeVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportChangeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportChangeExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyAnnualReportChangeWarehouseCommandExecutor;
/**
 * <p>
 * 企业年报变更 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:43
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyAnnualReportChangeApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAnnualReportChangeApplicationService {

    private DataCompanyAnnualReportChangeCreateCommandExecutor dataCompanyAnnualReportChangeCreateCommandExecutor;

    private DataCompanyAnnualReportChangeDeleteCommandExecutor dataCompanyAnnualReportChangeDeleteCommandExecutor;

    private DataCompanyAnnualReportChangeUpdateCommandExecutor dataCompanyAnnualReportChangeUpdateCommandExecutor;

    private DataCompanyAnnualReportChangeCommandExecutor dataCompanyAnnualReportChangeCommandExecutor;

    private DataCompanyAnnualReportChangeWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAnnualReportChangeVO> create(DataCompanyAnnualReportChangeCreateCommand dataCompanyAnnualReportChangeCreateCommand) {
        return dataCompanyAnnualReportChangeCreateCommandExecutor.execute(dataCompanyAnnualReportChangeCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportChangeVO> delete(IdCommand deleteCommand) {
        return dataCompanyAnnualReportChangeDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportChangeVO> update(DataCompanyAnnualReportChangeUpdateCommand dataCompanyAnnualReportChangeUpdateCommand) {
        return dataCompanyAnnualReportChangeUpdateCommandExecutor.execute(dataCompanyAnnualReportChangeUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyAnnualReportChangeExWarehouseVO> warehouse(DataCompanyAnnualReportChangeWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyAnnualReportChangeCreateCommandExecutor(DataCompanyAnnualReportChangeCreateCommandExecutor dataCompanyAnnualReportChangeCreateCommandExecutor) {
        this.dataCompanyAnnualReportChangeCreateCommandExecutor = dataCompanyAnnualReportChangeCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyAnnualReportChangeDeleteCommandExecutor(DataCompanyAnnualReportChangeDeleteCommandExecutor dataCompanyAnnualReportChangeDeleteCommandExecutor) {
        this.dataCompanyAnnualReportChangeDeleteCommandExecutor = dataCompanyAnnualReportChangeDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportChangeUpdateCommandExecutor(DataCompanyAnnualReportChangeUpdateCommandExecutor dataCompanyAnnualReportChangeUpdateCommandExecutor) {
        this.dataCompanyAnnualReportChangeUpdateCommandExecutor = dataCompanyAnnualReportChangeUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportChangeCommandExecutor(DataCompanyAnnualReportChangeCommandExecutor dataCompanyAnnualReportChangeCommandExecutor) {
        this.dataCompanyAnnualReportChangeCommandExecutor = dataCompanyAnnualReportChangeCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportChangeWarehouseCommandExecutor(DataCompanyAnnualReportChangeWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}