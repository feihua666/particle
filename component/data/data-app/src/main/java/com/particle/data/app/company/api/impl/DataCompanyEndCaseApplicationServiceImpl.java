package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyEndCaseCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyEndCaseDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyEndCaseUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyEndCaseCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyEndCaseUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyEndCaseApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyEndCaseCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyEndCaseVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyEndCaseWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyEndCaseExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyEndCaseWarehouseCommandExecutor;
/**
 * <p>
 * 企业终本案件 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:57
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyEndCaseApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyEndCaseApplicationService {

    private DataCompanyEndCaseCreateCommandExecutor dataCompanyEndCaseCreateCommandExecutor;

    private DataCompanyEndCaseDeleteCommandExecutor dataCompanyEndCaseDeleteCommandExecutor;

    private DataCompanyEndCaseUpdateCommandExecutor dataCompanyEndCaseUpdateCommandExecutor;

    private DataCompanyEndCaseCommandExecutor dataCompanyEndCaseCommandExecutor;

    private DataCompanyEndCaseWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyEndCaseVO> create(DataCompanyEndCaseCreateCommand dataCompanyEndCaseCreateCommand) {
        return dataCompanyEndCaseCreateCommandExecutor.execute(dataCompanyEndCaseCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyEndCaseVO> delete(IdCommand deleteCommand) {
        return dataCompanyEndCaseDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyEndCaseVO> update(DataCompanyEndCaseUpdateCommand dataCompanyEndCaseUpdateCommand) {
        return dataCompanyEndCaseUpdateCommandExecutor.execute(dataCompanyEndCaseUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyEndCaseExWarehouseVO> warehouse(DataCompanyEndCaseWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyEndCaseCreateCommandExecutor(DataCompanyEndCaseCreateCommandExecutor dataCompanyEndCaseCreateCommandExecutor) {
        this.dataCompanyEndCaseCreateCommandExecutor = dataCompanyEndCaseCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyEndCaseDeleteCommandExecutor(DataCompanyEndCaseDeleteCommandExecutor dataCompanyEndCaseDeleteCommandExecutor) {
        this.dataCompanyEndCaseDeleteCommandExecutor = dataCompanyEndCaseDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyEndCaseUpdateCommandExecutor(DataCompanyEndCaseUpdateCommandExecutor dataCompanyEndCaseUpdateCommandExecutor) {
        this.dataCompanyEndCaseUpdateCommandExecutor = dataCompanyEndCaseUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyEndCaseCommandExecutor(DataCompanyEndCaseCommandExecutor dataCompanyEndCaseCommandExecutor) {
        this.dataCompanyEndCaseCommandExecutor = dataCompanyEndCaseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyEndCaseWarehouseCommandExecutor(DataCompanyEndCaseWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}