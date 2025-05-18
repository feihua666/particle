package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprPatentCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprPatentApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprPatentWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:48
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprPatentApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentApplicationService {

    private DataCompanyIprPatentCreateCommandExecutor dataCompanyIprPatentCreateCommandExecutor;

    private DataCompanyIprPatentDeleteCommandExecutor dataCompanyIprPatentDeleteCommandExecutor;

    private DataCompanyIprPatentUpdateCommandExecutor dataCompanyIprPatentUpdateCommandExecutor;

    private DataCompanyIprPatentCommandExecutor dataCompanyIprPatentCommandExecutor;

    private DataCompanyIprPatentWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentVO> create(DataCompanyIprPatentCreateCommand dataCompanyIprPatentCreateCommand) {
        return dataCompanyIprPatentCreateCommandExecutor.execute(dataCompanyIprPatentCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprPatentDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentVO> update(DataCompanyIprPatentUpdateCommand dataCompanyIprPatentUpdateCommand) {
        return dataCompanyIprPatentUpdateCommandExecutor.execute(dataCompanyIprPatentUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprPatentExWarehouseVO> warehouse(DataCompanyIprPatentWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprPatentCreateCommandExecutor(DataCompanyIprPatentCreateCommandExecutor dataCompanyIprPatentCreateCommandExecutor) {
        this.dataCompanyIprPatentCreateCommandExecutor = dataCompanyIprPatentCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprPatentDeleteCommandExecutor(DataCompanyIprPatentDeleteCommandExecutor dataCompanyIprPatentDeleteCommandExecutor) {
        this.dataCompanyIprPatentDeleteCommandExecutor = dataCompanyIprPatentDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentUpdateCommandExecutor(DataCompanyIprPatentUpdateCommandExecutor dataCompanyIprPatentUpdateCommandExecutor) {
        this.dataCompanyIprPatentUpdateCommandExecutor = dataCompanyIprPatentUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentCommandExecutor(DataCompanyIprPatentCommandExecutor dataCompanyIprPatentCommandExecutor) {
        this.dataCompanyIprPatentCommandExecutor = dataCompanyIprPatentCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentWarehouseCommandExecutor(DataCompanyIprPatentWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}