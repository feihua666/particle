package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyCourtAnnouncementContentCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyCourtAnnouncementContentDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyCourtAnnouncementContentUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyCourtAnnouncementContentCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementContentUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyCourtAnnouncementContentApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementContentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementContentVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCourtAnnouncementContentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementContentExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyCourtAnnouncementContentWarehouseCommandExecutor;
/**
 * <p>
 * 企业法院公告内容 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:28
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyCourtAnnouncementContentApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyCourtAnnouncementContentApplicationService {

    private DataCompanyCourtAnnouncementContentCreateCommandExecutor dataCompanyCourtAnnouncementContentCreateCommandExecutor;

    private DataCompanyCourtAnnouncementContentDeleteCommandExecutor dataCompanyCourtAnnouncementContentDeleteCommandExecutor;

    private DataCompanyCourtAnnouncementContentUpdateCommandExecutor dataCompanyCourtAnnouncementContentUpdateCommandExecutor;

    private DataCompanyCourtAnnouncementContentCommandExecutor dataCompanyCourtAnnouncementContentCommandExecutor;

    private DataCompanyCourtAnnouncementContentWarehouseCommandExecutor dataCompanyCourtAnnouncementContentWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyCourtAnnouncementContentVO> create(DataCompanyCourtAnnouncementContentCreateCommand dataCompanyCourtAnnouncementContentCreateCommand) {
        return dataCompanyCourtAnnouncementContentCreateCommandExecutor.execute(dataCompanyCourtAnnouncementContentCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyCourtAnnouncementContentVO> delete(IdCommand deleteCommand) {
        return dataCompanyCourtAnnouncementContentDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyCourtAnnouncementContentVO> update(DataCompanyCourtAnnouncementContentUpdateCommand dataCompanyCourtAnnouncementContentUpdateCommand) {
        return dataCompanyCourtAnnouncementContentUpdateCommandExecutor.execute(dataCompanyCourtAnnouncementContentUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyCourtAnnouncementContentExWarehouseVO> warehouse(DataCompanyCourtAnnouncementContentWarehouseCommand dataCompanyCourtAnnouncementContentWarehouseCommand) {
        return dataCompanyCourtAnnouncementContentWarehouseCommandExecutor.warehouse(dataCompanyCourtAnnouncementContentWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyCourtAnnouncementContentCreateCommandExecutor(DataCompanyCourtAnnouncementContentCreateCommandExecutor dataCompanyCourtAnnouncementContentCreateCommandExecutor) {
        this.dataCompanyCourtAnnouncementContentCreateCommandExecutor = dataCompanyCourtAnnouncementContentCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyCourtAnnouncementContentDeleteCommandExecutor(DataCompanyCourtAnnouncementContentDeleteCommandExecutor dataCompanyCourtAnnouncementContentDeleteCommandExecutor) {
        this.dataCompanyCourtAnnouncementContentDeleteCommandExecutor = dataCompanyCourtAnnouncementContentDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCourtAnnouncementContentUpdateCommandExecutor(DataCompanyCourtAnnouncementContentUpdateCommandExecutor dataCompanyCourtAnnouncementContentUpdateCommandExecutor) {
        this.dataCompanyCourtAnnouncementContentUpdateCommandExecutor = dataCompanyCourtAnnouncementContentUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCourtAnnouncementContentCommandExecutor(DataCompanyCourtAnnouncementContentCommandExecutor dataCompanyCourtAnnouncementContentCommandExecutor) {
        this.dataCompanyCourtAnnouncementContentCommandExecutor = dataCompanyCourtAnnouncementContentCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCourtAnnouncementContentWarehouseCommandExecutor(DataCompanyCourtAnnouncementContentWarehouseCommandExecutor dataCompanyCourtAnnouncementContentWarehouseCommandExecutor) {
        this.dataCompanyCourtAnnouncementContentWarehouseCommandExecutor = dataCompanyCourtAnnouncementContentWarehouseCommandExecutor;
    }

}