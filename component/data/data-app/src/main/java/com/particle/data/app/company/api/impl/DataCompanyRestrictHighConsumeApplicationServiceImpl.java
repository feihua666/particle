package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyRestrictHighConsumeCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyRestrictHighConsumeDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyRestrictHighConsumeUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyRestrictHighConsumeCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyRestrictHighConsumeUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyRestrictHighConsumeApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyRestrictHighConsumeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyRestrictHighConsumeVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyRestrictHighConsumeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyRestrictHighConsumeExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyRestrictHighConsumeWarehouseCommandExecutor;
/**
 * <p>
 * 企业限制高消费 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:19
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyRestrictHighConsumeApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyRestrictHighConsumeApplicationService {

    private DataCompanyRestrictHighConsumeCreateCommandExecutor dataCompanyRestrictHighConsumeCreateCommandExecutor;

    private DataCompanyRestrictHighConsumeDeleteCommandExecutor dataCompanyRestrictHighConsumeDeleteCommandExecutor;

    private DataCompanyRestrictHighConsumeUpdateCommandExecutor dataCompanyRestrictHighConsumeUpdateCommandExecutor;

    private DataCompanyRestrictHighConsumeCommandExecutor dataCompanyRestrictHighConsumeCommandExecutor;

    private DataCompanyRestrictHighConsumeWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyRestrictHighConsumeVO> create(DataCompanyRestrictHighConsumeCreateCommand dataCompanyRestrictHighConsumeCreateCommand) {
        return dataCompanyRestrictHighConsumeCreateCommandExecutor.execute(dataCompanyRestrictHighConsumeCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyRestrictHighConsumeVO> delete(IdCommand deleteCommand) {
        return dataCompanyRestrictHighConsumeDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyRestrictHighConsumeVO> update(DataCompanyRestrictHighConsumeUpdateCommand dataCompanyRestrictHighConsumeUpdateCommand) {
        return dataCompanyRestrictHighConsumeUpdateCommandExecutor.execute(dataCompanyRestrictHighConsumeUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyRestrictHighConsumeExWarehouseVO> warehouse(DataCompanyRestrictHighConsumeWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyRestrictHighConsumeCreateCommandExecutor(DataCompanyRestrictHighConsumeCreateCommandExecutor dataCompanyRestrictHighConsumeCreateCommandExecutor) {
        this.dataCompanyRestrictHighConsumeCreateCommandExecutor = dataCompanyRestrictHighConsumeCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyRestrictHighConsumeDeleteCommandExecutor(DataCompanyRestrictHighConsumeDeleteCommandExecutor dataCompanyRestrictHighConsumeDeleteCommandExecutor) {
        this.dataCompanyRestrictHighConsumeDeleteCommandExecutor = dataCompanyRestrictHighConsumeDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyRestrictHighConsumeUpdateCommandExecutor(DataCompanyRestrictHighConsumeUpdateCommandExecutor dataCompanyRestrictHighConsumeUpdateCommandExecutor) {
        this.dataCompanyRestrictHighConsumeUpdateCommandExecutor = dataCompanyRestrictHighConsumeUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyRestrictHighConsumeCommandExecutor(DataCompanyRestrictHighConsumeCommandExecutor dataCompanyRestrictHighConsumeCommandExecutor) {
        this.dataCompanyRestrictHighConsumeCommandExecutor = dataCompanyRestrictHighConsumeCommandExecutor;
    }
    @Autowired
    public void setDataCompanyRestrictHighConsumeWarehouseCommandExecutor(DataCompanyRestrictHighConsumeWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}