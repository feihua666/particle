package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyCaseFilingCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyCaseFilingDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyCaseFilingUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyCaseFilingCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyCaseFilingUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyCaseFilingApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyCaseFilingCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCaseFilingVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCaseFilingWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCaseFilingExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyCaseFilingWarehouseCommandExecutor;
/**
 * <p>
 * 企业立案信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:36
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyCaseFilingApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyCaseFilingApplicationService {

    private DataCompanyCaseFilingCreateCommandExecutor dataCompanyCaseFilingCreateCommandExecutor;

    private DataCompanyCaseFilingDeleteCommandExecutor dataCompanyCaseFilingDeleteCommandExecutor;

    private DataCompanyCaseFilingUpdateCommandExecutor dataCompanyCaseFilingUpdateCommandExecutor;

    private DataCompanyCaseFilingCommandExecutor dataCompanyCaseFilingCommandExecutor;

    private DataCompanyCaseFilingWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyCaseFilingVO> create(DataCompanyCaseFilingCreateCommand dataCompanyCaseFilingCreateCommand) {
        return dataCompanyCaseFilingCreateCommandExecutor.execute(dataCompanyCaseFilingCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyCaseFilingVO> delete(IdCommand deleteCommand) {
        return dataCompanyCaseFilingDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyCaseFilingVO> update(DataCompanyCaseFilingUpdateCommand dataCompanyCaseFilingUpdateCommand) {
        return dataCompanyCaseFilingUpdateCommandExecutor.execute(dataCompanyCaseFilingUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyCaseFilingExWarehouseVO> warehouse(DataCompanyCaseFilingWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyCaseFilingCreateCommandExecutor(DataCompanyCaseFilingCreateCommandExecutor dataCompanyCaseFilingCreateCommandExecutor) {
        this.dataCompanyCaseFilingCreateCommandExecutor = dataCompanyCaseFilingCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyCaseFilingDeleteCommandExecutor(DataCompanyCaseFilingDeleteCommandExecutor dataCompanyCaseFilingDeleteCommandExecutor) {
        this.dataCompanyCaseFilingDeleteCommandExecutor = dataCompanyCaseFilingDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCaseFilingUpdateCommandExecutor(DataCompanyCaseFilingUpdateCommandExecutor dataCompanyCaseFilingUpdateCommandExecutor) {
        this.dataCompanyCaseFilingUpdateCommandExecutor = dataCompanyCaseFilingUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCaseFilingCommandExecutor(DataCompanyCaseFilingCommandExecutor dataCompanyCaseFilingCommandExecutor) {
        this.dataCompanyCaseFilingCommandExecutor = dataCompanyCaseFilingCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCaseFilingWarehouseCommandExecutor(DataCompanyCaseFilingWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}