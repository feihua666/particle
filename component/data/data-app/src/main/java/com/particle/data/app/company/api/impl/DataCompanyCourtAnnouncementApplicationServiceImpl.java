package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyCourtAnnouncementCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyCourtAnnouncementDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyCourtAnnouncementUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyCourtAnnouncementCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyCourtAnnouncementApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCourtAnnouncementWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyCourtAnnouncementWarehouseCommandExecutor;
/**
 * <p>
 * 企业法院公告 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:05
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyCourtAnnouncementApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyCourtAnnouncementApplicationService {

    private DataCompanyCourtAnnouncementCreateCommandExecutor dataCompanyCourtAnnouncementCreateCommandExecutor;

    private DataCompanyCourtAnnouncementDeleteCommandExecutor dataCompanyCourtAnnouncementDeleteCommandExecutor;

    private DataCompanyCourtAnnouncementUpdateCommandExecutor dataCompanyCourtAnnouncementUpdateCommandExecutor;

    private DataCompanyCourtAnnouncementCommandExecutor dataCompanyCourtAnnouncementCommandExecutor;

    private DataCompanyCourtAnnouncementWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyCourtAnnouncementVO> create(DataCompanyCourtAnnouncementCreateCommand dataCompanyCourtAnnouncementCreateCommand) {
        return dataCompanyCourtAnnouncementCreateCommandExecutor.execute(dataCompanyCourtAnnouncementCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyCourtAnnouncementVO> delete(IdCommand deleteCommand) {
        return dataCompanyCourtAnnouncementDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyCourtAnnouncementVO> update(DataCompanyCourtAnnouncementUpdateCommand dataCompanyCourtAnnouncementUpdateCommand) {
        return dataCompanyCourtAnnouncementUpdateCommandExecutor.execute(dataCompanyCourtAnnouncementUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyCourtAnnouncementExWarehouseVO> warehouse(DataCompanyCourtAnnouncementWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyCourtAnnouncementCreateCommandExecutor(DataCompanyCourtAnnouncementCreateCommandExecutor dataCompanyCourtAnnouncementCreateCommandExecutor) {
        this.dataCompanyCourtAnnouncementCreateCommandExecutor = dataCompanyCourtAnnouncementCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyCourtAnnouncementDeleteCommandExecutor(DataCompanyCourtAnnouncementDeleteCommandExecutor dataCompanyCourtAnnouncementDeleteCommandExecutor) {
        this.dataCompanyCourtAnnouncementDeleteCommandExecutor = dataCompanyCourtAnnouncementDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCourtAnnouncementUpdateCommandExecutor(DataCompanyCourtAnnouncementUpdateCommandExecutor dataCompanyCourtAnnouncementUpdateCommandExecutor) {
        this.dataCompanyCourtAnnouncementUpdateCommandExecutor = dataCompanyCourtAnnouncementUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCourtAnnouncementCommandExecutor(DataCompanyCourtAnnouncementCommandExecutor dataCompanyCourtAnnouncementCommandExecutor) {
        this.dataCompanyCourtAnnouncementCommandExecutor = dataCompanyCourtAnnouncementCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCourtAnnouncementWarehouseCommandExecutor(DataCompanyCourtAnnouncementWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}