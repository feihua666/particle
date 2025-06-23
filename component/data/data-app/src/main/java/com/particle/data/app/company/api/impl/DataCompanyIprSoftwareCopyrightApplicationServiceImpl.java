package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprSoftwareCopyrightCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprSoftwareCopyrightDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprSoftwareCopyrightUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprSoftwareCopyrightCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprSoftwareCopyrightUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprSoftwareCopyrightApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprSoftwareCopyrightCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprSoftwareCopyrightVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprSoftwareCopyrightWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprSoftwareCopyrightExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprSoftwareCopyrightWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权软件著作 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:01
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprSoftwareCopyrightApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprSoftwareCopyrightApplicationService {

    private DataCompanyIprSoftwareCopyrightCreateCommandExecutor dataCompanyIprSoftwareCopyrightCreateCommandExecutor;

    private DataCompanyIprSoftwareCopyrightDeleteCommandExecutor dataCompanyIprSoftwareCopyrightDeleteCommandExecutor;

    private DataCompanyIprSoftwareCopyrightUpdateCommandExecutor dataCompanyIprSoftwareCopyrightUpdateCommandExecutor;

    private DataCompanyIprSoftwareCopyrightCommandExecutor dataCompanyIprSoftwareCopyrightCommandExecutor;

    private DataCompanyIprSoftwareCopyrightWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprSoftwareCopyrightVO> create(DataCompanyIprSoftwareCopyrightCreateCommand dataCompanyIprSoftwareCopyrightCreateCommand) {
        return dataCompanyIprSoftwareCopyrightCreateCommandExecutor.execute(dataCompanyIprSoftwareCopyrightCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprSoftwareCopyrightVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprSoftwareCopyrightDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprSoftwareCopyrightVO> update(DataCompanyIprSoftwareCopyrightUpdateCommand dataCompanyIprSoftwareCopyrightUpdateCommand) {
        return dataCompanyIprSoftwareCopyrightUpdateCommandExecutor.execute(dataCompanyIprSoftwareCopyrightUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprSoftwareCopyrightExWarehouseVO> warehouse(DataCompanyIprSoftwareCopyrightWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprSoftwareCopyrightCreateCommandExecutor(DataCompanyIprSoftwareCopyrightCreateCommandExecutor dataCompanyIprSoftwareCopyrightCreateCommandExecutor) {
        this.dataCompanyIprSoftwareCopyrightCreateCommandExecutor = dataCompanyIprSoftwareCopyrightCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprSoftwareCopyrightDeleteCommandExecutor(DataCompanyIprSoftwareCopyrightDeleteCommandExecutor dataCompanyIprSoftwareCopyrightDeleteCommandExecutor) {
        this.dataCompanyIprSoftwareCopyrightDeleteCommandExecutor = dataCompanyIprSoftwareCopyrightDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprSoftwareCopyrightUpdateCommandExecutor(DataCompanyIprSoftwareCopyrightUpdateCommandExecutor dataCompanyIprSoftwareCopyrightUpdateCommandExecutor) {
        this.dataCompanyIprSoftwareCopyrightUpdateCommandExecutor = dataCompanyIprSoftwareCopyrightUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprSoftwareCopyrightCommandExecutor(DataCompanyIprSoftwareCopyrightCommandExecutor dataCompanyIprSoftwareCopyrightCommandExecutor) {
        this.dataCompanyIprSoftwareCopyrightCommandExecutor = dataCompanyIprSoftwareCopyrightCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprSoftwareCopyrightWarehouseCommandExecutor(DataCompanyIprSoftwareCopyrightWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}