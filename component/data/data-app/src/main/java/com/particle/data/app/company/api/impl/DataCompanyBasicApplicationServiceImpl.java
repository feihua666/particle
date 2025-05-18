package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyBasicCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyBasicDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyBasicUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyBasicCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyBasicExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyBasicWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.DataCompanyBasicUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyBasicApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyBasicCreateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyBasicWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyBasicVO;


import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyBasicExWarehouseVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 企业基本信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyBasicApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyBasicApplicationService {

    private DataCompanyBasicCreateCommandExecutor dataCompanyBasicCreateCommandExecutor;

    private DataCompanyBasicDeleteCommandExecutor dataCompanyBasicDeleteCommandExecutor;

    private DataCompanyBasicUpdateCommandExecutor dataCompanyBasicUpdateCommandExecutor;

    private DataCompanyBasicCommandExecutor dataCompanyBasicCommandExecutor;

    private DataCompanyBasicWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;


    @Override
    public SingleResponse<DataCompanyBasicVO> create(DataCompanyBasicCreateCommand dataCompanyBasicCreateCommand) {
        return dataCompanyBasicCreateCommandExecutor.execute(dataCompanyBasicCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyBasicVO> delete(IdCommand deleteCommand) {
        return dataCompanyBasicDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyBasicVO> update(DataCompanyBasicUpdateCommand dataCompanyBasicUpdateCommand) {
        return dataCompanyBasicUpdateCommandExecutor.execute(dataCompanyBasicUpdateCommand);
    }

    @Override
    public SingleResponse<DataCompanyBasicExWarehouseVO> warehouse(DataCompanyBasicWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }

    @Autowired
    public void setDataCompanyBasicCreateCommandExecutor(DataCompanyBasicCreateCommandExecutor dataCompanyBasicCreateCommandExecutor) {
        this.dataCompanyBasicCreateCommandExecutor = dataCompanyBasicCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyBasicDeleteCommandExecutor(DataCompanyBasicDeleteCommandExecutor dataCompanyBasicDeleteCommandExecutor) {
        this.dataCompanyBasicDeleteCommandExecutor = dataCompanyBasicDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyBasicUpdateCommandExecutor(DataCompanyBasicUpdateCommandExecutor dataCompanyBasicUpdateCommandExecutor) {
        this.dataCompanyBasicUpdateCommandExecutor = dataCompanyBasicUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyBasicCommandExecutor(DataCompanyBasicCommandExecutor dataCompanyBasicCommandExecutor) {
        this.dataCompanyBasicCommandExecutor = dataCompanyBasicCommandExecutor;
    }
    @Autowired
    public void setDataCompanyBasicWarehouseCommandExecutor(DataCompanyBasicWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}
