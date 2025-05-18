package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyAnnualReportEquityChangeCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportEquityChangeDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportEquityChangeUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportEquityChangeCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportEquityChangeUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyAnnualReportEquityChangeApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportEquityChangeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportEquityChangeVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportEquityChangeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportEquityChangeExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyAnnualReportEquityChangeWarehouseCommandExecutor;
/**
 * <p>
 * 企业年报股权变更 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:57
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyAnnualReportEquityChangeApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAnnualReportEquityChangeApplicationService {

    private DataCompanyAnnualReportEquityChangeCreateCommandExecutor dataCompanyAnnualReportEquityChangeCreateCommandExecutor;

    private DataCompanyAnnualReportEquityChangeDeleteCommandExecutor dataCompanyAnnualReportEquityChangeDeleteCommandExecutor;

    private DataCompanyAnnualReportEquityChangeUpdateCommandExecutor dataCompanyAnnualReportEquityChangeUpdateCommandExecutor;

    private DataCompanyAnnualReportEquityChangeCommandExecutor dataCompanyAnnualReportEquityChangeCommandExecutor;

    private DataCompanyAnnualReportEquityChangeWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAnnualReportEquityChangeVO> create(DataCompanyAnnualReportEquityChangeCreateCommand dataCompanyAnnualReportEquityChangeCreateCommand) {
        return dataCompanyAnnualReportEquityChangeCreateCommandExecutor.execute(dataCompanyAnnualReportEquityChangeCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportEquityChangeVO> delete(IdCommand deleteCommand) {
        return dataCompanyAnnualReportEquityChangeDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportEquityChangeVO> update(DataCompanyAnnualReportEquityChangeUpdateCommand dataCompanyAnnualReportEquityChangeUpdateCommand) {
        return dataCompanyAnnualReportEquityChangeUpdateCommandExecutor.execute(dataCompanyAnnualReportEquityChangeUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyAnnualReportEquityChangeExWarehouseVO> warehouse(DataCompanyAnnualReportEquityChangeWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyAnnualReportEquityChangeCreateCommandExecutor(DataCompanyAnnualReportEquityChangeCreateCommandExecutor dataCompanyAnnualReportEquityChangeCreateCommandExecutor) {
        this.dataCompanyAnnualReportEquityChangeCreateCommandExecutor = dataCompanyAnnualReportEquityChangeCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyAnnualReportEquityChangeDeleteCommandExecutor(DataCompanyAnnualReportEquityChangeDeleteCommandExecutor dataCompanyAnnualReportEquityChangeDeleteCommandExecutor) {
        this.dataCompanyAnnualReportEquityChangeDeleteCommandExecutor = dataCompanyAnnualReportEquityChangeDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportEquityChangeUpdateCommandExecutor(DataCompanyAnnualReportEquityChangeUpdateCommandExecutor dataCompanyAnnualReportEquityChangeUpdateCommandExecutor) {
        this.dataCompanyAnnualReportEquityChangeUpdateCommandExecutor = dataCompanyAnnualReportEquityChangeUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportEquityChangeCommandExecutor(DataCompanyAnnualReportEquityChangeCommandExecutor dataCompanyAnnualReportEquityChangeCommandExecutor) {
        this.dataCompanyAnnualReportEquityChangeCommandExecutor = dataCompanyAnnualReportEquityChangeCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportEquityChangeWarehouseCommandExecutor(DataCompanyAnnualReportEquityChangeWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}