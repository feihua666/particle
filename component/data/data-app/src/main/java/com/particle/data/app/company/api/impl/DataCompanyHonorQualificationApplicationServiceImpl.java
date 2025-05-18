package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyHonorQualificationCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyHonorQualificationDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyHonorQualificationUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyHonorQualificationCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyHonorQualificationUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyHonorQualificationApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyHonorQualificationCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyHonorQualificationVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyHonorQualificationWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyHonorQualificationExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyHonorQualificationWarehouseCommandExecutor;
/**
 * <p>
 * 企业荣誉资质 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:14
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyHonorQualificationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyHonorQualificationApplicationService {

    private DataCompanyHonorQualificationCreateCommandExecutor dataCompanyHonorQualificationCreateCommandExecutor;

    private DataCompanyHonorQualificationDeleteCommandExecutor dataCompanyHonorQualificationDeleteCommandExecutor;

    private DataCompanyHonorQualificationUpdateCommandExecutor dataCompanyHonorQualificationUpdateCommandExecutor;

    private DataCompanyHonorQualificationCommandExecutor dataCompanyHonorQualificationCommandExecutor;

    private DataCompanyHonorQualificationWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyHonorQualificationVO> create(DataCompanyHonorQualificationCreateCommand dataCompanyHonorQualificationCreateCommand) {
        return dataCompanyHonorQualificationCreateCommandExecutor.execute(dataCompanyHonorQualificationCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyHonorQualificationVO> delete(IdCommand deleteCommand) {
        return dataCompanyHonorQualificationDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyHonorQualificationVO> update(DataCompanyHonorQualificationUpdateCommand dataCompanyHonorQualificationUpdateCommand) {
        return dataCompanyHonorQualificationUpdateCommandExecutor.execute(dataCompanyHonorQualificationUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyHonorQualificationExWarehouseVO> warehouse(DataCompanyHonorQualificationWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyHonorQualificationCreateCommandExecutor(DataCompanyHonorQualificationCreateCommandExecutor dataCompanyHonorQualificationCreateCommandExecutor) {
        this.dataCompanyHonorQualificationCreateCommandExecutor = dataCompanyHonorQualificationCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyHonorQualificationDeleteCommandExecutor(DataCompanyHonorQualificationDeleteCommandExecutor dataCompanyHonorQualificationDeleteCommandExecutor) {
        this.dataCompanyHonorQualificationDeleteCommandExecutor = dataCompanyHonorQualificationDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyHonorQualificationUpdateCommandExecutor(DataCompanyHonorQualificationUpdateCommandExecutor dataCompanyHonorQualificationUpdateCommandExecutor) {
        this.dataCompanyHonorQualificationUpdateCommandExecutor = dataCompanyHonorQualificationUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyHonorQualificationCommandExecutor(DataCompanyHonorQualificationCommandExecutor dataCompanyHonorQualificationCommandExecutor) {
        this.dataCompanyHonorQualificationCommandExecutor = dataCompanyHonorQualificationCommandExecutor;
    }
    @Autowired
    public void setDataCompanyHonorQualificationWarehouseCommandExecutor(DataCompanyHonorQualificationWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}