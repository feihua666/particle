package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprWorkCopyrightCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprWorkCopyrightDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprWorkCopyrightUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprWorkCopyrightCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprWorkCopyrightUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprWorkCopyrightApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprWorkCopyrightCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprWorkCopyrightVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprWorkCopyrightWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprWorkCopyrightExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprWorkCopyrightWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权作品著作 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:45
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprWorkCopyrightApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprWorkCopyrightApplicationService {

    private DataCompanyIprWorkCopyrightCreateCommandExecutor dataCompanyIprWorkCopyrightCreateCommandExecutor;

    private DataCompanyIprWorkCopyrightDeleteCommandExecutor dataCompanyIprWorkCopyrightDeleteCommandExecutor;

    private DataCompanyIprWorkCopyrightUpdateCommandExecutor dataCompanyIprWorkCopyrightUpdateCommandExecutor;

    private DataCompanyIprWorkCopyrightCommandExecutor dataCompanyIprWorkCopyrightCommandExecutor;

    private DataCompanyIprWorkCopyrightWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprWorkCopyrightVO> create(DataCompanyIprWorkCopyrightCreateCommand dataCompanyIprWorkCopyrightCreateCommand) {
        return dataCompanyIprWorkCopyrightCreateCommandExecutor.execute(dataCompanyIprWorkCopyrightCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprWorkCopyrightVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprWorkCopyrightDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprWorkCopyrightVO> update(DataCompanyIprWorkCopyrightUpdateCommand dataCompanyIprWorkCopyrightUpdateCommand) {
        return dataCompanyIprWorkCopyrightUpdateCommandExecutor.execute(dataCompanyIprWorkCopyrightUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprWorkCopyrightExWarehouseVO> warehouse(DataCompanyIprWorkCopyrightWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprWorkCopyrightCreateCommandExecutor(DataCompanyIprWorkCopyrightCreateCommandExecutor dataCompanyIprWorkCopyrightCreateCommandExecutor) {
        this.dataCompanyIprWorkCopyrightCreateCommandExecutor = dataCompanyIprWorkCopyrightCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprWorkCopyrightDeleteCommandExecutor(DataCompanyIprWorkCopyrightDeleteCommandExecutor dataCompanyIprWorkCopyrightDeleteCommandExecutor) {
        this.dataCompanyIprWorkCopyrightDeleteCommandExecutor = dataCompanyIprWorkCopyrightDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprWorkCopyrightUpdateCommandExecutor(DataCompanyIprWorkCopyrightUpdateCommandExecutor dataCompanyIprWorkCopyrightUpdateCommandExecutor) {
        this.dataCompanyIprWorkCopyrightUpdateCommandExecutor = dataCompanyIprWorkCopyrightUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprWorkCopyrightCommandExecutor(DataCompanyIprWorkCopyrightCommandExecutor dataCompanyIprWorkCopyrightCommandExecutor) {
        this.dataCompanyIprWorkCopyrightCommandExecutor = dataCompanyIprWorkCopyrightCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprWorkCopyrightWarehouseCommandExecutor(DataCompanyIprWorkCopyrightWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}