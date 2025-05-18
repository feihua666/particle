package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprPatentContentCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentContentDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentContentUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentContentCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentContentUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprPatentContentApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentContentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentContentVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentContentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentContentExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprPatentContentWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利内容 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:27
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprPatentContentApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentContentApplicationService {

    private DataCompanyIprPatentContentCreateCommandExecutor dataCompanyIprPatentContentCreateCommandExecutor;

    private DataCompanyIprPatentContentDeleteCommandExecutor dataCompanyIprPatentContentDeleteCommandExecutor;

    private DataCompanyIprPatentContentUpdateCommandExecutor dataCompanyIprPatentContentUpdateCommandExecutor;

    private DataCompanyIprPatentContentCommandExecutor dataCompanyIprPatentContentCommandExecutor;

    private DataCompanyIprPatentContentWarehouseCommandExecutor dataCompanyIprPatentContentWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentContentVO> create(DataCompanyIprPatentContentCreateCommand dataCompanyIprPatentContentCreateCommand) {
        return dataCompanyIprPatentContentCreateCommandExecutor.execute(dataCompanyIprPatentContentCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentContentVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprPatentContentDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentContentVO> update(DataCompanyIprPatentContentUpdateCommand dataCompanyIprPatentContentUpdateCommand) {
        return dataCompanyIprPatentContentUpdateCommandExecutor.execute(dataCompanyIprPatentContentUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprPatentContentExWarehouseVO> warehouse(DataCompanyIprPatentContentWarehouseCommand dataCompanyIprPatentContentWarehouseCommand) {
        return dataCompanyIprPatentContentWarehouseCommandExecutor.warehouse(dataCompanyIprPatentContentWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprPatentContentCreateCommandExecutor(DataCompanyIprPatentContentCreateCommandExecutor dataCompanyIprPatentContentCreateCommandExecutor) {
        this.dataCompanyIprPatentContentCreateCommandExecutor = dataCompanyIprPatentContentCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprPatentContentDeleteCommandExecutor(DataCompanyIprPatentContentDeleteCommandExecutor dataCompanyIprPatentContentDeleteCommandExecutor) {
        this.dataCompanyIprPatentContentDeleteCommandExecutor = dataCompanyIprPatentContentDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentContentUpdateCommandExecutor(DataCompanyIprPatentContentUpdateCommandExecutor dataCompanyIprPatentContentUpdateCommandExecutor) {
        this.dataCompanyIprPatentContentUpdateCommandExecutor = dataCompanyIprPatentContentUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentContentCommandExecutor(DataCompanyIprPatentContentCommandExecutor dataCompanyIprPatentContentCommandExecutor) {
        this.dataCompanyIprPatentContentCommandExecutor = dataCompanyIprPatentContentCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentContentWarehouseCommandExecutor(DataCompanyIprPatentContentWarehouseCommandExecutor dataCompanyIprPatentContentWarehouseCommandExecutor) {
        this.dataCompanyIprPatentContentWarehouseCommandExecutor = dataCompanyIprPatentContentWarehouseCommandExecutor;
    }

}