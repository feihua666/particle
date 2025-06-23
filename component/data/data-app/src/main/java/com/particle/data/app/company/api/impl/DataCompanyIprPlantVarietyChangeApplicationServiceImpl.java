package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprPlantVarietyChangeCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPlantVarietyChangeDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPlantVarietyChangeUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPlantVarietyChangeCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPlantVarietyChangeUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprPlantVarietyChangeApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPlantVarietyChangeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPlantVarietyChangeVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPlantVarietyChangeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPlantVarietyChangeExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprPlantVarietyChangeWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权植物新品种变更信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:52
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprPlantVarietyChangeApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPlantVarietyChangeApplicationService {

    private DataCompanyIprPlantVarietyChangeCreateCommandExecutor dataCompanyIprPlantVarietyChangeCreateCommandExecutor;

    private DataCompanyIprPlantVarietyChangeDeleteCommandExecutor dataCompanyIprPlantVarietyChangeDeleteCommandExecutor;

    private DataCompanyIprPlantVarietyChangeUpdateCommandExecutor dataCompanyIprPlantVarietyChangeUpdateCommandExecutor;

    private DataCompanyIprPlantVarietyChangeCommandExecutor dataCompanyIprPlantVarietyChangeCommandExecutor;

    private DataCompanyIprPlantVarietyChangeWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPlantVarietyChangeVO> create(DataCompanyIprPlantVarietyChangeCreateCommand dataCompanyIprPlantVarietyChangeCreateCommand) {
        return dataCompanyIprPlantVarietyChangeCreateCommandExecutor.execute(dataCompanyIprPlantVarietyChangeCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPlantVarietyChangeVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprPlantVarietyChangeDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPlantVarietyChangeVO> update(DataCompanyIprPlantVarietyChangeUpdateCommand dataCompanyIprPlantVarietyChangeUpdateCommand) {
        return dataCompanyIprPlantVarietyChangeUpdateCommandExecutor.execute(dataCompanyIprPlantVarietyChangeUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprPlantVarietyChangeExWarehouseVO> warehouse(DataCompanyIprPlantVarietyChangeWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprPlantVarietyChangeCreateCommandExecutor(DataCompanyIprPlantVarietyChangeCreateCommandExecutor dataCompanyIprPlantVarietyChangeCreateCommandExecutor) {
        this.dataCompanyIprPlantVarietyChangeCreateCommandExecutor = dataCompanyIprPlantVarietyChangeCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprPlantVarietyChangeDeleteCommandExecutor(DataCompanyIprPlantVarietyChangeDeleteCommandExecutor dataCompanyIprPlantVarietyChangeDeleteCommandExecutor) {
        this.dataCompanyIprPlantVarietyChangeDeleteCommandExecutor = dataCompanyIprPlantVarietyChangeDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPlantVarietyChangeUpdateCommandExecutor(DataCompanyIprPlantVarietyChangeUpdateCommandExecutor dataCompanyIprPlantVarietyChangeUpdateCommandExecutor) {
        this.dataCompanyIprPlantVarietyChangeUpdateCommandExecutor = dataCompanyIprPlantVarietyChangeUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPlantVarietyChangeCommandExecutor(DataCompanyIprPlantVarietyChangeCommandExecutor dataCompanyIprPlantVarietyChangeCommandExecutor) {
        this.dataCompanyIprPlantVarietyChangeCommandExecutor = dataCompanyIprPlantVarietyChangeCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPlantVarietyChangeWarehouseCommandExecutor(DataCompanyIprPlantVarietyChangeWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}