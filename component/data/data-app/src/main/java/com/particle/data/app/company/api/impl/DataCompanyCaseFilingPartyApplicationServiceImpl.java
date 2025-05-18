package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyCaseFilingPartyCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyCaseFilingPartyDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyCaseFilingPartyUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyCaseFilingPartyCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyCaseFilingPartyUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyCaseFilingPartyApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyCaseFilingPartyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCaseFilingPartyVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCaseFilingPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCaseFilingPartyExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyCaseFilingPartyWarehouseCommandExecutor;
/**
 * <p>
 * 企业立案信息当事人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:50
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyCaseFilingPartyApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyCaseFilingPartyApplicationService {

    private DataCompanyCaseFilingPartyCreateCommandExecutor dataCompanyCaseFilingPartyCreateCommandExecutor;

    private DataCompanyCaseFilingPartyDeleteCommandExecutor dataCompanyCaseFilingPartyDeleteCommandExecutor;

    private DataCompanyCaseFilingPartyUpdateCommandExecutor dataCompanyCaseFilingPartyUpdateCommandExecutor;

    private DataCompanyCaseFilingPartyCommandExecutor dataCompanyCaseFilingPartyCommandExecutor;

    private DataCompanyCaseFilingPartyWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyCaseFilingPartyVO> create(DataCompanyCaseFilingPartyCreateCommand dataCompanyCaseFilingPartyCreateCommand) {
        return dataCompanyCaseFilingPartyCreateCommandExecutor.execute(dataCompanyCaseFilingPartyCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyCaseFilingPartyVO> delete(IdCommand deleteCommand) {
        return dataCompanyCaseFilingPartyDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyCaseFilingPartyVO> update(DataCompanyCaseFilingPartyUpdateCommand dataCompanyCaseFilingPartyUpdateCommand) {
        return dataCompanyCaseFilingPartyUpdateCommandExecutor.execute(dataCompanyCaseFilingPartyUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyCaseFilingPartyExWarehouseVO> warehouse(DataCompanyCaseFilingPartyWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyCaseFilingPartyCreateCommandExecutor(DataCompanyCaseFilingPartyCreateCommandExecutor dataCompanyCaseFilingPartyCreateCommandExecutor) {
        this.dataCompanyCaseFilingPartyCreateCommandExecutor = dataCompanyCaseFilingPartyCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyCaseFilingPartyDeleteCommandExecutor(DataCompanyCaseFilingPartyDeleteCommandExecutor dataCompanyCaseFilingPartyDeleteCommandExecutor) {
        this.dataCompanyCaseFilingPartyDeleteCommandExecutor = dataCompanyCaseFilingPartyDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCaseFilingPartyUpdateCommandExecutor(DataCompanyCaseFilingPartyUpdateCommandExecutor dataCompanyCaseFilingPartyUpdateCommandExecutor) {
        this.dataCompanyCaseFilingPartyUpdateCommandExecutor = dataCompanyCaseFilingPartyUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCaseFilingPartyCommandExecutor(DataCompanyCaseFilingPartyCommandExecutor dataCompanyCaseFilingPartyCommandExecutor) {
        this.dataCompanyCaseFilingPartyCommandExecutor = dataCompanyCaseFilingPartyCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCaseFilingPartyWarehouseCommandExecutor(DataCompanyCaseFilingPartyWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}