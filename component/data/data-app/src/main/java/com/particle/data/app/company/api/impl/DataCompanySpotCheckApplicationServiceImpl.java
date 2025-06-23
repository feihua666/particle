package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanySpotCheckCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanySpotCheckDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanySpotCheckUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanySpotCheckCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanySpotCheckUpdateCommand;
import com.particle.data.client.company.api.IDataCompanySpotCheckApplicationService;
import com.particle.data.client.company.dto.command.DataCompanySpotCheckCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanySpotCheckVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanySpotCheckWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySpotCheckExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanySpotCheckWarehouseCommandExecutor;
/**
 * <p>
 * 企业抽查检查 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:39
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanySpotCheckApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanySpotCheckApplicationService {

    private DataCompanySpotCheckCreateCommandExecutor dataCompanySpotCheckCreateCommandExecutor;

    private DataCompanySpotCheckDeleteCommandExecutor dataCompanySpotCheckDeleteCommandExecutor;

    private DataCompanySpotCheckUpdateCommandExecutor dataCompanySpotCheckUpdateCommandExecutor;

    private DataCompanySpotCheckCommandExecutor dataCompanySpotCheckCommandExecutor;

    private DataCompanySpotCheckWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanySpotCheckVO> create(DataCompanySpotCheckCreateCommand dataCompanySpotCheckCreateCommand) {
        return dataCompanySpotCheckCreateCommandExecutor.execute(dataCompanySpotCheckCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanySpotCheckVO> delete(IdCommand deleteCommand) {
        return dataCompanySpotCheckDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanySpotCheckVO> update(DataCompanySpotCheckUpdateCommand dataCompanySpotCheckUpdateCommand) {
        return dataCompanySpotCheckUpdateCommandExecutor.execute(dataCompanySpotCheckUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanySpotCheckExWarehouseVO> warehouse(DataCompanySpotCheckWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanySpotCheckCreateCommandExecutor(DataCompanySpotCheckCreateCommandExecutor dataCompanySpotCheckCreateCommandExecutor) {
        this.dataCompanySpotCheckCreateCommandExecutor = dataCompanySpotCheckCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanySpotCheckDeleteCommandExecutor(DataCompanySpotCheckDeleteCommandExecutor dataCompanySpotCheckDeleteCommandExecutor) {
        this.dataCompanySpotCheckDeleteCommandExecutor = dataCompanySpotCheckDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanySpotCheckUpdateCommandExecutor(DataCompanySpotCheckUpdateCommandExecutor dataCompanySpotCheckUpdateCommandExecutor) {
        this.dataCompanySpotCheckUpdateCommandExecutor = dataCompanySpotCheckUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanySpotCheckCommandExecutor(DataCompanySpotCheckCommandExecutor dataCompanySpotCheckCommandExecutor) {
        this.dataCompanySpotCheckCommandExecutor = dataCompanySpotCheckCommandExecutor;
    }
    @Autowired
    public void setDataCompanySpotCheckWarehouseCommandExecutor(DataCompanySpotCheckWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}