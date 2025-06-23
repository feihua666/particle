package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprGeograCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprGeograDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprGeograUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprGeograCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprGeograUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprGeograApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprGeograCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprGeograVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprGeograWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprGeograExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprGeograWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权地理标识 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:33
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprGeograApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprGeograApplicationService {

    private DataCompanyIprGeograCreateCommandExecutor dataCompanyIprGeograCreateCommandExecutor;

    private DataCompanyIprGeograDeleteCommandExecutor dataCompanyIprGeograDeleteCommandExecutor;

    private DataCompanyIprGeograUpdateCommandExecutor dataCompanyIprGeograUpdateCommandExecutor;

    private DataCompanyIprGeograCommandExecutor dataCompanyIprGeograCommandExecutor;

    private DataCompanyIprGeograWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprGeograVO> create(DataCompanyIprGeograCreateCommand dataCompanyIprGeograCreateCommand) {
        return dataCompanyIprGeograCreateCommandExecutor.execute(dataCompanyIprGeograCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprGeograVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprGeograDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprGeograVO> update(DataCompanyIprGeograUpdateCommand dataCompanyIprGeograUpdateCommand) {
        return dataCompanyIprGeograUpdateCommandExecutor.execute(dataCompanyIprGeograUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprGeograExWarehouseVO> warehouse(DataCompanyIprGeograWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprGeograCreateCommandExecutor(DataCompanyIprGeograCreateCommandExecutor dataCompanyIprGeograCreateCommandExecutor) {
        this.dataCompanyIprGeograCreateCommandExecutor = dataCompanyIprGeograCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprGeograDeleteCommandExecutor(DataCompanyIprGeograDeleteCommandExecutor dataCompanyIprGeograDeleteCommandExecutor) {
        this.dataCompanyIprGeograDeleteCommandExecutor = dataCompanyIprGeograDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprGeograUpdateCommandExecutor(DataCompanyIprGeograUpdateCommandExecutor dataCompanyIprGeograUpdateCommandExecutor) {
        this.dataCompanyIprGeograUpdateCommandExecutor = dataCompanyIprGeograUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprGeograCommandExecutor(DataCompanyIprGeograCommandExecutor dataCompanyIprGeograCommandExecutor) {
        this.dataCompanyIprGeograCommandExecutor = dataCompanyIprGeograCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprGeograWarehouseCommandExecutor(DataCompanyIprGeograWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}