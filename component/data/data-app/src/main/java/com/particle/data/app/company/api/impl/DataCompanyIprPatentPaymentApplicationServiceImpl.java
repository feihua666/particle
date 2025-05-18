package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprPatentPaymentCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentPaymentDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentPaymentUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentPaymentCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPaymentUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprPatentPaymentApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPaymentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPaymentVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentPaymentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPaymentExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprPatentPaymentWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利缴费信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:27
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprPatentPaymentApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentPaymentApplicationService {

    private DataCompanyIprPatentPaymentCreateCommandExecutor dataCompanyIprPatentPaymentCreateCommandExecutor;

    private DataCompanyIprPatentPaymentDeleteCommandExecutor dataCompanyIprPatentPaymentDeleteCommandExecutor;

    private DataCompanyIprPatentPaymentUpdateCommandExecutor dataCompanyIprPatentPaymentUpdateCommandExecutor;

    private DataCompanyIprPatentPaymentCommandExecutor dataCompanyIprPatentPaymentCommandExecutor;

    private DataCompanyIprPatentPaymentWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentPaymentVO> create(DataCompanyIprPatentPaymentCreateCommand dataCompanyIprPatentPaymentCreateCommand) {
        return dataCompanyIprPatentPaymentCreateCommandExecutor.execute(dataCompanyIprPatentPaymentCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentPaymentVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprPatentPaymentDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentPaymentVO> update(DataCompanyIprPatentPaymentUpdateCommand dataCompanyIprPatentPaymentUpdateCommand) {
        return dataCompanyIprPatentPaymentUpdateCommandExecutor.execute(dataCompanyIprPatentPaymentUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprPatentPaymentExWarehouseVO> warehouse(DataCompanyIprPatentPaymentWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprPatentPaymentCreateCommandExecutor(DataCompanyIprPatentPaymentCreateCommandExecutor dataCompanyIprPatentPaymentCreateCommandExecutor) {
        this.dataCompanyIprPatentPaymentCreateCommandExecutor = dataCompanyIprPatentPaymentCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprPatentPaymentDeleteCommandExecutor(DataCompanyIprPatentPaymentDeleteCommandExecutor dataCompanyIprPatentPaymentDeleteCommandExecutor) {
        this.dataCompanyIprPatentPaymentDeleteCommandExecutor = dataCompanyIprPatentPaymentDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentPaymentUpdateCommandExecutor(DataCompanyIprPatentPaymentUpdateCommandExecutor dataCompanyIprPatentPaymentUpdateCommandExecutor) {
        this.dataCompanyIprPatentPaymentUpdateCommandExecutor = dataCompanyIprPatentPaymentUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentPaymentCommandExecutor(DataCompanyIprPatentPaymentCommandExecutor dataCompanyIprPatentPaymentCommandExecutor) {
        this.dataCompanyIprPatentPaymentCommandExecutor = dataCompanyIprPatentPaymentCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentPaymentWarehouseCommandExecutor(DataCompanyIprPatentPaymentWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}