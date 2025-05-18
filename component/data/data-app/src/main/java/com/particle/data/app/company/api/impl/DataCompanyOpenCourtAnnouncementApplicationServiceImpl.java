package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyOpenCourtAnnouncementCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyOpenCourtAnnouncementDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyOpenCourtAnnouncementUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyOpenCourtAnnouncementCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyOpenCourtAnnouncementApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyOpenCourtAnnouncementWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyOpenCourtAnnouncementWarehouseCommandExecutor;
/**
 * <p>
 * 企业开庭公告 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:31
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyOpenCourtAnnouncementApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyOpenCourtAnnouncementApplicationService {

    private DataCompanyOpenCourtAnnouncementCreateCommandExecutor dataCompanyOpenCourtAnnouncementCreateCommandExecutor;

    private DataCompanyOpenCourtAnnouncementDeleteCommandExecutor dataCompanyOpenCourtAnnouncementDeleteCommandExecutor;

    private DataCompanyOpenCourtAnnouncementUpdateCommandExecutor dataCompanyOpenCourtAnnouncementUpdateCommandExecutor;

    private DataCompanyOpenCourtAnnouncementCommandExecutor dataCompanyOpenCourtAnnouncementCommandExecutor;

    private DataCompanyOpenCourtAnnouncementWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyOpenCourtAnnouncementVO> create(DataCompanyOpenCourtAnnouncementCreateCommand dataCompanyOpenCourtAnnouncementCreateCommand) {
        return dataCompanyOpenCourtAnnouncementCreateCommandExecutor.execute(dataCompanyOpenCourtAnnouncementCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyOpenCourtAnnouncementVO> delete(IdCommand deleteCommand) {
        return dataCompanyOpenCourtAnnouncementDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyOpenCourtAnnouncementVO> update(DataCompanyOpenCourtAnnouncementUpdateCommand dataCompanyOpenCourtAnnouncementUpdateCommand) {
        return dataCompanyOpenCourtAnnouncementUpdateCommandExecutor.execute(dataCompanyOpenCourtAnnouncementUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyOpenCourtAnnouncementExWarehouseVO> warehouse(DataCompanyOpenCourtAnnouncementWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyOpenCourtAnnouncementCreateCommandExecutor(DataCompanyOpenCourtAnnouncementCreateCommandExecutor dataCompanyOpenCourtAnnouncementCreateCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementCreateCommandExecutor = dataCompanyOpenCourtAnnouncementCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyOpenCourtAnnouncementDeleteCommandExecutor(DataCompanyOpenCourtAnnouncementDeleteCommandExecutor dataCompanyOpenCourtAnnouncementDeleteCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementDeleteCommandExecutor = dataCompanyOpenCourtAnnouncementDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyOpenCourtAnnouncementUpdateCommandExecutor(DataCompanyOpenCourtAnnouncementUpdateCommandExecutor dataCompanyOpenCourtAnnouncementUpdateCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementUpdateCommandExecutor = dataCompanyOpenCourtAnnouncementUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyOpenCourtAnnouncementCommandExecutor(DataCompanyOpenCourtAnnouncementCommandExecutor dataCompanyOpenCourtAnnouncementCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementCommandExecutor = dataCompanyOpenCourtAnnouncementCommandExecutor;
    }
    @Autowired
    public void setDataCompanyOpenCourtAnnouncementWarehouseCommandExecutor(DataCompanyOpenCourtAnnouncementWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}