package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyOpenCourtAnnouncementContentCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyOpenCourtAnnouncementContentDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyOpenCourtAnnouncementContentUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyOpenCourtAnnouncementContentCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementContentUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyOpenCourtAnnouncementContentApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementContentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementContentVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyOpenCourtAnnouncementContentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementContentExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyOpenCourtAnnouncementContentWarehouseCommandExecutor;
/**
 * <p>
 * 企业开庭公告内容 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:18
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyOpenCourtAnnouncementContentApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyOpenCourtAnnouncementContentApplicationService {

    private DataCompanyOpenCourtAnnouncementContentCreateCommandExecutor dataCompanyOpenCourtAnnouncementContentCreateCommandExecutor;

    private DataCompanyOpenCourtAnnouncementContentDeleteCommandExecutor dataCompanyOpenCourtAnnouncementContentDeleteCommandExecutor;

    private DataCompanyOpenCourtAnnouncementContentUpdateCommandExecutor dataCompanyOpenCourtAnnouncementContentUpdateCommandExecutor;

    private DataCompanyOpenCourtAnnouncementContentCommandExecutor dataCompanyOpenCourtAnnouncementContentCommandExecutor;

    private DataCompanyOpenCourtAnnouncementContentWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyOpenCourtAnnouncementContentVO> create(DataCompanyOpenCourtAnnouncementContentCreateCommand dataCompanyOpenCourtAnnouncementContentCreateCommand) {
        return dataCompanyOpenCourtAnnouncementContentCreateCommandExecutor.execute(dataCompanyOpenCourtAnnouncementContentCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyOpenCourtAnnouncementContentVO> delete(IdCommand deleteCommand) {
        return dataCompanyOpenCourtAnnouncementContentDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyOpenCourtAnnouncementContentVO> update(DataCompanyOpenCourtAnnouncementContentUpdateCommand dataCompanyOpenCourtAnnouncementContentUpdateCommand) {
        return dataCompanyOpenCourtAnnouncementContentUpdateCommandExecutor.execute(dataCompanyOpenCourtAnnouncementContentUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyOpenCourtAnnouncementContentExWarehouseVO> warehouse(DataCompanyOpenCourtAnnouncementContentWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyOpenCourtAnnouncementContentCreateCommandExecutor(DataCompanyOpenCourtAnnouncementContentCreateCommandExecutor dataCompanyOpenCourtAnnouncementContentCreateCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementContentCreateCommandExecutor = dataCompanyOpenCourtAnnouncementContentCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyOpenCourtAnnouncementContentDeleteCommandExecutor(DataCompanyOpenCourtAnnouncementContentDeleteCommandExecutor dataCompanyOpenCourtAnnouncementContentDeleteCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementContentDeleteCommandExecutor = dataCompanyOpenCourtAnnouncementContentDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyOpenCourtAnnouncementContentUpdateCommandExecutor(DataCompanyOpenCourtAnnouncementContentUpdateCommandExecutor dataCompanyOpenCourtAnnouncementContentUpdateCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementContentUpdateCommandExecutor = dataCompanyOpenCourtAnnouncementContentUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyOpenCourtAnnouncementContentCommandExecutor(DataCompanyOpenCourtAnnouncementContentCommandExecutor dataCompanyOpenCourtAnnouncementContentCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementContentCommandExecutor = dataCompanyOpenCourtAnnouncementContentCommandExecutor;
    }
    @Autowired
    public void setDataCompanyOpenCourtAnnouncementContentWarehouseCommandExecutor(DataCompanyOpenCourtAnnouncementContentWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}