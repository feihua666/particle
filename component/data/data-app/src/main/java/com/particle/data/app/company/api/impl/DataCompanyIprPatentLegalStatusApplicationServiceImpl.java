package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprPatentLegalStatusCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentLegalStatusDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentLegalStatusUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentLegalStatusCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentLegalStatusUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprPatentLegalStatusApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentLegalStatusCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentLegalStatusVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentLegalStatusWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentLegalStatusExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprPatentLegalStatusWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利法律状态 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:03
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprPatentLegalStatusApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentLegalStatusApplicationService {

    private DataCompanyIprPatentLegalStatusCreateCommandExecutor dataCompanyIprPatentLegalStatusCreateCommandExecutor;

    private DataCompanyIprPatentLegalStatusDeleteCommandExecutor dataCompanyIprPatentLegalStatusDeleteCommandExecutor;

    private DataCompanyIprPatentLegalStatusUpdateCommandExecutor dataCompanyIprPatentLegalStatusUpdateCommandExecutor;

    private DataCompanyIprPatentLegalStatusCommandExecutor dataCompanyIprPatentLegalStatusCommandExecutor;

    private DataCompanyIprPatentLegalStatusWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentLegalStatusVO> create(DataCompanyIprPatentLegalStatusCreateCommand dataCompanyIprPatentLegalStatusCreateCommand) {
        return dataCompanyIprPatentLegalStatusCreateCommandExecutor.execute(dataCompanyIprPatentLegalStatusCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentLegalStatusVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprPatentLegalStatusDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentLegalStatusVO> update(DataCompanyIprPatentLegalStatusUpdateCommand dataCompanyIprPatentLegalStatusUpdateCommand) {
        return dataCompanyIprPatentLegalStatusUpdateCommandExecutor.execute(dataCompanyIprPatentLegalStatusUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprPatentLegalStatusExWarehouseVO> warehouse(DataCompanyIprPatentLegalStatusWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprPatentLegalStatusCreateCommandExecutor(DataCompanyIprPatentLegalStatusCreateCommandExecutor dataCompanyIprPatentLegalStatusCreateCommandExecutor) {
        this.dataCompanyIprPatentLegalStatusCreateCommandExecutor = dataCompanyIprPatentLegalStatusCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprPatentLegalStatusDeleteCommandExecutor(DataCompanyIprPatentLegalStatusDeleteCommandExecutor dataCompanyIprPatentLegalStatusDeleteCommandExecutor) {
        this.dataCompanyIprPatentLegalStatusDeleteCommandExecutor = dataCompanyIprPatentLegalStatusDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentLegalStatusUpdateCommandExecutor(DataCompanyIprPatentLegalStatusUpdateCommandExecutor dataCompanyIprPatentLegalStatusUpdateCommandExecutor) {
        this.dataCompanyIprPatentLegalStatusUpdateCommandExecutor = dataCompanyIprPatentLegalStatusUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentLegalStatusCommandExecutor(DataCompanyIprPatentLegalStatusCommandExecutor dataCompanyIprPatentLegalStatusCommandExecutor) {
        this.dataCompanyIprPatentLegalStatusCommandExecutor = dataCompanyIprPatentLegalStatusCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentLegalStatusWarehouseCommandExecutor(DataCompanyIprPatentLegalStatusWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}