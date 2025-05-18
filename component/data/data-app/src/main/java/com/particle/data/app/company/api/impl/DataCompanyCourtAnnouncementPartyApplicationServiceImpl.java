package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyCourtAnnouncementPartyCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyCourtAnnouncementPartyDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyCourtAnnouncementPartyUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyCourtAnnouncementPartyCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementPartyUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyCourtAnnouncementPartyApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyCourtAnnouncementPartyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementPartyVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCourtAnnouncementPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementPartyExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyCourtAnnouncementPartyWarehouseCommandExecutor;
/**
 * <p>
 * 企业法院公告当事人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:44
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyCourtAnnouncementPartyApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyCourtAnnouncementPartyApplicationService {

    private DataCompanyCourtAnnouncementPartyCreateCommandExecutor dataCompanyCourtAnnouncementPartyCreateCommandExecutor;

    private DataCompanyCourtAnnouncementPartyDeleteCommandExecutor dataCompanyCourtAnnouncementPartyDeleteCommandExecutor;

    private DataCompanyCourtAnnouncementPartyUpdateCommandExecutor dataCompanyCourtAnnouncementPartyUpdateCommandExecutor;

    private DataCompanyCourtAnnouncementPartyCommandExecutor dataCompanyCourtAnnouncementPartyCommandExecutor;

    private DataCompanyCourtAnnouncementPartyWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyCourtAnnouncementPartyVO> create(DataCompanyCourtAnnouncementPartyCreateCommand dataCompanyCourtAnnouncementPartyCreateCommand) {
        return dataCompanyCourtAnnouncementPartyCreateCommandExecutor.execute(dataCompanyCourtAnnouncementPartyCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyCourtAnnouncementPartyVO> delete(IdCommand deleteCommand) {
        return dataCompanyCourtAnnouncementPartyDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyCourtAnnouncementPartyVO> update(DataCompanyCourtAnnouncementPartyUpdateCommand dataCompanyCourtAnnouncementPartyUpdateCommand) {
        return dataCompanyCourtAnnouncementPartyUpdateCommandExecutor.execute(dataCompanyCourtAnnouncementPartyUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyCourtAnnouncementPartyExWarehouseVO> warehouse(DataCompanyCourtAnnouncementPartyWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyCourtAnnouncementPartyCreateCommandExecutor(DataCompanyCourtAnnouncementPartyCreateCommandExecutor dataCompanyCourtAnnouncementPartyCreateCommandExecutor) {
        this.dataCompanyCourtAnnouncementPartyCreateCommandExecutor = dataCompanyCourtAnnouncementPartyCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyCourtAnnouncementPartyDeleteCommandExecutor(DataCompanyCourtAnnouncementPartyDeleteCommandExecutor dataCompanyCourtAnnouncementPartyDeleteCommandExecutor) {
        this.dataCompanyCourtAnnouncementPartyDeleteCommandExecutor = dataCompanyCourtAnnouncementPartyDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCourtAnnouncementPartyUpdateCommandExecutor(DataCompanyCourtAnnouncementPartyUpdateCommandExecutor dataCompanyCourtAnnouncementPartyUpdateCommandExecutor) {
        this.dataCompanyCourtAnnouncementPartyUpdateCommandExecutor = dataCompanyCourtAnnouncementPartyUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCourtAnnouncementPartyCommandExecutor(DataCompanyCourtAnnouncementPartyCommandExecutor dataCompanyCourtAnnouncementPartyCommandExecutor) {
        this.dataCompanyCourtAnnouncementPartyCommandExecutor = dataCompanyCourtAnnouncementPartyCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCourtAnnouncementPartyWarehouseCommandExecutor(DataCompanyCourtAnnouncementPartyWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}