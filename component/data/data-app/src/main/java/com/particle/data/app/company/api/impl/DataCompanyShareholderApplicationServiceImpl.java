package com.particle.data.app.company.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.company.executor.DataCompanyShareholderCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyShareholderCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyShareholderDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyShareholderUpdateCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyShareholderWarehouseCommandExecutor;
import com.particle.data.client.company.api.IDataCompanyShareholderApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyShareholderCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyShareholderUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyShareholderWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyShareholderVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyShareholderExWarehouseVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 企业股东 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:01
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyShareholderApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyShareholderApplicationService {

    private DataCompanyShareholderCreateCommandExecutor dataCompanyShareholderCreateCommandExecutor;

    private DataCompanyShareholderDeleteCommandExecutor dataCompanyShareholderDeleteCommandExecutor;

    private DataCompanyShareholderUpdateCommandExecutor dataCompanyShareholderUpdateCommandExecutor;

    private DataCompanyShareholderCommandExecutor dataCompanyShareholderCommandExecutor;

    private DataCompanyShareholderWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyShareholderVO> create(DataCompanyShareholderCreateCommand dataCompanyShareholderCreateCommand) {
        return dataCompanyShareholderCreateCommandExecutor.execute(dataCompanyShareholderCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyShareholderVO> delete(IdCommand deleteCommand) {
        return dataCompanyShareholderDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyShareholderVO> update(DataCompanyShareholderUpdateCommand dataCompanyShareholderUpdateCommand) {
        return dataCompanyShareholderUpdateCommandExecutor.execute(dataCompanyShareholderUpdateCommand);
    }

    @Override
    public SingleResponse<DataCompanyShareholderExWarehouseVO> warehouse(DataCompanyShareholderWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyShareholderCreateCommandExecutor(DataCompanyShareholderCreateCommandExecutor dataCompanyShareholderCreateCommandExecutor) {
        this.dataCompanyShareholderCreateCommandExecutor = dataCompanyShareholderCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyShareholderDeleteCommandExecutor(DataCompanyShareholderDeleteCommandExecutor dataCompanyShareholderDeleteCommandExecutor) {
        this.dataCompanyShareholderDeleteCommandExecutor = dataCompanyShareholderDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyShareholderUpdateCommandExecutor(DataCompanyShareholderUpdateCommandExecutor dataCompanyShareholderUpdateCommandExecutor) {
        this.dataCompanyShareholderUpdateCommandExecutor = dataCompanyShareholderUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyShareholderCommandExecutor(DataCompanyShareholderCommandExecutor dataCompanyShareholderCommandExecutor) {
        this.dataCompanyShareholderCommandExecutor = dataCompanyShareholderCommandExecutor;
    }
    @Autowired
    public void setDataCompanyShareholderWarehouseCommandExecutor(DataCompanyShareholderWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}
