package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprPlantVarietyCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPlantVarietyDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPlantVarietyUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPlantVarietyCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPlantVarietyUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprPlantVarietyApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPlantVarietyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPlantVarietyVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPlantVarietyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPlantVarietyExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprPlantVarietyWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权植物新品种 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:40
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprPlantVarietyApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPlantVarietyApplicationService {

    private DataCompanyIprPlantVarietyCreateCommandExecutor dataCompanyIprPlantVarietyCreateCommandExecutor;

    private DataCompanyIprPlantVarietyDeleteCommandExecutor dataCompanyIprPlantVarietyDeleteCommandExecutor;

    private DataCompanyIprPlantVarietyUpdateCommandExecutor dataCompanyIprPlantVarietyUpdateCommandExecutor;

    private DataCompanyIprPlantVarietyCommandExecutor dataCompanyIprPlantVarietyCommandExecutor;

    private DataCompanyIprPlantVarietyWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPlantVarietyVO> create(DataCompanyIprPlantVarietyCreateCommand dataCompanyIprPlantVarietyCreateCommand) {
        return dataCompanyIprPlantVarietyCreateCommandExecutor.execute(dataCompanyIprPlantVarietyCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPlantVarietyVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprPlantVarietyDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPlantVarietyVO> update(DataCompanyIprPlantVarietyUpdateCommand dataCompanyIprPlantVarietyUpdateCommand) {
        return dataCompanyIprPlantVarietyUpdateCommandExecutor.execute(dataCompanyIprPlantVarietyUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprPlantVarietyExWarehouseVO> warehouse(DataCompanyIprPlantVarietyWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprPlantVarietyCreateCommandExecutor(DataCompanyIprPlantVarietyCreateCommandExecutor dataCompanyIprPlantVarietyCreateCommandExecutor) {
        this.dataCompanyIprPlantVarietyCreateCommandExecutor = dataCompanyIprPlantVarietyCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprPlantVarietyDeleteCommandExecutor(DataCompanyIprPlantVarietyDeleteCommandExecutor dataCompanyIprPlantVarietyDeleteCommandExecutor) {
        this.dataCompanyIprPlantVarietyDeleteCommandExecutor = dataCompanyIprPlantVarietyDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPlantVarietyUpdateCommandExecutor(DataCompanyIprPlantVarietyUpdateCommandExecutor dataCompanyIprPlantVarietyUpdateCommandExecutor) {
        this.dataCompanyIprPlantVarietyUpdateCommandExecutor = dataCompanyIprPlantVarietyUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPlantVarietyCommandExecutor(DataCompanyIprPlantVarietyCommandExecutor dataCompanyIprPlantVarietyCommandExecutor) {
        this.dataCompanyIprPlantVarietyCommandExecutor = dataCompanyIprPlantVarietyCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPlantVarietyWarehouseCommandExecutor(DataCompanyIprPlantVarietyWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}