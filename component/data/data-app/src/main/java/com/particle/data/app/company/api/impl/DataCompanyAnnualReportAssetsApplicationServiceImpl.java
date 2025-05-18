package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyAnnualReportAssetsCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportAssetsDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportAssetsUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAnnualReportAssetsCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportAssetsUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyAnnualReportAssetsApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportAssetsCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportAssetsVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportAssetsWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportAssetsExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyAnnualReportAssetsWarehouseCommandExecutor;
/**
 * <p>
 * 企业资产状况信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:31
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyAnnualReportAssetsApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAnnualReportAssetsApplicationService {

    private DataCompanyAnnualReportAssetsCreateCommandExecutor dataCompanyAnnualReportAssetsCreateCommandExecutor;

    private DataCompanyAnnualReportAssetsDeleteCommandExecutor dataCompanyAnnualReportAssetsDeleteCommandExecutor;

    private DataCompanyAnnualReportAssetsUpdateCommandExecutor dataCompanyAnnualReportAssetsUpdateCommandExecutor;

    private DataCompanyAnnualReportAssetsCommandExecutor dataCompanyAnnualReportAssetsCommandExecutor;

    private DataCompanyAnnualReportAssetsWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAnnualReportAssetsVO> create(DataCompanyAnnualReportAssetsCreateCommand dataCompanyAnnualReportAssetsCreateCommand) {
        return dataCompanyAnnualReportAssetsCreateCommandExecutor.execute(dataCompanyAnnualReportAssetsCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportAssetsVO> delete(IdCommand deleteCommand) {
        return dataCompanyAnnualReportAssetsDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyAnnualReportAssetsVO> update(DataCompanyAnnualReportAssetsUpdateCommand dataCompanyAnnualReportAssetsUpdateCommand) {
        return dataCompanyAnnualReportAssetsUpdateCommandExecutor.execute(dataCompanyAnnualReportAssetsUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyAnnualReportAssetsExWarehouseVO> warehouse(DataCompanyAnnualReportAssetsWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyAnnualReportAssetsCreateCommandExecutor(DataCompanyAnnualReportAssetsCreateCommandExecutor dataCompanyAnnualReportAssetsCreateCommandExecutor) {
        this.dataCompanyAnnualReportAssetsCreateCommandExecutor = dataCompanyAnnualReportAssetsCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyAnnualReportAssetsDeleteCommandExecutor(DataCompanyAnnualReportAssetsDeleteCommandExecutor dataCompanyAnnualReportAssetsDeleteCommandExecutor) {
        this.dataCompanyAnnualReportAssetsDeleteCommandExecutor = dataCompanyAnnualReportAssetsDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportAssetsUpdateCommandExecutor(DataCompanyAnnualReportAssetsUpdateCommandExecutor dataCompanyAnnualReportAssetsUpdateCommandExecutor) {
        this.dataCompanyAnnualReportAssetsUpdateCommandExecutor = dataCompanyAnnualReportAssetsUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportAssetsCommandExecutor(DataCompanyAnnualReportAssetsCommandExecutor dataCompanyAnnualReportAssetsCommandExecutor) {
        this.dataCompanyAnnualReportAssetsCommandExecutor = dataCompanyAnnualReportAssetsCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAnnualReportAssetsWarehouseCommandExecutor(DataCompanyAnnualReportAssetsWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}