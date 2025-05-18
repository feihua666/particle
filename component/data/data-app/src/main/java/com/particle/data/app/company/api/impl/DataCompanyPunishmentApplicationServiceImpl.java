package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyPunishmentCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyPunishmentDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyPunishmentUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyPunishmentCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyPunishmentUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyPunishmentApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyPunishmentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyPunishmentVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyPunishmentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPunishmentExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyPunishmentWarehouseCommandExecutor;
/**
 * <p>
 * 企业行政处罚 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:37
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyPunishmentApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyPunishmentApplicationService {

    private DataCompanyPunishmentCreateCommandExecutor dataCompanyPunishmentCreateCommandExecutor;

    private DataCompanyPunishmentDeleteCommandExecutor dataCompanyPunishmentDeleteCommandExecutor;

    private DataCompanyPunishmentUpdateCommandExecutor dataCompanyPunishmentUpdateCommandExecutor;

    private DataCompanyPunishmentCommandExecutor dataCompanyPunishmentCommandExecutor;
    private DataCompanyPunishmentWarehouseCommandExecutor dataCompanyPunishmentWarehouseCommandExecutor;


    @Override
    public SingleResponse<DataCompanyPunishmentVO> create(DataCompanyPunishmentCreateCommand dataCompanyPunishmentCreateCommand) {
        return dataCompanyPunishmentCreateCommandExecutor.execute(dataCompanyPunishmentCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyPunishmentVO> delete(IdCommand deleteCommand) {
        return dataCompanyPunishmentDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyPunishmentVO> update(DataCompanyPunishmentUpdateCommand dataCompanyPunishmentUpdateCommand) {
        return dataCompanyPunishmentUpdateCommandExecutor.execute(dataCompanyPunishmentUpdateCommand);
    }

    @Override
    public SingleResponse<DataCompanyPunishmentExWarehouseVO> warehouse(DataCompanyPunishmentWarehouseCommand dataCompanyPunishmentWarehouseCommand) {
        return dataCompanyPunishmentWarehouseCommandExecutor.warehouse(dataCompanyPunishmentWarehouseCommand);
    }

    @Autowired
    public void setDataCompanyPunishmentCreateCommandExecutor(DataCompanyPunishmentCreateCommandExecutor dataCompanyPunishmentCreateCommandExecutor) {
        this.dataCompanyPunishmentCreateCommandExecutor = dataCompanyPunishmentCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyPunishmentDeleteCommandExecutor(DataCompanyPunishmentDeleteCommandExecutor dataCompanyPunishmentDeleteCommandExecutor) {
        this.dataCompanyPunishmentDeleteCommandExecutor = dataCompanyPunishmentDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyPunishmentUpdateCommandExecutor(DataCompanyPunishmentUpdateCommandExecutor dataCompanyPunishmentUpdateCommandExecutor) {
        this.dataCompanyPunishmentUpdateCommandExecutor = dataCompanyPunishmentUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyPunishmentCommandExecutor(DataCompanyPunishmentCommandExecutor dataCompanyPunishmentCommandExecutor) {
        this.dataCompanyPunishmentCommandExecutor = dataCompanyPunishmentCommandExecutor;
    }
    @Autowired
    public void setDataCompanyPunishmentWarehouseCommandExecutor(DataCompanyPunishmentWarehouseCommandExecutor dataCompanyPunishmentWarehouseCommandExecutor) {
        this.dataCompanyPunishmentWarehouseCommandExecutor = dataCompanyPunishmentWarehouseCommandExecutor;
    }

}
