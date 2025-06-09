package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyAbnormalCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAbnormalDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAbnormalUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyAbnormalCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyAbnormalUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyAbnormalApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyAbnormalCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAbnormalVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAbnormalWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAbnormalExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyAbnormalWarehouseCommandExecutor;
/**
 * <p>
 * 企业经营异常 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-05-29 10:47:31
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyAbnormalApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyAbnormalApplicationService {

    private DataCompanyAbnormalCreateCommandExecutor dataCompanyAbnormalCreateCommandExecutor;

    private DataCompanyAbnormalDeleteCommandExecutor dataCompanyAbnormalDeleteCommandExecutor;

    private DataCompanyAbnormalUpdateCommandExecutor dataCompanyAbnormalUpdateCommandExecutor;

    private DataCompanyAbnormalCommandExecutor dataCompanyAbnormalCommandExecutor;

    private DataCompanyAbnormalWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyAbnormalVO> create(DataCompanyAbnormalCreateCommand dataCompanyAbnormalCreateCommand) {
        return dataCompanyAbnormalCreateCommandExecutor.execute(dataCompanyAbnormalCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyAbnormalVO> delete(IdCommand deleteCommand) {
        return dataCompanyAbnormalDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyAbnormalVO> update(DataCompanyAbnormalUpdateCommand dataCompanyAbnormalUpdateCommand) {
        return dataCompanyAbnormalUpdateCommandExecutor.execute(dataCompanyAbnormalUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyAbnormalExWarehouseVO> warehouse(DataCompanyAbnormalWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyAbnormalCreateCommandExecutor(DataCompanyAbnormalCreateCommandExecutor dataCompanyAbnormalCreateCommandExecutor) {
        this.dataCompanyAbnormalCreateCommandExecutor = dataCompanyAbnormalCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyAbnormalDeleteCommandExecutor(DataCompanyAbnormalDeleteCommandExecutor dataCompanyAbnormalDeleteCommandExecutor) {
        this.dataCompanyAbnormalDeleteCommandExecutor = dataCompanyAbnormalDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAbnormalUpdateCommandExecutor(DataCompanyAbnormalUpdateCommandExecutor dataCompanyAbnormalUpdateCommandExecutor) {
        this.dataCompanyAbnormalUpdateCommandExecutor = dataCompanyAbnormalUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAbnormalCommandExecutor(DataCompanyAbnormalCommandExecutor dataCompanyAbnormalCommandExecutor) {
        this.dataCompanyAbnormalCommandExecutor = dataCompanyAbnormalCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAbnormalWarehouseCommandExecutor(DataCompanyAbnormalWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}